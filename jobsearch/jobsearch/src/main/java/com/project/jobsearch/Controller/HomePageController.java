package com.project.jobsearch.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

	@GetMapping(value = "/")
	public String home() {
		return "index";
	}

	@GetMapping(value = { "/login" })
	public String login() {
		return "login";
	}

}
