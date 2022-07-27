package com.centroxy.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leaves")
public class Leave {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	private Date appliedDate;
	private Date startDate;
	private Date endDate;
	private String leaveType;
	private static String copyTo = "hremail@gmail.com";
	private String reason;
	private Boolean isApproved;
	private String approvedBy;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	 

	public Date getAppliedDate() {
		return appliedDate;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public static String generateId() {
		String id = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		return id;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public static String getCopyTo() {
		return copyTo;
	}

	public static void setCopyTo(String copyTo) {
		Leave.copyTo = copyTo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", appliedDate=" + appliedDate + ", startDate=" + startDate + ", endDate="
				+ endDate + ", leaveType=" + leaveType + ", reason=" + reason + ", isApproved=" + isApproved
				+ ", approvedBy=" + approvedBy + ", employee=" + employee + ", getAppliedDate()=" + getAppliedDate()
				+ ", getLeaveId()=" + getLeaveId() + ", getStartDate()=" + getStartDate() + ", getEndDate()="
				+ getEndDate() + ", getLeaveType()=" + getLeaveType() + ", getReason()=" + getReason()
				+ ", getIsApproved()=" + getIsApproved() + ", getApprovedBy()=" + getApprovedBy() + ", getEmployee()="
				+ getEmployee() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
