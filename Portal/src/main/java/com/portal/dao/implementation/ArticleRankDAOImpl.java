package com.portal.dao.implementation;

import com.portal.dao.interfaces.ArticleRankDAOI;
import com.portal.entity.ArticleRank;
import java.util.ArrayList;
import java.util.List;

import com.portal.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleRankDAOImpl implements ArticleRankDAOI {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<ArticleRank> findAll() {
        return openSession().createQuery("from ArticleRank").list();
    }

    @Override
    public ArticleRank get(Long id) {
        return (ArticleRank) openSession().get(ArticleRank.class, id);
    }
    
    @Override
    public void create(ArticleRank articleRank) {
        openSession().save(articleRank);
    }

    @Override
    public void edit(Long id, ArticleRank template) {
        Session session = openSession();
        ArticleRank articleRank = (ArticleRank) session.get(ArticleRank.class, id);
        articleRank.setName(template.getName());
        articleRank.setDescription(template.getDescription());
        articleRank.setWeight(template.getWeight());

        session.update(articleRank);
        session.flush();
    }

    @Override
    public void delete(Long id) {
        Session session = openSession();
        ArticleRank articleRank = (ArticleRank) session.get(ArticleRank.class, id);
        session.delete(articleRank);
        session.flush();
    }
    
}
