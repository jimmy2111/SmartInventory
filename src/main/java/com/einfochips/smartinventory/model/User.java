package com.einfochips.smartinventory.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class User {
	private String name;
	@Id
	private String email;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="role_name",referencedColumnName = "name")
	private List<RoleEntity> roles;
	
	
	public User() {
		super();
	}
	public User(String name, String email,List<RoleEntity> roles) {
		super();
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
	public List<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + "]";
	}
}
