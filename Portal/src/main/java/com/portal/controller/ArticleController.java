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
        articleDAO.create(article.getTitle(),
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

    @RequestMapping(value="/articlesByTag", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesByTag(@RequestParam("limit") int limit,
                                                   @RequestParam("pageNo") int pageNO,
                                                   @RequestParam("sortBy") String sortBy,
                                                   @RequestParam("sortOrder") Boolean sortOrder,
                                                   @RequestBody Tag tag,
                                                   HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);

        return articleDAO.get(limit, pageNO, sortBy, sortOrder, tag);
    }

    @RequestMapping(value="/articlesByCategory", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesByCategory(@RequestParam("limit") int limit,
                                                        @RequestParam("pageNo") int pageNO,
                                                        @RequestParam("sortBy") String sortBy,
                                                        @RequestParam("sortOrder") Boolean sortOrder,
                                                        @RequestBody Category category,
                                                        HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);

        return articleDAO.get(limit, pageNO, sortBy, sortOrder, category);
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

    @RequestMapping(value="/articlesByRank", method=RequestMethod.GET)
    public @ResponseBody List<Article> getArticlesByRank(@RequestParam("limit") int limit,
                                                                   @RequestParam("pageNo") int pageNO,
                                                                   @RequestParam("sortBy") String sortBy,
                                                                   @RequestParam("sortOrder") Boolean sortOrder,
                                                                   @RequestBody ArticleRank articleRank,
                                                                   HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_OK);

        return articleDAO.get(limit, pageNO, sortBy, sortOrder, articleRank);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Article getArticle(@PathVariable("id") long id, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return articleDAO.getById(id);
    }

}
