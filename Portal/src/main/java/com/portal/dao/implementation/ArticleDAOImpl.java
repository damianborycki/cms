package com.portal.dao.implementation;


import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.entity.*;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDAOImpl implements ArticleDAOI {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private List<Article> test(int num, int pageNum){
		List<Article> a = new ArrayList<Article>(num);
		for(int i = 0; i < num; ++i){
			a.add(i, this.getById(pageNum*num+num));
		}
		return a;
	}
	
	private Criteria getCriteria(int limit, int pageNum,
			String sortby, boolean asc){
		Criteria criteria=this.getCurrentSession().createCriteria(Article.class,"c");
		if(asc)
			criteria.addOrder(Order.asc(sortby));
		else
			criteria.addOrder(Order.desc(sortby));
		criteria.setFirstResult(pageNum*limit);
		criteria.setMaxResults(limit);
		return criteria;
	}
	
	@Override
	public List<Article> get(int num, int pageNum, String sortBy,
			boolean ascOrder) {
		Criteria c = this.getCriteria(num, pageNum, sortBy, ascOrder);
		return c.list();
	}

	@Override
	public List<Article> get(int num, int pageNum, String sortBy,
			boolean ascOrder, Category category) {
		Criteria c = this.getCriteria(num, pageNum, sortBy, ascOrder);
		c.add(Restrictions.idEq(category));
		return c.list();
	}

	@Override
	public List<Article> get(int num, int pageNum, String sortBy,
			boolean ascOrder, Category category, Tag tag) {
		Criteria c = this.getCriteria(num, pageNum, sortBy, ascOrder);
		c.add(Restrictions.idEq(category));
		c.add(Restrictions.in("tag", new Tag[]{tag}));
		return c.list();
	}

	@Override
	public Article getById(long id) {
		Article art = (Article) getCurrentSession().load(Article.class, id);
		return art;
	}

	@Override
	public void create(String title, Category category_id, String description,
			String content, User user, Date expiration_date,
			Date publication_date, long galery, long image, List<Tag> tags,
			String article_owner, ArticleRank rank) {
		
		Article art = new Article();
		art.setTitle(title);
		art.setCategory_id(category_id);
		art.setDescription(description);
		
		art.setContent(content);
		art.setUser(user);
		art.setExpiration_date(expiration_date);
		
		art.setPublication_date(publication_date);
		art.setGalery(galery);
		art.setImage(image);
		art.setTag(tags);
		
		art.setArticle_owner(article_owner);
		art.setRank(rank);
		
		art.setDate(Calendar.getInstance().getTime());
		
		this.getCurrentSession().save(art);
	}

	@Override
	public void edit(long id, String title, Category category_id,
			String description, String content, User user,
			Date expiration_date, Date publication_date, Long galery,
			Long image, List<Tag> tags, String article_owner, ArticleRank rank) {
		
		Article art = this.getById(id);
		
		art.setTitle(title);
		art.setCategory_id(category_id);
		art.setDescription(description);
		
		art.setContent(content);
		art.setUser(user);
		art.setExpiration_date(expiration_date);
		
		art.setPublication_date(publication_date);
		art.setGalery(galery);
		art.setImage(image);
		art.setTag(tags);
		
		art.setArticle_owner(article_owner);
		art.setRank(rank);
		this.getCurrentSession().merge(art);
		
	}

}
