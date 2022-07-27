package com.centroxy.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centroxy.entities.JobDescription;
import com.centroxy.entities.Notification;
import com.centroxy.entities.ProjectDetails;
import com.centroxy.repositories.JDRepository;
import com.centroxy.repositories.NotificationRepository;
import com.centroxy.repositories.ProjectRepository;

@Controller
@RequestMapping("/ceo")
public class CEOController {

	@Autowired
	JDRepository jdRepo;
	

	@Autowired 
	Notification notification;

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	NotificationRepository notificationRepository;

	@GetMapping("/dashboard")
	public String showCEODashboard(Model model) {
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
		model.addAttribute("NotificationsForCEO", ceoNotifications);
		
		int noOfNotifications = notificationRepository.countNoOfNotifications();
		model.addAttribute("notificationcount",noOfNotifications);
		return "ceo-dashboard";
	}

	@GetMapping("/showDemands")
	public String showDemands(Model model) {
		List<JobDescription> findAllDemands = jdRepo.findAll();


		model.addAttribute("JDs", findAllDemands);
		return "all-demands";

	}

	@PostMapping("/acceptdemand")
	@ResponseBody
	public JobDescription acceptDemand(@RequestBody String id) {

		Optional<JobDescription> findJDById = jdRepo.findById(id);

		findJDById.get().setStatus("Approved");


		JobDescription savedJD = jdRepo.save(findJDById.get());
		

		notification.setId(Notification.generateId());
		notification.setDateTime(Notification.showDate());
		notification.setTriggeredBy("CEO");
		notification.setReceivedBy("PM,HR");
		notification.setCausedBy("JD Aprroved ");
		notification.setIsRead(false);
		notification.setJd(savedJD);
		
		notificationRepository.save(notification);

		return savedJD;
	}

	@PostMapping("/rejectdemand")
	@ResponseBody
	public JobDescription rejectDemand(@RequestBody String id) {

		Optional<JobDescription> findJDById = jdRepo.findById(id);

		findJDById.get().setStatus("Rejected");

		JobDescription rejectedJd = jdRepo.save(findJDById.get());
		
		notification.setId(Notification.generateId());
		notification.setDateTime(Notification.showDate());
		notification.setTriggeredBy("CEO");
		notification.setReceivedBy("PM,HR");
		notification.setCausedBy("JD Rejected ");
		notification.setIsRead(false);
		notification.setJd(rejectedJd);
		
		notificationRepository.save(notification);

		return rejectedJd;
	}

}
