package com.project.jobsearch.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.jobsearch.dao.CandidateRepository;
import com.project.jobsearch.model.data.Candidate;
import com.project.jobsearch.service.UserService;

@Controller
@RequestMapping("/register")
public class CandidateRegisterController {

	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
	UserService userService;

	@GetMapping
	public String register(Candidate candidate) {
		return "candidateregister";
	}

	@PostMapping
	public String register(@Valid Candidate candidate, BindingResult bindingResult, Model model) {

		Candidate userExists = userService.findUserByUserName(candidate.getUsername());
		if (bindingResult.hasErrors()) {
			return "candidateregister";
		}
		if (userExists != null) {
			model.addAttribute("userExists", "userExist");
			return "candidateregister";

		}

		if (!candidate.getPassword().equals(candidate.getConfirmPassword())) {
			model.addAttribute("passwordMatchProblem", "Passwords do not match");
			return "candidateregister";
		}

		userService.saveUser(candidate);
		return "redirect:/login";
	}

}
