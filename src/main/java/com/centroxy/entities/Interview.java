package com.centroxy.entities;

import java.beans.Transient;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Interview {
	
	@Id
	@Column(name="intw_id")
	private String id;
	
	private String candidateName;
	
	private String candidateCV;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch= FetchType.LAZY , optional = true)
	@JoinColumn(name = "jd_id")
	private JobDescription relatedJD;
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch= FetchType.LAZY , optional = true)
	@JoinColumn(name = "panel_id")
	private InterviewerPanel panel;
	
	private Date scheduledDate;
	
	private String status;
	
	@Column(length = 5000) 
	private String comments;
	
	
	@Transient
	public String getCandidateCVPath() {
		if (candidateCV == null || id == null)
			return null;

		return "/candidate-cv/" + id + "/" + candidateCV;
	}
	
	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


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

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateCV() {
		return candidateCV;
	}

	public void setCandidateCV(String candidateCV) {
		this.candidateCV = candidateCV;
	}

	public JobDescription getRelatedJD() {
		return relatedJD;
	}

	public void setRelatedJD(JobDescription relatedJD) {
		//System.out.println(relatedJD);
		this.relatedJD = relatedJD;
	}

	public InterviewerPanel getPanel() {
		return panel;
	}

	public void setPanel(InterviewerPanel panel) {
		this.panel = panel;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Interview [id=" + id + ", candidateName=" + candidateName + ", candidateCV=" + candidateCV
				+ ", relatedJD=" + relatedJD + ", panel=" + panel + ", scheduledDate=" + scheduledDate + ", status="
				+ status + ", comments=" + comments + "]";
	}

	

}
