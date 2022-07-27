package com.centroxy.controllers;

import java.io.IOException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.centroxy.entities.Notification;
import com.centroxy.entities.ProjectDetails;
import com.centroxy.repositories.NotificationRepository;
import com.centroxy.repositories.ProjectRepository;
import com.centroxy.utils.FileUploadUtil;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired 
	Notification notification;
	
	@Autowired
	NotificationRepository notificationRepository;

	@GetMapping("/addNewProject")
	public String showAddNewProjectForm(Model model) {
		model.addAttribute("ProjectDetails", new ProjectDetails());
		return "add-project-form";
	}

	@PostMapping("/addNewProject")
	public String addNewProject(@ModelAttribute ProjectDetails projectDetails,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		projectDetails.setProjectPic(fileName);

		projectDetails.setCreatedDate(Notification.showDate());

		ProjectDetails savedProject = projectRepo.save(projectDetails);


		String uploadDir = "project-pics/" + savedProject.getId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		notification.setId(Notification.generateId());
		notification.setDateTime(Notification.showDate());
		notification.setTriggeredBy("CEO");
		notification.setReceivedBy("PM,HR,EMP");
		notification.setCausedBy("New Project Added");
		notification.setIsRead(false);
		notification.setProjectDetails(savedProject);
		
		notificationRepository.save(notification);


		return "redirect:/ceo/dashboard";

	}

	@GetMapping("/showdetails/{id}")
	public String showProjectDetails(@PathVariable int id, Model model) {
		Optional<ProjectDetails> findById = projectRepo.findById(id);
		//System.out.println(findById.get());
		model.addAttribute("details", findById.get());
		return "projectdetails";
	}

}
