package com.centroxy.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@Table(name = "notification")
public class Notification {
	
	@Id
	private String id;
	private String triggeredBy;
	private String receivedBy;
	private String causedBy;
	@Column(columnDefinition = "TIMESTAMP")
	private Timestamp dateTime;
	private Boolean isRead;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch= FetchType.LAZY , optional = true)
	@JoinColumn(name = "project_id")
	private ProjectDetails projectDetails;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch= FetchType.LAZY , optional = true)
	@JoinColumn(name = "jd_id")
	private JobDescription jd;
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch= FetchType.LAZY , optional = true)
	@JoinColumn(name = "emp_id")
	private Employee emp;
	
	
	
	
	
	
	
	
	
	
	public static String generateId() {
		String id = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		return id;
	}
	
	public static Timestamp showDate() {
		
		Timestamp instant= Timestamp.from(Instant.now());
		
		return instant;
		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTriggeredBy() {
		return triggeredBy;
	}
	public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}
	public String getCausedBy() {
		return causedBy;
	}
	public void setCausedBy(String causedBy) {
		this.causedBy = causedBy;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp localDate) {
		this.dateTime = localDate;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	public JobDescription getJd() {
		return jd;
	}

	public void setJd(JobDescription jd) {
		this.jd = jd;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", triggeredBy=" + triggeredBy + ", receivedBy=" + receivedBy + ", causedBy="
				+ causedBy + ", dateTime=" + dateTime + ", isRead=" + isRead + ", projectDetails=" + projectDetails
				+ ", jd=" + jd + ", emp=" + emp + "]";
	}
	
	
	

}
