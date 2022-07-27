package com.centroxy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.centroxy.entities.JobDescription;
import com.centroxy.entities.Notification;
import com.centroxy.entities.ProjectDetails;
import com.centroxy.repositories.JDRepository;
import com.centroxy.repositories.NotificationRepository;
import com.centroxy.repositories.ProjectRepository;

@Controller
@RequestMapping("/hr/new")
public class HRControllerNew {
	
	@Autowired
	JDRepository jdRepo;
	

	@Autowired 
	Notification notification;

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		List<ProjectDetails> findAll = projectRepo.findAll();
		model.addAttribute("listOfProject", findAll);

		// used in demands link popup content
		List<JobDescription> allJD = jdRepo.findAll();
		model.addAttribute("jobdescription", allJD);
		
		/* 
		 * Find all the notifications meant for the PM and populate the same
		 * upon login.
		 */
		List<Notification> ceoNotifications = notificationRepository.findByOrderByDateTimeDesc();
		//System.out.println(ceoNotifications);
		model.addAttribute("NotificationsForHR", ceoNotifications);
		
		int noOfNotifications = notificationRepository.countNoOfNotifications();
		model.addAttribute("notificationcount",noOfNotifications);
		return "hr-dashboard-new";
	}
	
}
