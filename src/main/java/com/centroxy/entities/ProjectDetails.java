
package com.centroxy.entities;

import java.beans.Transient;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_details")
public class ProjectDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private int id;
	private String name;
	private String location;
	@Column(columnDefinition = "TIMESTAMP")
	private Timestamp createdDate;
	private Date startDate;
	private Date dueEndDate;
	private String projectPic;
	
//	@OneToMany(mappedBy = "project_details", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//	List<Notification> notification = new ArrayList<Notification>();
	
	
	
	
	
	
	
	@Transient
	public String getProjectPicImagePath() {
		if (projectPic == null || id == 0)
			return null;

		return "/project-pics/" + id + "/" + projectPic;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp date) {
		this.createdDate = date;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueEndDate() {
		return dueEndDate;
	}

	public void setDueEndDate(Date dueEndDate) {
		this.dueEndDate = dueEndDate;
	}

	public String getProjectPic() {
		return projectPic;
	}

	public void setProjectPic(String projectPic) {
		this.projectPic = projectPic;
	}


//	public List<Notification> getNotification() {
//		return notification;
//	}
//
//
//	public void setNotification(List<Notification> notification) {
//		this.notification = notification;
//	}
//
//
//	@Override
//	public String toString() {
//		return "ProjectDetails [id=" + id + ", name=" + name + ", location=" + location + ", createdDate=" + createdDate
//				+ ", startDate=" + startDate + ", dueEndDate=" + dueEndDate + ", projectPic=" + projectPic
//				+ ", notification=" + notification + "]";
//	}
//	
	
	
	
}
