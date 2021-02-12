package com.project.jobsearch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jobsearch.model.data.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	Candidate findByUsername(String username);

	          

}
