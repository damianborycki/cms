package com.portal.dao.interfaces;


import com.portal.entity.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface ArticleDAOI {

    List<Article> getAll();

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder);

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder,
                      ArticleRank articleRank);

    List<Article> get(long categoryID,
    				  int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder);

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder,
                      List<Tag> tags);

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder,
                      Category category,
                      Tag tag);

    Article getById(long id);

    void create(String title,
                Category category_id,
                String description,
                String content,
                User user,
                Date expiration_date,
                Date publication_date,
                long galery,
                String image,
                List<Tag> tags,
                String article_owner,
                ArticleRank rank);

    void edit(long id,
              String title,
              Category category_id,
              String description,
              String content,
              User user,
              Date expiration_date,
              Date publication_date,
              Long galery,
              String image,
              List<Tag> tags,
              String article_owner,
              ArticleRank rank);
    
    void createNew(Article a);

    Long countAll();

    boolean existsForCategory(Category category);
}
