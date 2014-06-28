package com.portal.entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Table(name="groups")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group {
	
	@JsonCreator
	public Group(@JsonProperty("name") String name) {
		this.name = name;
	}
//	@JsonCreator
//	public Group(Long id, String name, String description) {
//		this.id = id;
//		this.name = name;
//		this.description = description;
//	}
	
	public Group() {}
	
	@Id
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
