package com.centroxy.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centroxy.entities.Employee;
import com.centroxy.entities.InterviewerPanel;
import com.centroxy.repositories.EmployeeRepository;
import com.centroxy.repositories.PanelRepository;

@Controller
public class PanelConroller {

	@Autowired
	PanelRepository panelRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	InterviewerPanel interviewerPanel;
	
	public static String removeFirstandLast(String panelFor)
    {
		panelFor = panelFor.substring(1, panelFor.length() - 1);
  
        
        return panelFor;
    }
	
	
	
	
	@PostMapping(value = "/panel")
	@ResponseBody
	public void createPanel(@RequestBody String panelFr){
		
		try
		{
			JsonParser parser = new JsonParser();
			JsonObject JSONObject = parser.parse(panelFr).getAsJsonObject();
			
			interviewerPanel.setId(InterviewerPanel.generateId());
			String panel = JSONObject.get("panelFor").toString();
			panel = removeFirstandLast(panel);
			interviewerPanel.setPanelFor(panel);
			panelRepository.save(interviewerPanel);
			
			
			Iterator<JsonElement> employeeIterator = JSONObject.get("employee").getAsJsonArray().iterator();
			
			
			while(employeeIterator.hasNext()){
				Employee emp = employeeRepository.findById(employeeIterator.next().getAsInt()).get();
				emp.setInterviewerPanel(interviewerPanel);
				employeeRepository.save(emp);
			}
			
			
		}
		catch (JsonParseException e)
		{
			//System.out.println("Error "+e.toString());
		} 
	}
	
	@GetMapping("/panels")
	@ResponseBody
	public ResponseEntity<List<InterviewerPanel>> allPanels(){
		List<InterviewerPanel> panels = panelRepository.findAll();
		return ResponseEntity.ok(panels);
	}

}
