package com.project.jobsearch.model.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class JobPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	public String workSkill;
	public String description;
	public String location;
	public String employername;
	public String phonenumber;
	public long salary;
	public String timePeriod;

}
