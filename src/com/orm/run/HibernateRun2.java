package com.orm.run;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.orm.model.Dept;
import com.orm.model.Emp;
import com.orm.utils.SessionProvider;

public class HibernateRun2 {
    public static void main(String[] args) {
    	   SessionFactory sf=SessionProvider.getSessionFactory();
    	   Session session=sf.openSession();
    	   Transaction t=session.beginTransaction();
    	   Dept d=new Dept(2,"Marketing");
    	   
    	   Emp e1=new Emp(4,"Manish","Chennai","Manager");
    	   Emp e2=new Emp(5,"Ramesh","Indore","Clerk");
    	   Emp e3=new Emp(6,"Jose","Jaipur","Supervisor");
    	   
    	   e1.setDept(d);e2.setDept(d);e3.setDept(d);//many to one
    	   
    	   Set<Emp> set=new HashSet<Emp>();
    	   set.add(e1);set.add(e2);set.add(e3);
    	   
    	   d.setEmployees(set);//one to many
    	   
    	   session.save(d);
    	   
    	   t.commit();
    	   
    	   
	}
}
