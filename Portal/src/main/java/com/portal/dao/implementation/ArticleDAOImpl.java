package com.portal.dao.implementation;


import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAOI {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

    private Session openSession() {
        return sessionFactory.openSession();
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
		Criteria criteria=this.openSession().createCriteria(Article.class,"c");
		if(asc)
			criteria.addOrder(Order.asc(sortby));
		else
			criteria.addOrder(Order.desc(sortby));
		criteria.setFirstResult(pageNum*limit);
		criteria.setMaxResults(limit);
		return criteria;
	}

    @Override
    public List<Article> getAll() {
        
    	List<Article> a = openSession().createQuery("FROM Article").list();
    	for (Article art : a) {
    		art.setComments(null);
    	}
    	
    	return a;
    }

    @Override
	public List<Article> get(int num, int pageNum, String sortBy,
			boolean ascOrder) {
		Criteria c = this.getCriteria(num, pageNum, sortBy, ascOrder);
		return c.list();
	}

    @Override
    public List<Article> get(int num, int pageNum, String sortBy, boolean ascOrder, ArticleRank articleRank) {
        Criteria c = this.getCriteria(num, pageNum, sortBy, ascOrder);
        c.add(Restrictions.in("rank", new ArticleRank[]{articleRank}));
        return c.list();
    }

    @Override
    public List<Article> get(long categoryID, int num, int pageNum, String sortBy,
			boolean ascOrder) {
		
    	List<Article> articles = new ArrayList<Article>();		
		Criteria criteria = openSession().createCriteria(Article.class);			
		criteria.add(Restrictions.eq("category_id.id", categoryID));		
		criteria.setFirstResult(num*(pageNum-1));
		criteria.setMaxResults(num);
		if(ascOrder){
			criteria.addOrder(Order.asc(sortBy));	
		} else {
			criteria.addOrder(Order.desc(sortBy));
		}			
		articles = criteria.list();
		for (Article art : articles) {
			User u = new User();
			u.setLogin(art.getUser().getLogin());
			art.setUser(u);
	    	art.setComments(null);
	    }	
		return articles;
    }

    @Override
    public List<Article> get(int num, int pageNum, String sortBy, boolean ascOrder, List<Tag> tags) {
        Criteria c = this.getCriteria(num, pageNum, sortBy, ascOrder);

        List<Long> tagIds = new ArrayList<>(tags.size());

        for (int i = 0; i < tags.size(); ++i){
            tagIds.add(tags.get(i).getId());
        }

        c.createAlias("tag", "t");
        c.add(Restrictions.in("t.id", tagIds));

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
		Session session = sessionFactory.openSession();
		
		Article art = (Article) session.load(Article.class, id);
		
		System.out.println(art.getViews());
		
		art.setViews(art.getViews()+1);
		session.update(art);
		session.flush();
		
        return art;
	}

	@Override
	public void create(String title, Category category, String description,
			String content, User user, Date expiration_date,
			Date publication_date, long galery, String image, List<Tag> tags,
			String article_owner, ArticleRank rank) {
		
		Article art = new Article();
		art.setTitle(title);
		art.setCategory_id(category);
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
		
		this.openSession().save(art);
	}

	@Override
	public void edit(long id, String title, Category category,
			String description, String content, User user,
			Date expiration_date, Date publication_date, Long galery,
			String image, List<Tag> tags, String article_owner, ArticleRank rank) {
		
		Article art = this.getById(id);
		
		art.setTitle(title);
		art.setCategory_id(category);
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
		this.openSession().merge(art);
		
	}
	
	public void createNew(Article a) {
		openSession().save(a);
	}

    @Override
    public Long countAll() {
        return ((BigInteger) openSession().createSQLQuery("SELECT Count(*) FROM articles").uniqueResult()).longValue();
    }

    @Override
    public boolean existsForCategory(Category category) {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Article.class);
        criteria.add(Restrictions.eq("category_id", category));

        return !criteria.list().isEmpty();
    }

}
