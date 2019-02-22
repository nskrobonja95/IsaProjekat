package edu.ftn.isa.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="users")
public @Data class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userID")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@Column(name="city")
	private String city;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name="passwordChanged")
	private boolean passwordChanged;
	
	@Column(name="verificationToken")
	private String verificationToken;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "friends")
	private Set<Friends> friends = new HashSet<Friends>();
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "persons")
    private Set<Friends> persons = new HashSet<Friends>();
    
}
