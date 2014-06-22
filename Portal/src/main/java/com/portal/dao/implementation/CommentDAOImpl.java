package com.portal.dao.implementation;


import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.dao.interfaces.CommentDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Article;
import com.portal.entity.Comment;
import com.portal.entity.CommentState;
import com.portal.entity.User;

import java.lang.reflect.Field;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
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
	
	private Session openSession() {	
		return sessionFactory.openSession();
	}
	
	@Override
	public Comment getById(long id) {
		Comment c = (Comment) openSession().load(Comment.class, id);
		
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
	}
	Comment root(Comment comment){
		Comment c = comment;
		while(c.getParent() != null)
			c = this.getById(c.getParent().getId());
		return c;
	}
	private Criteria getCriteria(int limit, int pageNum,
			String sortby, boolean asc){
		Criteria criteria=this.getCurrentSession().createCriteria(Comment.class,"c");
		if(asc)
			criteria.addOrder(Order.asc(sortby));
		else
			criteria.addOrder(Order.desc(sortby));
		criteria.setFirstResult(pageNum*limit);
		criteria.setMaxResults(limit);
		return criteria;
	}
	@Override
	public List<Comment> get(int limit, int pageNum,
			String sortby, boolean asc) {
		Criteria criteria= this.getCriteria(limit, pageNum, sortby, asc);
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
		String sqlQuery = "FROM Comment AS c " +
                " FULL JOIN FETCH c.user AS u " +   
                " WHERE  ORDER BY c." + sby.getName();
		if(asc)
			sqlQuery += " ASC";
		else
			sqlQuery += " DESC";
		Query query = this.getCurrentSession().createQuery( sqlQuery )
                   .setFirstResult( limit * pageNum )
                   .setMaxResults( limit );
		return query.list();
	}
	@Override
	public List<Comment> get(long articleId, long thread, int limit,
			int pageNum, String sortby, boolean asc) {
		Criteria criteria = this.getCriteria(limit, pageNum, sortby, asc);
		criteria.createAlias("c.article", "a");
		criteria.setProjection( Projections.distinct( Projections.projectionList()
	            .add( Projections.property("m.id"), Long.toString(articleId))));
		return criteria.list();
	}
	@Override
	public List<Comment> getApprovedByUser(long userId) {
		Query query = this.getCurrentSession().createQuery("from Comment u where u.user= :login");
		query.setParameter("userId", userId);
		return query.list();
	}
	@Override
	public void add(long userId, String content, Long parent, long articleId) {
		Comment comment = new Comment();
		User u = (User) openSession().load(User.class, userId);
		comment.setUser(u);
		
		System.out.println(parent);
		
		if (parent != null) {
			
			Comment p = (Comment) openSession().load(Comment.class, parent);
			comment.setParent(p);

			Query incrResponses = openSession().createQuery("update Comment c set c.responsesNumber = :resp where c.id = :id");
			incrResponses.setParameter("resp", p.getResponsesNumber()+1);
			incrResponses.setParameter("id", parent);
			incrResponses.executeUpdate();
			
			comment.setResponsesNumber(p.getResponsesNumber()+1);
			
			List<Comment> children = children(p);
			
			for (Comment child : children) {
				Query incrResponses2 = openSession().createQuery("update Comment c set c.responsesNumber = :resp where c.id = :id");
				incrResponses2.setParameter("resp", child.getResponsesNumber()+1);
				incrResponses2.setParameter("id", child.getId());
				incrResponses2.executeUpdate();
			}
		}
		
		Article a = (Article) openSession().load(Article.class, articleId);
		comment.setArticle(a);
		
		comment.setContent(content);
		CommentState cs = (CommentState) openSession().load(CommentState.class, 1l);
		comment.setState(cs);
		comment.setDate(new Date());
		
		openSession().save(comment);
	}
	@Override
	public void deleteCascade(long comment_id) {
		Comment c = this.getById(comment_id);
		for(Comment comment : children(c)){
			deleteCascade(comment.getId());
		}
		this.getCurrentSession().delete(c);
	}
	
	@Override
	public void delete(List<Comment> comments) {
		
		for (Comment c : comments) {
			
			List<Comment> children = children(c);
			
			for (Comment child : children) {
				Query deleteChild = openSession().createQuery("delete Comment c where c.id = :id");
				deleteChild.setParameter("id", child.getId());
				deleteChild.executeUpdate();
			}
			
			Query deleteComment = openSession().createQuery("delete Comment c where c.id = :id");
			deleteComment.setParameter("id", c.getId());
			deleteComment.executeUpdate();
		}
		
	}
	
	@Override
	public List<Comment> children(Comment comment) {
		Criteria criteria = openSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("parent", comment));
		return criteria.list();
	}
	
	@Override
	public void setStates(List<Comment> comments) {
		
		for(Comment c : comments) {		
			Query query = openSession().createQuery("update Comment c set c.state = :state where c.id = :id");
			query.setParameter("id", c.getId());
			query.setParameter("state", c.getState());
			
			query.executeUpdate();
		}
	}
	
	@Override
	public List<Comment> getUserComments(long userID, int limit, int pageNO, String sortOrder){
		
		List<Comment> comments = new ArrayList<Comment>();		
		Criteria criteria = openSession().createCriteria(Comment.class);			
			criteria.add(Restrictions.eq("user.id", userID));
			criteria.add(Restrictions.eq("state.id", 2L));
			criteria.setFirstResult(limit*(pageNO-1));
			criteria.setMaxResults(limit);
			if(sortOrder.equals("DESC")){
				criteria.addOrder(Order.desc("id"));	
			} else {
				criteria.addOrder(Order.asc("id"));
			}
			
		List<Comment> coms = criteria.list();
			
		
        for(Comment c : coms) {

        	Comment com = new Comment();
            
            User user = new User();
            user.setId(c.getUser().getId());
            
            //CommentState state = new CommentState();
            //state.setId(c.getState().getId());
            
            Article article = new Article();
            article.setId(c.getArticle().getId());
            //article.setContent(c.getArticle().getContent());
            
            Comment parent = new Comment();
            
            try{            	
                parent.setId(c.getParent().getId());                
            } catch (Exception e){
            	parent.setId(0L);
            }
            
            com.setId(c.getId());
            com.setUser(user);
            com.setContent(c.getContent());
            com.setDate(c.getDate());
            com.setResponsesNumber(c.getResponsesNumber());           
            com.setArticle(article);            
            com.setParent(parent);
            //com.setState(state);            
            
            comments.add(com);
        }
        
        return comments;
	}

	@Override
	public List<Comment> getArticleComments(long articleID, int limit, int pageNO, String sortOrder) {
		
		List<Comment> comments = new ArrayList<Comment>();		
		Criteria criteria = openSession().createCriteria(Comment.class);			
			criteria.add(Restrictions.eq("article.id", articleID));
			criteria.add(Restrictions.eq("state.id", 2L));
			criteria.setFirstResult(limit*(pageNO-1));
			criteria.setMaxResults(limit);
			if(sortOrder.equals("DESC")){
				criteria.addOrder(Order.desc("id"));	
			} else {
				criteria.addOrder(Order.asc("id"));
			}
			
		List<Comment> coms = criteria.list();
			
		
        for(Comment c : coms) {

        	Comment com = new Comment();
            
            User user = new User();
            user.setId(c.getUser().getId());
            user.setDateOfRegistration(null);
            
            //CommentState state = new CommentState();
            //state.setId(c.getState().getId());
            
            Article article = new Article();
            article.setId(c.getArticle().getId());
            
            Comment parent = new Comment();
            try{            	
                parent.setId(c.getParent().getId());
            } catch (Exception e){
            	parent.setId(0L);
            }
            
            com.setId(c.getId());
            com.setUser(user);
            com.setContent(c.getContent());
            com.setDate(c.getDate());
            com.setResponsesNumber(c.getResponsesNumber());           
            com.setArticle(article);            
            com.setParent(parent);
            com.setState(null);            
            
            comments.add(com);
        }        
        return comments;   
	}
	
}
