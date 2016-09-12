package com.mindtree.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mindtree.dao.EmployeeDao;
import com.mindtree.entity.Employee;
import com.mindtree.exceptions.EmployeeNotFoundException;

public class EmployeeService {
	
	EmployeeDao empDao=new EmployeeDao();
	public void addEmployee(String empID,String empName,Date joinDate,String emailID, Session session){
		Employee emp=new Employee(empID, emailID, joinDate, empName);
		empDao.addEmployee(emp,session);
		System.out.println("Employee Persisted Successfully");
	}
	
	public Employee findEmployeeByEmployeeID(String empID,Session session) throws EmployeeNotFoundException{
		Employee emp=empDao.findEmployeeByEmployeeID(empID,session);
		return emp;
	}

	public void showAllEmployees(Session session) {
		List<Employee> employeeList=empDao.getListOfEmployees(session);
		for(Employee emp:employeeList)
		{
			System.out.println(emp.toString());
		}
	}

}
