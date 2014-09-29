package com.portal.dao.implementation;

import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.dao.interfaces.CommentDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Article;
import com.portal.entity.Comment;
import com.portal.entity.CommentState;
import com.portal.entity.User;
import com.portal.util.ClassComment;
import com.portal.util.ClassParentComment;
import com.portal.util.ParentComment;

import java.lang.reflect.Field;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl implements CommentDAOI {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ArticleDAOI articleDao;

	@Autowired
	private UserDAOI userDao;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

//	private Session openSession() {
//			return sessionFactory.openSession();
//	}

	@Override
	public Comment getById(long id) {
		
		Session session = sessionFactory.openSession();
		
		try {
		
		Comment c = (Comment) session.load(Comment.class, id);

		Comment comToReturn = new Comment();
		comToReturn.setId(c.getId());
		comToReturn.setContent(c.getContent());
		comToReturn.setDate(c.getDate());
		comToReturn.setResponsesNumber(c.getResponsesNumber());

		CommentState cs = new CommentState();
		cs.setId(c.getId());

		Article a = new Article();
		a.setId(c.getArticle().getId());

		User u = new User();
		u.setId(c.getUser().getId());
		u.setDateOfRegistration(null);

		if (c.getParent() != null) {
			Comment parent = new Comment();
			parent.setId(c.getParent().getId());
			comToReturn.setParent(parent);
		}

		comToReturn.setState(cs);
		comToReturn.setArticle(a);
		comToReturn.setUser(u);
		
		return comToReturn;
		
		} finally {
			if (session != null)
				session.close();
		}

	}

	Comment root(Comment comment) {
		Comment c = comment;
		while (c.getParent() != null)
			c = this.getById(c.getParent().getId());
		return c;
	}

	private Criteria getCriteria(int limit, int pageNum, String sortby,
			boolean asc) {
		Criteria criteria = this.getCurrentSession().createCriteria(
				Comment.class, "c");
		if (asc)
			criteria.addOrder(Order.asc(sortby));
		else
			criteria.addOrder(Order.desc(sortby));
		criteria.setFirstResult(pageNum * limit);
		criteria.setMaxResults(limit);
		return criteria;
	}

	@Override
	public List<Comment> get(int limit, int pageNum, String sortby, boolean asc) {
		Criteria criteria = this.getCriteria(limit, pageNum, sortby, asc);
		return criteria.list();
	}

	@Override
	public List<Comment> get(long user_id, int limit, int pageNum,
			String sortby, boolean asc) {
		Field sby = null;
		try {
			sby = Comment.class.getField(sortby);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Comment>();
		}
		String sqlQuery = "FROM Comment AS c "
				+ " FULL JOIN FETCH c.user AS u " + " WHERE  ORDER BY c."
				+ sby.getName();
		if (asc)
			sqlQuery += " ASC";
		else
			sqlQuery += " DESC";
		Query query = this.getCurrentSession().createQuery(sqlQuery)
				.setFirstResult(limit * pageNum).setMaxResults(limit);
		return query.list();
	}

	@Override
	public List<Comment> get(long articleId, long thread, int limit,
			int pageNum, String sortby, boolean asc) {
		Criteria criteria = this.getCriteria(limit, pageNum, sortby, asc);
		criteria.createAlias("c.article", "a");
		criteria.setProjection(Projections.distinct(Projections
				.projectionList().add(Projections.property("m.id"),
						Long.toString(articleId))));
		return criteria.list();
	}

	@Override
	public List<Comment> getApprovedByUser(long userId) {
		
		Session session = sessionFactory.openSession();
		
		try {
			Query query = session.createQuery("from Comment u where u.user= :login");
			query.setParameter("userId", userId);
			return query.list();
		} finally {
			if (session != null)
				session.close();
		}
	}

	@Override
	public boolean add(String login, String content, Long parent, long articleId) {
		Comment comment = new Comment();
		
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		
		User u = null;
		
		if(criteria.list().size() > 0)
			u = (User) criteria.list().get(0);
		else return false;

		comment.setUser(u);
		
		if(u.getGroup().getId() > 3) return false;

		if (parent != null) {

            try {
                Comment p = (Comment) session.load(Comment.class, parent);
                if (p.getParent() != null) return false;
                comment.setParent(p);

                Query incrResponses = session.createQuery("update Comment c set c.responsesNumber = :resp where c.id = :id");
                incrResponses.setParameter("resp", p.getResponsesNumber() + 1);
                incrResponses.setParameter("id", parent);
                incrResponses.executeUpdate();

                comment.setResponsesNumber(p.getResponsesNumber() + 1);

                List<Comment> children = children(p);

                for (Comment child : children) {
                    Query incrResponses2 = session.createQuery("update Comment c set c.responsesNumber = :resp where c.id = :id");
                    incrResponses2.setParameter("resp", child.getResponsesNumber() + 1);
                    incrResponses2.setParameter("id", child.getId());
                    incrResponses2.executeUpdate();
                }
            } catch (Exception e) {
                if (session != null)
                    session.close();

                return false;
            }
        }
		
			try {
	
				Article a = (Article) session.load(Article.class, articleId);
				comment.setArticle(a);
		
				comment.setContent(content);
				CommentState cs = (CommentState) session.load(CommentState.class, 1l);
				comment.setState(cs);
				comment.setDate(new Date());
		
				session.save(comment);
			
			} catch(Exception e) {
				if (session != null)
					session.close();
				
				return false;
			}

		
		if (session != null)
			session.close();
		
		return true;
	}

	@Override
	public void deleteCascade(long comment_id) {
		Comment c = this.getById(comment_id);
		for (Comment comment : children(c)) {
			deleteCascade(comment.getId());
		}
		this.getCurrentSession().delete(c);
	}

	@Override
	public void delete(List<Comment> comments) {
		
		Session session = sessionFactory.openSession();

		for (Comment c : comments) {

			List<Comment> children = children(c);

			for (Comment child : children) {
				Query deleteChild = session.createQuery(
						"delete Comment c where c.id = :id");
				deleteChild.setParameter("id", child.getId());
				deleteChild.executeUpdate();
			}

			Query deleteComment = session.createQuery(
					"delete Comment c where c.id = :id");
			deleteComment.setParameter("id", c.getId());
			deleteComment.executeUpdate();
		}
		
		if (session != null)
			session.close();

	}

	@Override
	public List<Comment> children(Comment comment) {
		
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Comment.class);
		criteria.add(Restrictions.eq("parent", comment));
		
		List<Comment> comments = criteria.list();
		
		if (session != null)
			session.close();
		
		return comments;
	}

	@Override
	public void setStates(List<Comment> comments) {
		
		Session session = sessionFactory.openSession();

		for (Comment c : comments) {
			Query query = session.createQuery("update Comment c set c.state = :state where c.id = :id");
			query.setParameter("id", c.getId());
			query.setParameter("state", c.getState());

			query.executeUpdate();
		}
		
		if (session != null)
			session.close();
	}

	@Override
	public ClassComment getUserComments(String login, int limit, int pageNO,
			String sortOrder) {

		List<User> u = new ArrayList<User>();
		
		Session session = sessionFactory.openSession();
		
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("login", login));
		u = cri.list();
		Long userID = u.get(0).getId();

		List<Comment> comments = new ArrayList<Comment>();
		Criteria criteria = session.createCriteria(Comment.class);
		criteria.add(Restrictions.eq("user.id", userID));
		criteria.add(Restrictions.eq("state.id", 2L));
		criteria.setFirstResult(limit * (pageNO - 1));
		criteria.setMaxResults(limit);
		if (sortOrder.equals("DESC")) {
			criteria.addOrder(Order.desc("id"));
		} else {
			criteria.addOrder(Order.asc("id"));
		}
		List<Comment> coms = criteria.list();

		Criteria criteria2 = session.createCriteria(Comment.class);
		criteria2.add(Restrictions.eq("user.id", userID));
		criteria2.add(Restrictions.eq("state.id", 2L));
		List<Comment> coms2 = criteria2.list();

		for (Comment c : coms) {

			Comment com = new Comment();

			User user = new User();
			user.setId(c.getUser().getId());

			// CommentState state = new CommentState();
			// state.setId(c.getState().getId());

			Article article = new Article();
			article.setId(c.getArticle().getId());
			// article.setContent(c.getArticle().getContent());

			Comment parent = new Comment();

			if(c.getParent() != null){
				parent.setId(c.getParent().getId());
			} else {
				parent.setId(null);
			}

			com.setId(c.getId());
			com.setArticle(article);
			com.setUser(user);
			com.setContent(c.getContent());
			com.setDate(c.getDate());
			com.setResponsesNumber(c.getResponsesNumber());			
			com.setParent(parent);
			// com.setState(state);

			comments.add(com);						
		}

		ClassComment classComment = new ClassComment();
		classComment.setComments(comments);
		classComment.setSize(coms2.size());

		if (session != null)
			session.close();
		
		return classComment;
	}

	@Override
	public ClassParentComment getArticleComments(long articleID, int limit,
			int pageNO, String sortOrder) {

		List<ParentComment> pComments = new ArrayList<ParentComment>();
		
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Comment.class);
		criteria.add(Restrictions.eq("article.id", articleID));
		criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("state.id", 2L));
		criteria.setFirstResult(limit * (pageNO - 1));
		criteria.setMaxResults(limit);
		if (sortOrder.equals("DESC")) {
			criteria.addOrder(Order.desc("id"));
		} else {
			criteria.addOrder(Order.asc("id"));
		}

		List<Comment> coms = criteria.list();

		Criteria criteria2 = session.createCriteria(Comment.class);
		criteria2.add(Restrictions.eq("article.id", articleID));
		criteria2.add(Restrictions.eq("state.id", 2L));
		criteria2.add(Restrictions.isNull("parent"));
		List<Comment> coms2 = criteria2.list();

		for (Comment c : coms) {

			ParentComment parentComment = new ParentComment();
			User user = new User();
			user.setId(c.getUser().getId());
			user.setLogin(c.getUser().getLogin());
			user.setAvatar(c.getUser().getAvatar());
			user.setDateOfRegistration(null);

			CommentState state = new CommentState();
			state.setId(c.getState().getId());

			Article article = new Article();
			article.setId(c.getArticle().getId());

			parentComment.setId(c.getId());
			parentComment.setUser(user);
			parentComment.setContent(c.getContent());
			parentComment.setDate(c.getDate());
			parentComment.setResponsesNumber(c.getResponsesNumber());
			parentComment.setArticle(article);
			parentComment.setState(state);
			parentComment.setParent(null);

			List<Comment> com = new ArrayList<Comment>();
			com = children(parentComment);

			for (Comment r : com) {

				Comment reply = new Comment();

				User userR = new User();
				userR.setId(r.getUser().getId());
				userR.setLogin(r.getUser().getLogin());
				userR.setAvatar(r.getUser().getAvatar());
				userR.setDateOfRegistration(null);

				CommentState stateR = new CommentState();
				stateR.setId(r.getState().getId());

				Article articleR = new Article();
				articleR.setId(r.getArticle().getId());

				Comment parentR = new Comment();

				parentR.setId(r.getParent().getId());

				reply.setId(r.getId());
				reply.setUser(userR);
				reply.setContent(r.getContent());
				reply.setDate(r.getDate());
				reply.setResponsesNumber(null);
				reply.setArticle(articleR);
				reply.setParent(parentR);
				reply.setState(stateR);

				parentComment.addReply(reply);
			}

			pComments.add(parentComment);
		}
		ClassParentComment comParent = new ClassParentComment();
		comParent.setComments(pComments);
		comParent.setSize(coms2.size());
		
		if (session != null)
			session.close();

		return comParent;
	}

	@Override
	public ClassComment getAllComments(long status, int limit, int pageNo,
			String sortOrder) {

		List<Comment> comments = new ArrayList<Comment>();
		
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Comment.class);
		// criteria.add(Restrictions.isNull("parent"));
		criteria.add(Restrictions.eq("state.id", status));
		criteria.setFirstResult(limit * (pageNo - 1));
		criteria.setMaxResults(limit);
		if (sortOrder.equals("DESC")) {
			criteria.addOrder(Order.desc("id"));
		} else {
			criteria.addOrder(Order.asc("id"));
		}

		List<Comment> coms = criteria.list();

		Criteria criteria2 = session.createCriteria(Comment.class);
		criteria2.add(Restrictions.isNull("parent"));
		criteria2.add(Restrictions.eq("state.id", status));

		List<Comment> coms2 = criteria2.list();

		for (Comment c : coms) {

			Comment com = new Comment();

			User user = new User();
			user.setId(c.getUser().getId());

			Criteria criteria3 = session.createCriteria(User.class);
			criteria3.add(Restrictions.eq("id", c.getUser().getId()));

			user.setLogin(((User) criteria3.list().get(0)).getLogin());

			CommentState state = new CommentState();
			state.setId(c.getState().getId());

			Article article = new Article();
			article.setId(c.getArticle().getId());
			article.setContent(c.getArticle().getContent());

			Comment parent = new Comment();

			try {
				parent.setId(c.getParent().getId());
			} catch (Exception e) {
				parent.setId(0L);
			}

			com.setId(c.getId());
			com.setUser(user);
			com.setContent(c.getContent());
			com.setDate(c.getDate());
			com.setResponsesNumber(c.getResponsesNumber());
			com.setArticle(article);
			com.setParent(parent);
			com.setState(state);

			comments.add(com);
		}

		ClassComment classCom = new ClassComment();
		classCom.setComments(comments);
		classCom.setSize(coms2.size());
		
		if (session != null)
			session.close();

		return classCom;
	}

	@Override
	public ClassComment getAllComments(int limit, int pageNo, String sortOrder) {

		List<Comment> comments = new ArrayList<Comment>();
		
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Comment.class);
		// criteria.add(Restrictions.isNull("parent"));
		criteria.setFirstResult(limit * (pageNo - 1));
		criteria.setMaxResults(limit);
		if (sortOrder.equals("DESC")) {
			criteria.addOrder(Order.desc("id"));
		} else {
			criteria.addOrder(Order.asc("id"));
		}

		List<Comment> coms = criteria.list();

		Criteria criteria2 = session.createCriteria(Comment.class);
		criteria2.add(Restrictions.isNull("parent"));

		List<Comment> coms2 = criteria2.list();

		for (Comment c : coms) {

			Comment com = new Comment();

			User user = new User();
			user.setId(c.getUser().getId());

			Criteria criteria3 = session.createCriteria(User.class);
			criteria3.add(Restrictions.eq("id", c.getUser().getId()));

			user.setLogin(((User) criteria3.list().get(0)).getLogin());

			CommentState state = new CommentState();
			state.setId(c.getState().getId());

			Article article = new Article();
			article.setId(c.getArticle().getId());
			article.setContent(c.getArticle().getContent());

			Comment parent = new Comment();

			try {
				parent.setId(c.getParent().getId());
			} catch (Exception e) {
				parent.setId(0L);
			}

			com.setId(c.getId());
			com.setUser(user);
			com.setContent(c.getContent());
			com.setDate(c.getDate());
			com.setResponsesNumber(c.getResponsesNumber());
			com.setArticle(article);
			com.setParent(parent);
			com.setState(state);

			comments.add(com);
		}

		ClassComment classCom = new ClassComment();
		classCom.setComments(comments);
		classCom.setSize(coms2.size());
		
		if (session != null)
			session.close();

		return classCom;
	}

	@Override
	public long getTotalComments(long status) {

		List<Comment> comments = new ArrayList<Comment>();
		
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Comment.class);
		if (status != 0) {
			criteria.add(Restrictions.eq("state.id", status));
		}
		
		if (session != null)
			session.close();

		return criteria.list().size();
	}

	@Override
	public ClassComment getUserComments(String login, long status, int limit,
			int pageNo, String sortOrder) {

		Session session = sessionFactory.openSession();
		
		List<User> u = new ArrayList<User>();
		Criteria cri = session.createCriteria(User.class);
		cri.add(Restrictions.eq("login", login));
		u = cri.list();
		Long userID = u.get(0).getId();

		List<Comment> comments = new ArrayList<Comment>();
		Criteria criteria = session.createCriteria(Comment.class);
		criteria.add(Restrictions.eq("user.id", userID));
		if (status != 0) {
			criteria.add(Restrictions.eq("state.id", status));
		}
		criteria.setFirstResult(limit * (pageNo - 1));
		criteria.setMaxResults(limit);
		if (sortOrder.equals("DESC")) {
			criteria.addOrder(Order.desc("id"));
		} else {
			criteria.addOrder(Order.asc("id"));
		}

		List<Comment> coms = criteria.list();

		Criteria criteria2 = session.createCriteria(Comment.class);
		criteria2.add(Restrictions.eq("user.id", userID));
		criteria2.add(Restrictions.eq("state.id", status));
		List<Comment> coms2 = criteria2.list();

		for (Comment c : coms) {

			Comment com = new Comment();

			User user = new User();
			user.setId(c.getUser().getId());

			CommentState state = new CommentState();
			state.setId(c.getState().getId());

			Article article = new Article();
			article.setId(c.getArticle().getId());
			article.setContent(c.getArticle().getContent());

			Comment parent = new Comment();

			try {
				parent.setId(c.getParent().getId());
			} catch (Exception e) {
				parent.setId(0L);
			}

			com.setId(c.getId());
			com.setArticle(article);
			com.setUser(user);
			com.setContent(c.getContent());
			com.setDate(c.getDate());
			com.setResponsesNumber(c.getResponsesNumber());
			com.setArticle(article);
			com.setParent(parent);
			com.setState(state);

			comments.add(com);
		}
		ClassComment classComment = new ClassComment();
		classComment.setComments(comments);
		classComment.setSize(coms2.size());
		
		if (session != null)
			session.close();

		return classComment;
	}

}
