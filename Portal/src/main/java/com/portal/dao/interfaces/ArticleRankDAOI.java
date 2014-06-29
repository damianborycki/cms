package com.portal.dao.interfaces;

import com.portal.entity.ArticleRank;
import java.util.List;

public interface ArticleRankDAOI {
    
    public List<ArticleRank> findAll();
    public ArticleRank get(Long id);
    public void create(ArticleRank articleRank);
    public void edit(Long ArticleRankId, ArticleRank template);
    public void delete(Long id);
    
}

