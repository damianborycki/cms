package com.portal.controller;

import com.portal.dao.interfaces.ArticleRankDAOI;
import com.portal.entity.ArticleRank;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleRankController {
    @Autowired
    public ArticleRankDAOI articleRankDAO;
    
    @RequestMapping(value="/articleRank", method= RequestMethod.GET)
    @ResponseBody
    public List<ArticleRank> getAll(){
        return articleRankDAO.findAll();
    }

    @RequestMapping(value="/articleRank/{id}", method= RequestMethod.GET)
    @ResponseBody
    public ArticleRank get(@PathVariable Long id){
        return articleRankDAO.get(id);
    }
    
    @RequestMapping(value="articleRank", method= RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> create(@RequestBody ArticleRank articleRank){
        articleRankDAO.create(articleRank);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value="articleRank/{id}", method= RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> edit(@PathVariable Long id, @RequestBody ArticleRank articleRank){
        articleRankDAO.edit(id, articleRank);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="articleRank/{id}", method= RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Object> delete(@PathVariable Long id){
        articleRankDAO.delete(id);
        return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
    }
}
