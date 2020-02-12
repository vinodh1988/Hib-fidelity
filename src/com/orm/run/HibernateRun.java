package com.orm.run;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.orm.model.Person;
import com.orm.utils.SessionProvider;

public class HibernateRun {
  public static void main(String[] args) {
	   SessionFactory sf=SessionProvider.getSessionFactory();
	   Session session=sf.openSession();
	   
	  // Query q=session.createQuery("from Person where name like 'A%'");
	  Criteria q=session.createCriteria(Person.class);
	//  Criterion c1=Restrictions.like("name", "A%");
	    Order o= Order.asc("name");
	  q.addOrder(o);
	   List<Person> list=q.list();
	   
	   for(Person x: list)
		   System.out.println(x.getName()+" "+x.getCity());
	   
	  /* Transaction t=session.beginTransaction();
	   Person p=new Person(4,"03824098","Bengaluru");
	   Person p1=new Person(6,"Kevin","Chennai");
	   									//object not part of hibernate
       session.delete(p);
       
       t.commit();*/
	   
       
      //System.out.println("A New record Added...!!");
  }
}
