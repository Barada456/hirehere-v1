package com.centroxy.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.centroxy.entities.DurationCalculator;
import com.centroxy.entities.JobDescription;
import com.centroxy.entities.Notification;
import com.centroxy.entities.ProjectDetails;
import com.centroxy.repositories.JDRepository;
import com.centroxy.repositories.NotificationRepository;
import com.centroxy.repositories.ProjectRepository;

@Controller
@RequestMapping("/pm")
public class PMController {
	
	
	@Autowired
	JDRepository jdRepo;

	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	DurationCalculator durationCalculator;

	@GetMapping(path = { "/dashboard/{page}", "/dashboard" })
	public String showPMDashboard(Model model, @PathVariable(value = "page", required = false) Integer page) {
		if (page == null) {
			page = 0;
		}

		Pageable pageble = PageRequest.of(page, 4);
		Page<ProjectDetails> findAll = projectRepo.findAll(pageble);

		List<ProjectDetails> projects = projectRepo.findAll();
		model.addAttribute("projects", projects);

		model.addAttribute("listOfProject", findAll);
		model.addAttribute("currentpage", page);
		model.addAttribute("totalpage", findAll.getTotalPages());

		//List<JobDescription> rejectedJDs = jdRepo.findAllByStatus("Rejected");
		//System.out.println(rejectedJDs.toString());

		/*
		 * Find all the notifications meant for the PM and populate the same upon login.
		 */
		List<Notification> pmNotifications = notificationRepository.findByOrderByDateTimeDesc();
		model.addAttribute("NotificationsForPM", pmNotifications);

		int noOfNotifications = notificationRepository.countNoOfNotifications();
		model.addAttribute("notificationcount", noOfNotifications);

		return "pm-dashboard";
	}

	@GetMapping("/demandResource")
	public String showJDForm(Model model) {
		JobDescription jobDescription = new JobDescription();
		model.addAttribute("JobDescription", jobDescription);
		return "JobDescription";
	}

	@PostMapping("/processResourceDemand")
	public String processRequest(@ModelAttribute JobDescription jd) {


		// to set id
		String generateId = JobDescription.generateId();
		jd.setJdId(generateId);

		// to set date
		Timestamp showDate = JobDescription.showDate();
		jd.setCreatedDate(showDate);

		// JobDescription.setAuthor(JobDescription.getAuthor());

		jd.setAuthor("Sashi Bhusan");
		jd.setStatus("Pending");


		jdRepo.save(jd);

		return "redirect:/pm/dashboard";
	}


}
