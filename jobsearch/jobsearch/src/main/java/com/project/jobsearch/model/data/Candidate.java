package com.project.jobsearch.model.data;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "candidate")
public class Candidate  {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	public String username;

	public String password;

	@Transient
	public String confirmPassword;

	@Column(name = "phone_number")
	public String phoneNumber;
	
	public long salary;
	public String address;
	public String profession;
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	Set<Role> roles;

	



}
