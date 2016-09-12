package com.mindtree.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mid;
	private String emailID;
	private Date joinDate;
	private String name;
	private List<Event> events=new ArrayList<Event>();

	public Employee() {
	}


	@Id
	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}


	@Column(name="Email_ID")
	public String getEmailID() {
		return this.emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


	@Temporal(TemporalType.DATE)
	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-many association to Event
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="MID")
	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public void addEvent(Event event){
		this.events.add(event);
	}


	public Employee(String mid, String emailID, Date joinDate, String name) {
		super();
		this.mid = mid;
		this.emailID = emailID;
		this.joinDate = joinDate;
		this.name = name;
		
	}


	@Override
	public String toString() {
		return "Employee [mid=" + mid + ", emailID=" + emailID + ", joinDate=" + joinDate + ", name=" + name
				+ ", events=" + events + "]";
	}

}