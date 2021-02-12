package com.project.jobsearch.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.jobsearch.dao.CandidateRepository;
import com.project.jobsearch.dao.JobPostRepository;
import com.project.jobsearch.model.data.Candidate;
import com.project.jobsearch.model.data.JobPost;
import com.project.jobsearch.service.UserService;

@Controller
public class JobController {

	@Autowired
	UserService userService;
	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
	JobPostRepository jobPostRepository;
	

	@GetMapping(value = "/jobs")
	public String jobsAvailable(Model model) {
		List<JobPost> jobPosts = jobPostRepository.findAll();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Candidate user = userService.findUserByUserName(auth.getName());
		model.addAttribute("listOfPosts", jobPosts);
		model.addAttribute("user", user);
		return "jobs";
	}

	@GetMapping(value = "/profile/edit/{id}")
	public String editProfile(@PathVariable("id") int id, Model model)

	{
		Candidate candidate = candidateRepository.findById(id).get();
		model.addAttribute("candidate", candidate);
		return "getprofile";
	}

	@PostMapping("/profile/edit/{id}")
	public String editProfile(@PathVariable("id") int id, @Valid Candidate user, BindingResult bindingresult,
			RedirectAttributes rAttributes, Model model)

	{

		if (bindingresult.hasErrors()) {
			return "getprofile";
		}
		model.addAttribute("user", user);
		rAttributes.addFlashAttribute("message", "Profile edited");
		rAttributes.addFlashAttribute("alertClass", "alert-success");
		user.setUsername(user.getUsername());
		user.setAddress(user.getAddress());
		user.setPhoneNumber(user.getPhoneNumber());
		user.setProfession(user.getProfession());
		user.setSalary(user.getSalary());
		candidateRepository.save(user);
		return "redirect:/jobs";

	}

	@GetMapping(value = "/create-job-post")
	public String createJobPost(JobPost jobPost, Model model) {

		return "createJobPost";
	}

	@PostMapping("/create-job-post")
	public String createJobPost(@Valid JobPost jobPost, BindingResult bindingresult, RedirectAttributes rAttributes,
			Model model) throws IOException {

		if (bindingresult.hasErrors()) {
			return "jobs";
		}

		rAttributes.addFlashAttribute("message", "Job Post added");
		rAttributes.addFlashAttribute("alertClass", "alert-success");
		jobPost.setDescription(jobPost.getDescription());
		jobPost.setEmployername(jobPost.getEmployername());
		jobPost.setLocation(jobPost.getLocation());
		jobPost.setPhonenumber(jobPost.getPhonenumber());
		jobPost.setSalary(jobPost.getSalary());
		jobPost.setWorkSkill(jobPost.getWorkSkill());
		jobPost.setTimePeriod(jobPost.getTimePeriod());

		jobPostRepository.save(jobPost);

		return "redirect:/create-job-post";

	}

	@GetMapping(value = "/search")
	public String search(Model model)

	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Candidate user = userService.findUserByUserName(auth.getName());
		model.addAttribute("user", user);
		return "jobs";
	}

	@PostMapping(value = "/search")
	public String search(@RequestParam String str, RedirectAttributes rAttributes, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Candidate user = userService.findUserByUserName(auth.getName());
		model.addAttribute("user", user);
		List<JobPost> filteredPosts = new ArrayList<JobPost>();
		List<JobPost> jobPosts = jobPostRepository.findAll();
		for (JobPost post : jobPosts) {
			if (post.location.contains(str) || post.description.contains(str) || post.workSkill.contains(str)) {
				filteredPosts.add(post);
			}
		}

		model.addAttribute("filteredPosts", filteredPosts);
		return "jobs";

	}
	
	
	@GetMapping(value = "/profile/view/{id}")
	public String viewProfile(@PathVariable("id") int id, Model model)

	{
		Candidate candidate = candidateRepository.findById(id).get();
		model.addAttribute("user", candidate);
		return "viewprofile";
	}

	

}
