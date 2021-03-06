package com.portal.controller;

import com.portal.dao.interfaces.ArticleDAOI;
import com.portal.dao.interfaces.TagDAOI;
import com.portal.dao.interfaces.UserDAOI;
import com.portal.entity.*;
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

    @Autowired
    private UserDAOI userDAO;

    @Autowired
    private TagDAOI tagDAO;

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

        User user = userDAO.getUser(article.getUser().getLogin());
        article.setUser(user);
        article.setArticle_owner(user.getLogin());

        articleDAO.createNew(article);
        long maxArticle = articleDAO.maxArticleId();
        tagDAO.createArticleTag(maxArticle, article.getTag());
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @RequestMapping(value = "article/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public void put(@PathVariable("id") long pasedId, @RequestBody Article article, HttpServletResponse response) {
        articleDAO.edit(pasedId,
                article.getTitle(),
                article.getCategory_id(),
                article.getDescription(),
                article.getContent(),
                article.getExpiration_date(),
                article.getPublication_date(),
                article.getGalery(),
                article.getImage(),
                article.getTag(),
                article.getRank());

        tagDAO.deleteArticleTagByArticleId(pasedId);
        tagDAO.createArticleTag(pasedId, article.getTag());

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value="/articlesAll", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticles(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.getAll();
    }
    
    @RequestMapping(value="/articles", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesAll(@RequestParam("limit") int limit,
            @RequestParam("pageNo") int pageNO,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("sortOrder") Boolean sortOrder,
            HttpServletResponse response) {
    	
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.get(limit, pageNO, sortBy, sortOrder);
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
        
        Article art = articleDAO.getById(id);
        
        art.setComments(null);
        
        return art;
    }

    @RequestMapping(value = "/articleCount", method = RequestMethod.GET)
    public @ResponseBody Long getArticleCount(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.countAll();
    }

    @RequestMapping(value = "/articleCountByArticleRank", method = RequestMethod.GET)
    public @ResponseBody Long getArticleCountByArticleRank(@RequestParam("articleRank") Long articleRank, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.countByArticleRank(articleRank);
    }

    @RequestMapping(value = "/articleCountByCategory", method = RequestMethod.GET)
    public @ResponseBody Long getArticleCountByCategory(@RequestParam("category") Long category, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.countByCategory(category);
    }

    @RequestMapping(value = "/articleCountByTags", method = RequestMethod.GET)
    public @ResponseBody Long getArticleCountByTags(@RequestParam("tags") List<Long> tags, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.countByTags(tags);
    }

    @RequestMapping(value = "/articleCountByCategoryAndTag", method = RequestMethod.GET)
    public @ResponseBody Long getArticleCountByCategoryAndTag(@RequestParam("category") Long category, @RequestParam("tags") List<Long> tags, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.countByCategoryAndTag(category, tags);
    }
}
