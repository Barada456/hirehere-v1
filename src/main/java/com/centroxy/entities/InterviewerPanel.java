package com.centroxy.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;



@Entity
@Component
public class InterviewerPanel {
	
	
	@Id
	@Column(name = "panel_id")
	private String id;
	
    private String panelFor;
    
   
	public static String generateId() {
		String id = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		return id;
	}


	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getPanelFor() {
		return panelFor;
	}


	public void setPanelFor(String panelFor) {
		this.panelFor = panelFor;
	}
	
	




	public InterviewerPanel(String id, String panelFor, String employee) {
		super();
		this.id = id;
		this.panelFor = panelFor;
		
	}


	public InterviewerPanel() {
		
	}
	//to String Method


	@Override
	public String toString() {
		return "InterviewerPanel [id=" + id + ", panelFor=" + panelFor + "]";
	}

	
	
}
