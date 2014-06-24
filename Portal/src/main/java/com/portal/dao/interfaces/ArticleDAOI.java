package com.portal.dao.interfaces;


import com.portal.entity.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public interface ArticleDAOI {

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder);

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder,
                      ArticleRank articleRank);

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder,
                      Category category);

    List<Article> get(int num,
                      int pageNum,
                      String sortBy,
                      boolean ascOrder,
                      Tag tag);

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
                long image,
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
              Long image,
              List<Tag> tags,
              String article_owner,
              ArticleRank rank);

}
