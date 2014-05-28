/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.portal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Michal
 */
@Entity
@Table(name = "articles")
public class Article {
    
    @Column(name = "id" , unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category_id;
    
    @NotNull
    @Column(name = "title")
    private String title;
    
    @NotNull
    @Column(name = "description")
    private String description;
    
    @NotNull
    @Column(name = "content")
    private String content;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    
    @NotNull
    @Column(name = "date")
    private Date date;
    
    @NotNull
    @Column(name = "publication_date")
    private Date publication_date;
    
    @NotNull
    @Column(name = "expiration_date")
    private Date expiration_date;
    
    @Column(name = "galery", nullable = true)
    private Long galery;

    @NotNull
    @OneToMany
    @JoinColumn(name = "tag")
    private List<Tag> tag;

    @Column(name = "article_owner", nullable = true)
    private String article_owner;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "rank")
    private ArticleRank rank;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="comment_list", 
                joinColumns={@JoinColumn(name="user_id")}, 
                inverseJoinColumns={@JoinColumn(name="id")})
    @Column(name = "comments", nullable = true)
    private List <Comment> comments;
    
    @NotNull
    @Column(name = "image")
    private Long image;
    
    @NotNull
    @Column(name = "views", columnDefinition="default 0")
    private Long views;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Long getGalery() {
        return galery;
    }

    public void setGalery(Long galery) {
        this.galery = galery;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public String getArticle_owner() {
        return article_owner;
    }

    public void setArticle_owner(String article_owner) {
        this.article_owner = article_owner;
    }

    public ArticleRank getRank() {
        return rank;
    }

    public void setRank(ArticleRank rank) {
        this.rank = rank;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
