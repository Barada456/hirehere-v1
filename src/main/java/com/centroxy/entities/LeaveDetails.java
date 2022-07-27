package com.centroxy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Leave_Details")
public class LeaveDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveDetailsId;

	private static final int sickLeave=10;            

	private static final int casualLeave = 10;

	private static final int probationLeave = 5;
	
	private static final int WFH = 5;

	private static final int EL = 10;
	

	private static final int compensatoryLeave = 5;
	
	public static int getSickLeave() {
		return sickLeave;
	}

	public static int getCasualLeave() {
		return casualLeave;
	}

	public static int getProbationLeave() {
		return probationLeave;
	}

	public static int getWFH() {
		return WFH;
	}

	public static int getEL() {
		return EL;
	}

	public static int getCompensatoryLeave() {
		return compensatoryLeave;
	}

	/*
	 * @Override public String toString() { return "LeaveDetails []"; }
	 */
	
	
	
	

}
