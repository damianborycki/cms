package com.portal.dao.implementation;


import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.dao.interfaces.CommentDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.Article;
import com.portal.entity.Comment;
import com.portal.entity.User;

import java.lang.reflect.Field;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
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
	
	@Override
	public Comment getById(long id) {
		return (Comment) this.getCurrentSession().load(Comment.class, id);
	}
	Comment root(Comment comment){
		Comment c = comment;
		while(c.getParent() != null)
			c = this.getById(c.getParent().getId());
		return c;
	}

	@Override
	public List<Comment> get(int limit, int pageNum,
			String sortby, boolean asc) {
		Criteria criteria=this.getCurrentSession().createCriteria(Comment.class);
		if(asc)
			criteria.addOrder(Order.asc(sortby));
		else
			criteria.addOrder(Order.desc(sortby));
		criteria.setFirstResult(pageNum*limit);
		criteria.setMaxResults(limit);
		
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
		// TODO Auto-generated method stub
		return new ArrayList<Comment>();
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
		User u = (User) this.getCurrentSession().load(User.class, userId);
		comment.setUser(u);
		Comment p = this.getById(parent);
		comment.setParent(p);
		Article a = (Article) this.getCurrentSession().load(Article.class, articleId);
		comment.setArticle(a);
		this.getCurrentSession().save(comment);
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
	public List<Comment> children(Comment comment) {
		Criteria criteria=this.getCurrentSession().createCriteria(Comment.class);
		criteria.add(Restrictions.eq("parent", comment));
		return criteria.list();
	}
	

}
