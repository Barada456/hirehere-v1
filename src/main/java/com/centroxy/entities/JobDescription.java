package com.centroxy.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job_description")
public  class JobDescription {
	
	
	@Id
	@Column(name = "jd_id")
	private String jdId;
	@Column(columnDefinition = "TIMESTAMP")
	private Timestamp createdDate;
	private String author;
	private String  domain ;
	private String skillSet;
	private	String  experienceInYears;
	private	String   positionFor; 
	private String qualification;
	
	@Column(length = 5555) 
	private String responsibilities;
	
	private String status;
	
	@Column(length = 5000) 
	private String suggestion;
	
	
//	@OneToMany(mappedBy = "job_description", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//	List<Notification> notification = new ArrayList<Notification>();
	
	

//	public List<Notification> getNotification() {
//		return notification;
//	}
//
//	public void setNotification(List<Notification> notification) {
//		this.notification = notification;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public static String generateId() {
		String id = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		return id;
	}
	
	public static Timestamp showDate() {
		
		return Timestamp.from(Instant.now());
		
	}
	
	
	
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	
	public String getJdId() {
		return jdId;
	}

	public void setJdId(String jdId) {
		this.jdId = jdId;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getAuthor() {
		return author;
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public String getExperienceInYears() {
		return experienceInYears;
	}
	public void setExperienceInYears(String experienceInYears) {
		this.experienceInYears = experienceInYears;
	}
	public String getPositionFor() {
		return positionFor;
	}
	public void setPositionFor(String positionFor) {
		this.positionFor = positionFor;
	}

	@Override
	public String toString() {
		return "JobDescription [id=" + jdId + ", createdDate=" + createdDate + ", author=" + author + ", domain=" + domain
				+ ", skillSet=" + skillSet + ", experienceInYears=" + experienceInYears + ", positionFor=" + positionFor
				+ ", qualification=" + qualification + ", responsibilities=" + responsibilities + ", status=" + status
				+ ", suggestion=" + suggestion + "]";
	}


	
	
	
	
		
	

}
