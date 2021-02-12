package com.project.jobsearch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jobsearch.model.data.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRole(String string);

}
