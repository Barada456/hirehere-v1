package com.centroxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.centroxy.entities.Leave;
import com.centroxy.repositories.LeaveRepository;
@Controller
public class LeaveController {
	@Autowired
	LeaveRepository leaveRepo;
	
	@GetMapping("/applyLeave")
	public String showLeaveForm(Model model) {
		model.addAttribute("LeaveDetails",new Leave());
	
		return "leave-form";
	}
	
	@GetMapping()
	public String takeLeave() {
		return null;
	}

}
