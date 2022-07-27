package com.centroxy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leaveDetails")
public class LeaveDetailsController {
	
	@GetMapping("/takeLeave")
	public String takeLeave() {
		return null;
	}

}
