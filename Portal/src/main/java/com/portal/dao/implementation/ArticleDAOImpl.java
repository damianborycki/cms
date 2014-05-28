package com.portal.dao.implementation;


import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.entity.*;

import java.util.*;

public class ArticleDAOImpl implements ArticleDAOI {
	
	private List<Article> test(int num, int pageNum){
		List<Article> a = new ArrayList<Article>(num);
		for(int i = 0; i < num; ++i){
			a.add(i, this.getById(pageNum*num+num));
		}
		return a;
	}
	@Override
	public List<Article> get(int num, int pageNum, Comparator<Article> cmp,
			boolean ascOrder) {
		// TODO 
		return test( num,  pageNum);
	}

	@Override
	public List<Article> get(int num, int pageNum, Comparator<Article> cmp,
			boolean ascOrder, Category category) {
		// TODO 
		return test( num,  pageNum);
	}

	@Override
	public List<Article> get(int num, int pageNum, Comparator<Article> cmp,
			boolean ascOrder, Category category, Tag tag) {
		// TODO 
		return test( num,  pageNum);
	}

	@Override
	public Article getById(long id) {
		Article art = new Article();
		art.setId(id);
		art.setTitle("Example article nr: " +id);
		art.setCategory_id(new Category());
		art.setArticle_owner("Jan Kowalski");
		art.setContent(id+ " justo datelibero \norci nibh fusce, himenaeos volutpat etiam vehicula. Turpis id hendrerit cursus sit \nelit phasellus netus, sapien velit sit ");
		art.setDate(Calendar.getInstance().getTime());
		art.setDescription("Test article nr: " + id);
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
		
		// TODO save it
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
		// TODO save it
		
	}

}
