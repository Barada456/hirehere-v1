package com.centroxy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.centroxy.entities.DurationCalculator;
import com.centroxy.entities.Notification;
import com.centroxy.repositories.NotificationRepository;

@Controller
@RequestMapping("/pm/new")
public class PMControllerNew {
	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	DurationCalculator durationCalculator;
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		/*
		 * Find all the notifications meant for the PM and populate the same upon login.
		 */
		List<Notification> pmNotifications = notificationRepository.findByOrderByDateTimeDesc();
		//System.out.println(pmNotifications);
		model.addAttribute("NotificationsForPM", pmNotifications);

		int noOfNotifications = notificationRepository.countNoOfNotifications();
		model.addAttribute("notificationcount", noOfNotifications);
		
		return "pm-dashboard-new";
	}
	@GetMapping("/assignEmployee")
	public String showAssignEmployeeForm(){
		return "assignEmployee";
	}
	
}
