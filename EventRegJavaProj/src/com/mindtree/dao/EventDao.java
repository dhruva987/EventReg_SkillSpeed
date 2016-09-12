package com.mindtree.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.mindtree.entity.Event;
import com.mindtree.exceptions.EventNotFoundException;

public class EventDao {

	public void addEvent(Event event, Session session) {

		Transaction tx=null;
		try{

			tx = session.beginTransaction();

			session.save(event);
		}catch(Exception ex){
			System.out.println("Error in persisting the data in database");

		}
		tx.commit();
	


	}

	public Event findEvent(String eventName, Session session) throws EventNotFoundException{

		Transaction tx=null;
		Event event=null;

		tx = session.beginTransaction();

		Criteria cr = session.createCriteria(Event.class);
		cr.add(Restrictions.eq("eventTitle", eventName));
		event=(Event) cr.uniqueResult();
		if(event==null){
			throw new EventNotFoundException("No such event found");
		}
		tx.commit();
		

		return event;
	}


}
