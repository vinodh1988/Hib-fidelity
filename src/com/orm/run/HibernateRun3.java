package com.orm.run;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.orm.model.Contact;
import com.orm.model.Dept;
import com.orm.model.Emp;
import com.orm.utils.SessionProvider;

public class HibernateRun3 {
  public static void main(String[] args) {
	   SessionFactory sf=SessionProvider.getSessionFactory();
	   Session session=sf.openSession();
	   
	   Transaction t=session.beginTransaction();
	   Dept d=new Dept(2,"Marketing");
	   
	   Emp e1=new Emp(4,"Manish","Indore","Manager");
	   Emp e2=new Emp(5,"Ramesh","Chennai","Clerk");
	   Emp e3=new Emp(6,"Jose","Mumbai","Supervisor");
	   
	   Contact c1=new Contact(4,"12/2","Gandhi Road","8908304834");
	   Contact c2=new Contact(5,"12/3","Main Road","89042204834");
	   Contact c3=new Contact(6,"12/3","Robin Road","9934304834");
	   
	   e1.setDept(d);e2.setDept(d);e3.setDept(d);//many to one
	   e1.setContact(c1);e2.setContact(c2);e3.setContact(c3);
	   
	   Set<Emp> set=new HashSet<Emp>();
	   set.add(e1);set.add(e2);set.add(e3);
	   
	   d.setEmployees(set);//one to many
	   
	   session.update(d);
	   t.commit();
   }
}
