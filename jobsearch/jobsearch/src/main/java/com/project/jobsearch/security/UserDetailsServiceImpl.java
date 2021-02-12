package com.project.jobsearch.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.jobsearch.model.data.Candidate;
import com.project.jobsearch.model.data.Role;
import com.project.jobsearch.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Candidate candidate = userService.findUserByUserName(username);
		List<GrantedAuthority> authorities = getUserAuthority(candidate.getRoles());
		return buildUserForAuthentication(candidate, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return new ArrayList<GrantedAuthority>(roles);
	}

	private UserDetails buildUserForAuthentication(Candidate user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, authorities);

	}
}