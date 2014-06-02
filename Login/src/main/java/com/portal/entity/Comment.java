package com.portal.entity;

//import com.sun.istack.internal.Nullable;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Created by Mateusz on 23.03.14.
 */
@Entity
@Table(name = "komentarze")
public class Comment {

    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotNull
    @OneToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

//    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

//    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

//    @NotNull
    @OneToOne
    @JoinColumn(name = "state", nullable = false)
    private CommentState state;

//    @Nullable
    @Column(name = "parent", nullable = true)
    private Long parent;

//    @NotNull
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

    public CommentState getStan() {
        return state;
    }

    public void setStan(CommentState stan) {
        this.state = state;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getResponsesNumber() {
        return responsesNumber;
    }

    public void setResponsesNumber(Long responsesNumber) {
        this.responsesNumber = responsesNumber;
    }
}
