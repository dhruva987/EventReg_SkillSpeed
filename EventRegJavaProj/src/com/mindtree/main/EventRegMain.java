package com.mindtree.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mindtree.service.EmployeeService;
import com.mindtree.service.EventService;

public class EventRegMain {

	
	private static EmployeeService empService=new EmployeeService();
	private static EventService eventService=new EventService();
	private static Session session;

	/* Setting up Hibernate & create session factory*/
	public static void setup(){
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		session=factory.openSession();
	}
	@SuppressWarnings("deprecation")
	private static void addEmployee(){
		
		System.out.println("Enter Employee ID");
		String employeeID=sc.nextLine();
		System.out.println("Enter Employee Name");
		String employeeName=sc.nextLine();
		System.out.println("Enter JoinDate (MM/dd/yyyy)");
		String joinDate=sc.nextLine();
		System.out.println("Enter Email ID");
		String emailID=sc.nextLine();
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
		Date startDate=null;
		try {
			 startDate= (Date)formatter.parse(joinDate);
		} catch (ParseException e) {
			System.out.println("Unexpected Date format. Please retry");

		} 
		empService.addEmployee(employeeID, employeeName, startDate, emailID,session);
	}
	
	@SuppressWarnings("deprecation")
	private static void addEvent(){
		System.out.println("Enter Event Title");
		String eventTitle=sc.nextLine();
		System.out.println("Enter Event Description");
		String eventDescription=sc.nextLine();

		eventService.addEvent(eventTitle,eventDescription,session);
	}
	
	
	@SuppressWarnings("deprecation")
	private static void registerEventForEmployee(){
		System.out.println("Enter Employee ID");
		String employeeID=sc.nextLine();
		System.out.println("Enter Event Name");
		String eventName=sc.nextLine();

		eventService.registerEventToEmployee(employeeID,eventName,session);
	}
	

	private static Scanner sc=new Scanner(System.in);
	private static void showMenu(){
		System.out.println("Select from the following options");
		System.out.println("-------------------------------------------------");
		System.out.println("1. Add Employee");
		System.out.println("2. Add Event");
		System.out.println("3. Register employees for events");
		System.out.println("4. Display all employees");
		System.out.println("5. Exit");
		System.out.println("-------------------------------------------------");
	}
	
	private static void showAllEmployees(){
		empService.showAllEmployees(session);
	}

	public static void main(String[] args) {
	  
		setup();
		int choice;
		do{
	  	EventRegMain.showMenu();
		choice=Integer.parseInt(sc.nextLine());
		switch(choice){
		case 1: EventRegMain.addEmployee();
		break;
		case 2:EventRegMain.addEvent();
		break;
		case 3:EventRegMain.registerEventForEmployee();
		break;
		case 4:EventRegMain.showAllEmployees();
		break;
		}
		}while(choice !=5);

	}

}
