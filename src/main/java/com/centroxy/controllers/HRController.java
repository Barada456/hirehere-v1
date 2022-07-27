package com.centroxy.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.centroxy.entities.Employee;
import com.centroxy.entities.Interview;
import com.centroxy.entities.InterviewerPanel;
import com.centroxy.entities.JobDescription;
import com.centroxy.entities.Notification;
import com.centroxy.entities.ProjectDetails;
import com.centroxy.repositories.EmployeeRepository;
import com.centroxy.repositories.InterviewRepository;
import com.centroxy.repositories.JDRepository;
import com.centroxy.repositories.NotificationRepository;
import com.centroxy.repositories.PanelRepository;
import com.centroxy.repositories.ProjectRepository;
import com.centroxy.utils.FileUploadUtil;

@Controller
@RequestMapping("/hr")
public class HRController {
	

	@Autowired
	ProjectRepository projectRepo;

	@Autowired
	JDRepository jdRepo;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	EmployeeRepository empRepository;

	@Autowired
	PanelRepository panelRepository;
	
	@Autowired
	InterviewRepository interviewRepository;

	@GetMapping("/dashboard")
	public String showHRDashboard(Model model) {
		List<ProjectDetails> findAll = projectRepo.findAll();
		model.addAttribute("listOfProject", findAll);
		List<JobDescription> allJd = jdRepo.findAll();
		model.addAttribute("jobdescription", allJd);

		List<Notification> hrNotifications = notificationRepository.findByOrderByDateTimeDesc();
		model.addAttribute("NotificationsForHR", hrNotifications);

		List<Employee> allEmployee = empRepository.findAll();
		model.addAttribute("allemployee", allEmployee);

		List<InterviewerPanel> panels = panelRepository.findAll();
		model.addAttribute("panels", panels);
		
		List<Interview> interviews = interviewRepository.findAll();
		List<Interview> interviewsWithScheduledStatus = new ArrayList<Interview>();
		List<Interview> interviewsWithUpdatedStatus = new ArrayList<Interview>();
		interviews.stream()
        .forEach(
                interview -> {
                    if (interview.getStatus().equalsIgnoreCase("scheduled")) {
                    	interviewsWithScheduledStatus.add(interview);
                    } else {
                    	interviewsWithUpdatedStatus.add(interview);
                    }
                }
        );
		
		model.addAttribute("interviewsScheduled",interviewsWithScheduledStatus );
		model.addAttribute("interviewsUpdated",interviewsWithUpdatedStatus );
		
		int noOfNotifications = notificationRepository.countNoOfNotifications();
		model.addAttribute("notificationcount",noOfNotifications);
		return "hr-dashboard";
	}
	
	
	@GetMapping("/schedule")
	public String schedule(Model model) {
		List<ProjectDetails> findAll = projectRepo.findAll();
		model.addAttribute("listOfProject", findAll);
		
		List<JobDescription> allJd = jdRepo.findAll();
		model.addAttribute("jobdescription", allJd);

		List<Notification> hrNotifications = notificationRepository.findByOrderByDateTimeDesc();
		model.addAttribute("NotificationsForHR", hrNotifications);

		List<Employee> allEmployee = empRepository.findAll();
		model.addAttribute("allemployee", allEmployee);
		
		
		
		model.addAttribute("interviewDetails",new Interview());
		int noOfNotifications = notificationRepository.countNoOfNotifications();
		model.addAttribute("notificationcount",noOfNotifications);
		
		return "schedule";
	}

	

	@GetMapping("/calender")
	public String showCalender(Model model) {
		model.addAttribute("interviewDetails",new Interview());
		return "calender";
	}
	
	@PostMapping("/schedule")
	@ResponseBody
	public void scheduleInterview(@ModelAttribute Interview interview ,@RequestParam("CV") MultipartFile multipartFile,@RequestParam("jdid") String jdId ,@RequestParam("hiddden-panel") String panelId)  throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		fileName=fileName.replace(" ", "");
		interview.setRelatedJD(jdRepo.findById(jdId).get());
		interview.setId(Interview.generateId());
		interview.setCandidateCV(fileName);
		interview.setPanel(panelRepository.findById(panelId).get());
		interview.setStatus("scheduled");
		Interview scheduledInterview = interviewRepository.save(interview);
		String uploadDir = "candidate-cv/" + scheduledInterview.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	}
	
	
}
