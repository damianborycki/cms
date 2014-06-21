package com.portal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Mateusz on 2014-05-11.
 */
@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Table(name = "comment_state")
public class CommentState {
	
	@JsonCreator
	public CommentState(@JsonProperty("id") long id) {
		this.id = id;
	}
	
	public CommentState() { }

    @Id
    @NotNull
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    @Size(max = 45)
    private String name;

    @Column(name = "description", nullable = true, length = 600)
    @Size(max = 600)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

}
