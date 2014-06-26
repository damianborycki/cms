package com.portal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;


/**
 * Created by Mateusz on 23.03.14.
 */
@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Table(name = "comments")
public class Comment {
	
	@JsonCreator
	public Comment(@JsonProperty("id") long id,
				   @JsonProperty("state") CommentState state,
				   @JsonProperty("login") String login, 
				   @JsonProperty("content") String content, 
				   @JsonProperty("parent") Long parent, 
				   @JsonProperty("articleId") long articleId) {
		
		User u = new User();
		u.setLogin(login);
		u.setId(1l);
		this.user = u;
		
		this.content = content;
		
		Comment c = new Comment();
		c.setId(parent);
		this.parent = c;
		
		Article a = new Article();
		a.setId(articleId);
		this.article = a;
		
		this.id = id;
		this.state = state;
	}
	
	public Comment() { }

    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull
    @OneToOne
    @JoinColumn(name = "state", nullable = false)
    private CommentState state;
    
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parent;
    
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = true)
    @JsonBackReference
    private Article article;
    
	@NotNull
    @Column(name = "number_of_responses", nullable = false)
    private Long responsesNumber = Long.valueOf(0);
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CommentState getState() {
        return state;
    }

    public void setState(CommentState state) {
        this.state = state;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
    
    public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

    public Long getResponsesNumber() {
        return responsesNumber;
    }

    public void setResponsesNumber(Long responsesNumber) {
        this.responsesNumber = responsesNumber;
    }
    
    public String toString() {
    	return "id: " + this.id + "\n" +
    			"user: " + this.user + "\n" +
    			"content: " + this.content + "\n" +
    			"date: " + this.date + "\n" + 
    			"state: " + this.state + "\n" + 
    			"parent: " + this.parent + "\n" + 
    			"article: " + this.article + "\n" +
    			"responses nr: " + this.responsesNumber;			
    }
}
