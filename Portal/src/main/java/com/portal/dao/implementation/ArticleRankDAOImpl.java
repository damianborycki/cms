package com.portal.dao.implementation;

import com.portal.dao.interfaces.ArticleRankDAOI;
import com.portal.entity.ArticleRank;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ArticleRankDAOImpl implements ArticleRankDAOI {
    
    @Override
    public List<ArticleRank> findAll() {
        List<ArticleRank> list = new ArrayList<ArticleRank>();
        ArticleRank exampleArticleRank = new ArticleRank();
        exampleArticleRank.setId(0l);
        exampleArticleRank.setDescription("example");
        exampleArticleRank.setName("Best");

        list.add(exampleArticleRank);

        exampleArticleRank = new ArticleRank();
        exampleArticleRank.setId(1l);
        exampleArticleRank.setName("New");
        exampleArticleRank.setDescription("example2");

        list.add(exampleArticleRank);

        return list;
    }

    @Override
    public ArticleRank get(Long id) {
        ArticleRank exampleArticleRank = new ArticleRank();
        exampleArticleRank.setId(0l);
        exampleArticleRank.setDescription("example");
        exampleArticleRank.setName("Best");

        return exampleArticleRank;
    }
    
    @Override
    public void create(ArticleRank tagType) {
        //@TODO
    }

    @Override
    public void edit(Long tagTypeId, ArticleRank template) {
        //@TODO
    }

    @Override
    public void delete(Long id) {
        //@TODO
    }
    
}
