package com.portal.entity;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Table(name="groups")
public class Group {
	
	@JsonCreator
	public Group(@JsonProperty("group_id") Long id) {
		this.id = id;
	}
	
	public Group() {
		
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
    @Column(name = "description", nullable = true, length = 600)
    private String description;
    
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
	
}
