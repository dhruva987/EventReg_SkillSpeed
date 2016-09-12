package com.mindtree.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mindtree.entity.Employee;
import com.mindtree.exceptions.EmployeeNotFoundException;

public class EmployeeDao {

	public void addEmployee(Employee emp, Session session){

		Transaction tx=null;
		try{
			tx = session.beginTransaction();

			session.save(emp);
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error in persisting the data in database");

		}
		tx.commit();
	


	}
	public Employee findEmployeeByEmployeeID(String empID, Session session) throws EmployeeNotFoundException {

	
		Transaction tx=null;
		Employee employee=null;

		tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("mid", empID));
		employee=(Employee) cr.uniqueResult();
		if(employee==null){
			throw new EmployeeNotFoundException("No such employee found");

		}
		tx.commit();
		
		return employee;


	}
	public void updateEmployee(Employee emp,Session session) {
	
		Transaction tx=null;
		try{
			tx = session.beginTransaction();

			session.update(emp);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		tx.commit();
	


	}
	public List<Employee> getListOfEmployees(Session session) {
		
		Transaction tx=null;
		List<Employee> employeeList=null;

		tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Employee.class);
		employeeList=cr.list();
		
		tx.commit();
		return employeeList;
	}
}
