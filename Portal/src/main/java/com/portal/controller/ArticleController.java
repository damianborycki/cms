package com.portal.controller;

import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.entity.Article;
import com.portal.entity.ArticleRank;
import com.portal.entity.Category;
import com.portal.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 2014-06-24.
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleDAOI articleDAO;

    @RequestMapping(value = "/article", method=RequestMethod.POST)
    public void addArticle(@RequestBody Article article, HttpServletResponse response) {
//        articleDAO.create(article.getTitle(),
//                article.getCategory_id(),
//                article.getDescription(),
//                article.getContent(),
//                article.getUser(),
//                article.getExpiration_date(),
//                article.getPublication_date(),
//                article.getGalery(),
//                article.getImage(),
//                article.getTag(),
//                article.getArticle_owner(),
//                article.getRank());
        
        articleDAO.createNew(article);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @RequestMapping(value = "article/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public void put(@RequestBody Article article, HttpServletResponse response) {
        articleDAO.edit(article.getId(),
                article.getTitle(),
                article.getCategory_id(),
                article.getDescription(),
                article.getContent(),
                article.getUser(),
                article.getExpiration_date(),
                article.getPublication_date(),
                article.getGalery(),
                article.getImage(),
                article.getTag(),
                article.getArticle_owner(),
                article.getRank());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value="/articles", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticles(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.getAll();
    }

    @RequestMapping(value="/articlesByTag/{tagIds}", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesByTag(@RequestParam("limit") int limit,
                                                   @RequestParam("pageNo") int pageNO,
                                                   @RequestParam("sortBy") String sortBy,
                                                   @RequestParam("sortOrder") Boolean sortOrder,
                                                   @PathVariable("tagIds") String tagIds,
                                                   HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);

        String[] split = tagIds.split(",");
        List<Tag> tags = new ArrayList<>();

        Tag temp = null;
        for (int i = 0; i< split.length; ++i){
            temp = new Tag();
            temp.setId(Long.parseLong(split[i]));
            tags.add(temp);
        }

        return articleDAO.get(limit, pageNO, sortBy, sortOrder, tags);
    }

    @RequestMapping(value="/articlesByCategory/{categoryID}", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesByCategory(@PathVariable("categoryID") long categoryID,
    													@RequestParam("limit") int limit,
                                                        @RequestParam("pageNo") int pageNO,
                                                        @RequestParam("sortBy") String sortBy,
                                                        @RequestParam("sortOrder") Boolean sortOrder,                                                        
                                                        HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);
        
        List<Article> arts = articleDAO.get(categoryID, limit, pageNO, sortBy, sortOrder);
        
        for(int i = 0; i < arts.size(); i++) {
        	arts.get(i).setComments(null);
        }

        return arts;
    }

    @RequestMapping(value="/articlesByTagAndCategory", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesByTagAndCategory(@RequestParam("limit") int limit,
                                                        @RequestParam("pageNo") int pageNO,
                                                        @RequestParam("sortBy") String sortBy,
                                                        @RequestParam("sortOrder") Boolean sortOrder,
                                                        @RequestBody Tag tag,
                                                        @RequestBody Category category,
                                                        HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);

        return articleDAO.get(limit, pageNO, sortBy, sortOrder, category, tag);
    }

    @RequestMapping(value="/articlesByRank/{rankId}", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesByRank(@RequestParam("limit") int limit,
                                                                   @RequestParam("pageNo") int pageNO,
                                                                   @RequestParam("sortBy") String sortBy,
                                                                   @RequestParam("sortOrder") Boolean sortOrder,
                                                                   @PathVariable("rankId") Long rankId,
                                                                   HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);

        ArticleRank articleRank = new ArticleRank();
        articleRank.setId(rankId);
        
        List<Article> arts = articleDAO.get(limit, pageNO, sortBy, sortOrder, articleRank);
        
        for(int i = 0; i < arts.size(); i++) {
        	arts.get(i).setComments(null);
        }

        return arts;
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
         public @ResponseBody
         Article getArticle(@PathVariable("id") long id, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.getById(id);
    }

    @RequestMapping(value = "/articleCount", method = RequestMethod.GET)
    public @ResponseBody Long getArticleCount(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.countAll();
    }
}
