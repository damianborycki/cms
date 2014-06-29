package com.portal.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

//import java.time.LocalDateTime;

@Entity
@Table(name="gallery")
public class Gallery {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", nullable = true)
    private String description;

    // TODO: uncomment after Tag is added
    //@Column(name = "tags", nullable = false)
    //private List<Tag> tags;

    @Column(name = "add_datetime", nullable = false)
    private Date add_datetime = new Date(System.currentTimeMillis());

    @Column(name = "add_usr")
    private Long add_usr;

}
