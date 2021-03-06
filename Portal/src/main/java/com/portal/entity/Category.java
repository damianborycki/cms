package com.portal.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * User: Bartosz
 * Date: 18.05.14
 * Time: 21:04
 */

@Entity
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Table(name = "categories")
public class Category {
	
	@JsonCreator
    public Category(@JsonProperty("name") String name, 
    			@JsonProperty("description") String descr,
    			@JsonProperty("parentId") Long parentId ) {
		
        this.name = name;
        this.description = descr;
        
        if (parentId != null) {
	        Category c = new Category();
	        c.setId(parentId);
	        this.parent = c;
        }
        
    }
	
	public Category() { }

    @Column(name = "id", unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @JsonBackReference("parent")
    @ManyToOne
    @JoinColumn(name = "parent")
    public Category parent;

    @JsonManagedReference("parent")
    @OneToMany(mappedBy="parent", cascade = CascadeType.ALL)
    public List<Category> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public void fromTemplate(Category template){
        if (template.getName() != null){
            setName(template.getName());
        }

        if (template.getDescription() != null){
            setDescription(template.getDescription());
        }

        setParent(template.getParent());

        if (template.getChildren() != null){
            setChildren(template.getChildren());
        }
    }
}
