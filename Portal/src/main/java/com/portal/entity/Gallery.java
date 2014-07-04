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

    public Long getAdd_usr() {
        return add_usr;
    }

    public void setAdd_usr(Long add_usr) {
        this.add_usr = add_usr;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany (mappedBy = "id")
    private List<Tag> tags;

    @Column(name = "add_usr")
    private Long add_usr;

    @OneToMany (mappedBy = "gallery_id")
    private List<Image> images;
}
