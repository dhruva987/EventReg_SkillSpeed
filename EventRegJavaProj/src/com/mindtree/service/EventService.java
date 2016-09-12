package com.mindtree.service;

import org.hibernate.Session;

import com.mindtree.dao.EmployeeDao;
import com.mindtree.dao.EventDao;
import com.mindtree.entity.Employee;
import com.mindtree.entity.Event;
import com.mindtree.exceptions.EmployeeNotFoundException;
import com.mindtree.exceptions.EventNotFoundException;

public class EventService {
	
	EventDao eventDao=new EventDao();
	EmployeeDao employeeDao=new EmployeeDao();
	public void addEvent(String eventTitle, String eventDescription, Session session) {
		Event event =new Event();
		event.setDescription(eventDescription);
		event.setEventTitle(eventTitle);
		eventDao.addEvent(event,session);
		System.out.println("Event Persisted Successfully");
	}

	public void registerEventToEmployee(String employeeID, String eventName, Session session) {
		Employee emp = null;
		Event event = null;
		try {
			emp = employeeDao.findEmployeeByEmployeeID(employeeID,session);
	
			event = eventDao.findEvent(eventName,session);
			if((emp !=null )&&(event !=null)){
				emp.addEvent(event);
			}
			
			employeeDao.updateEmployee(emp,session);
			System.out.println("Event is registered to employee successfully");
		} 
		catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (EventNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
	}


}
