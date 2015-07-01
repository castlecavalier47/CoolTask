package com.castle.cooltask.model;

import java.util.Date;

public class Task {
	
	public enum State{  
		
	    COMPLETED,SNOOZED, State, OVERDUE
	} 
	
	private String name;
	private String desc;
	private Date startTime;
	private Date endTime;
	private State state;
	

	public Task(String name, String desc, Date startTime, Date endTime, State state) {
		super();
		this.name = name;
		this.desc = desc;
		this.startTime = startTime;
		this.endTime = endTime;
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	
	
	

}
