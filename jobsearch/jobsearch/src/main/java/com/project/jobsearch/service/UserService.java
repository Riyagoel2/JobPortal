package com.project.jobsearch.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.jobsearch.dao.CandidateRepository;
import com.project.jobsearch.dao.RoleRepository;
import com.project.jobsearch.model.data.Candidate;
import com.project.jobsearch.model.data.Role;

@Service
public class UserService {

	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
    RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Candidate findUserByUserName(String name) {

		return candidateRepository.findByUsername(name);
	}
	
	
	public Candidate saveUser(Candidate user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));		
		user.setPhoneNumber(user.getPhoneNumber());
		user.setUsername(user.getUsername());
		Role userRole = roleRepository.findByRole("CANDIDATE");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return candidateRepository.save(user);
	}

}
