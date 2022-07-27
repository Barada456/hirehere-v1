package com.centroxy.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centroxy.entities.Interview;
import com.centroxy.repositories.InterviewRepository;
import com.centroxy.repositories.PanelRepository;

@Controller
@RequestMapping("/interviewer")
public class InterviewerController {
	Boolean controlFlag = false;
	
	@Autowired
	InterviewRepository interviewRepository;
	
	@Autowired
	PanelRepository panelRepository;

	@GetMapping("/dashboard")
	public String showInterviewerDashboard() {
		return "interviewer-dashboard";
	}

	@GetMapping("/interviews/{id}")
	public String allPanelss(Model model, @PathVariable String id) {
		List<Interview> interviews = interviewRepository.findByPanelId(id);
		////System.out.println(interviews);
		/*
		 * List<Interview> interviewsScheduled= interviews.stream() .filter(interview ->
		 * interview.getStatus() .equalsIgnoreCase("scheduled"))
		 * .collect(Collectors.toList());
		 */
		
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
		
		model.addAttribute("interviewsScheduled", interviewsWithScheduledStatus);
		model.addAttribute("interviewsUpdated", interviewsWithUpdatedStatus);
		
		model.addAttribute("interviewfeedback",new Interview());
		return "interviews";

	}
	
	@PostMapping("/registerResult")
	@ResponseBody
	public String acceptCandidate(@ModelAttribute Interview interview ,@RequestParam String result) {
		result = result.replace(",", "");
		Interview acceptedInterview = interviewRepository.findById(interview.getId()).get();
		acceptedInterview.setStatus(result);
		acceptedInterview.setComments(interview.getComments());
		interviewRepository.save(acceptedInterview);
		this.controlFlag = true;
		return "Successs";
	}
	
	
	/*
    this method is used to check whether interviewer have closed any open interview,
    if closed,it will be auto updated into hr dashboard closed table card without page refresh
    */
	@GetMapping("/check")
	@ResponseBody
	public ResponseEntity<Boolean> check() {
		Boolean tempFlag=this.controlFlag;
		controlFlag=false;
		return ResponseEntity.ok(tempFlag);
	}

}