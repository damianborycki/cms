/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.portal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michal
 */
@Entity
@Table(name = "article_rank")
public class ArticleRank {
    
    @Column(name = "id" , unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "weight", unique = true)
    @NotNull
    private Long weight;
    
    @NotNull
    @Column(name = "name")
    private String name;
    
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the weight
     */
    public Long getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Long weight) {
        this.weight = weight;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
