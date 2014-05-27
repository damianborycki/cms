package com.portal.entity;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.sql.Date;
//import java.time.LocalDateTime;

@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Table(name="users")
public class User {
	
	@JsonCreator
	public User(@JsonProperty("login") String login, @JsonProperty("password") String password) {
		this.login = login;
		this.password = password;
	}
	
	public User() {
		
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @Column(name = "login", unique = true, nullable = false, length = 45)
	private String login;
	
    @Column(name = "password", nullable = false, length = 45)
	private String password;
    
    @Column(name = "email", nullable = false, length = 120)
    private String email;
    
    @Column(name = "name", nullable = true, length = 120)
    private String name;
    
    @Column(name = "surname", nullable = true, length = 120)
    private String surname;
    
    @Column(name = "city", nullable = true, length = 120)
    private String city;
    
	@Column(name = "registration_date", nullable = false)
    private Date dateOfRegistration = new Date(System.currentTimeMillis());

    @Column(name = "last_login_date", nullable = true)
    private java.sql.Date dateOfLastLogIn;

    @Column(name = "gender", nullable = true, length = 120)
    private String gender;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "group", nullable = false)
	private Group group;
	
    @Column(name = "avatar", nullable = true)
    private Long avatar;

    @Column(name = "info", nullable = true, length = 200)
    private String info;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Date getDateOfLastLogIn() {
        return dateOfLastLogIn;
    }

    public void setDateOfLastLogIn(Date dateOfLastLogIn) {
        this.dateOfLastLogIn = dateOfLastLogIn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
