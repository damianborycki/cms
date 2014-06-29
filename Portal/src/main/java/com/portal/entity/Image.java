package com.portal.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;
//import java.time.LocalDateTime;

@Entity
@Table(name="image")
public class Image {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getAdd_datetime() {
        return add_datetime;
    }

    public void setAdd_datetime(Date add_datetime) {
        this.add_datetime = add_datetime;
    }

    public Long getAdd_usr() {
        return add_usr;
    }

    public void setAdd_usr(Long add_usr) {
        this.add_usr = add_usr;
    }

    public Date getApp_datetime() {
        return app_datetime;
    }

    public void setApp_datetime(Date app_datetime) {
        this.app_datetime = app_datetime;
    }

    public Long getApp_usr() {
        return app_usr;
    }

    public void setApp_usr(Long app_usr) {
        this.app_usr = app_usr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
 //   @GeneratedValue(strategy = GenerationType.AUTO)
    private String id = new String(new BigInteger(130, new SecureRandom()).toString(32));

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "author", nullable = true, length = 100)
    private String author;

    // TODO: uncomment after Tag is added
    //@Column(name = "tags", nullable = false)
    //private List<Tag> tags;

    @Column(name = "add_datetime", nullable = false)
    private Date add_datetime = new Date(System.currentTimeMillis());

    @Column(name = "add_usr", nullable = false)
    private Long add_usr;

    @Column(name = "app_datetime")
    private Date app_datetime;

    @Column(name = "app_usr")
    private Long app_usr;

    @Column(name = "type", nullable = false, length = 10)
    private String type;

    @Column(name = "width", nullable = false)
    private Long width;

    @Column(name = "height", nullable = false)
    private Long height;

    @Column(name = "link", nullable = false)
    private String link;


}
