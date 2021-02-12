package com.project.jobsearch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jobsearch.model.data.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

	

	 

}
