package com.centroxy.entities;

import java.beans.Transient;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id")
	private int empId;
	private double salary;
	private String name;
	private String gender;
	private String address;
	private String martialStatus;
	private Date dateOfBirth;
	private String empImage;
	private Date joiningDate;
	private String reportsTo;

	private String designation;
	private boolean isAssigned;
	
	private Long mobilleNumber;
	private String emailId;
	
	
	
	@OneToOne
	@JoinColumn(name = "leaveDetailsId")
	private LeaveDetails leaveDetailsId;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "panel_id")
	private InterviewerPanel interviewerPanel;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "project_id")
	private ProjectDetails projectDetails;

	@Transient
	public String getEmployeePicImagePath() {
		if (empImage == null || empId == 0)
			return null;

		return "/employee-pics/" + empId + "/" + empImage;
	}

	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public InterviewerPanel getInterviewerPanel() {
		return interviewerPanel;
	}

	public void setInterviewerPanel(InterviewerPanel interviewerPanel) {
		this.interviewerPanel = interviewerPanel;
	}

	public String getEmpImage() {
		return empImage;
	}

	public void setEmpImage(String empImage) {
		this.empImage = empImage;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee() {
		super();
	}

	public Long getMobilleNumber() {
		return mobilleNumber;
	}

	public void setMobilleNumber(Long mobilleNumber) {
		this.mobilleNumber = mobilleNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", salary=" + salary + ", name=" + name + ", gender=" + gender
				+ ", address=" + address + ", martialStatus=" + martialStatus + ", dateOfBirth=" + dateOfBirth
				+ ", empImage=" + empImage + ", joiningDate=" + joiningDate + ", reportsTo=" + reportsTo
				+ ", designation=" + designation + ", isAssigned=" + isAssigned + ", mobilleNumber=" + mobilleNumber
				+ ", emailId=" + emailId + ", interviewerPanel=" + interviewerPanel + ", projectDetails="
				+ projectDetails + "]";
	}

}