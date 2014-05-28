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
@Table(name = "tag")
public class Tag {
    
    @Column(name = "id" , unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(name = "name")
    private String name;
    
    @Column(name = "description", nullable = true)
    private String description;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "type", nullable = false)
    private TagType type;

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

    public TagType getType() {
        return type;
    }

    public void setType(TagType type) {
        this.type = type;
    }
}
