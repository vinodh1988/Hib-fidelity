package com.orm.run;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.orm.model.Dept;
import com.orm.model.Emp;
import com.orm.utils.SessionProvider;

public class HibernateRun2 {
    public static void main(String[] args) {
    	   SessionFactory sf=SessionProvider.getSessionFactory();
    	   //https://github.com/vinodh1988/Hib-fidelity
    	   Session session=sf.openSession();
    	   
    	   Query q=session.createQuery("from Dept");
    	   List<Dept> list=q.list();//in eager loading
    	   //query for employees done here itself
    	   for(Dept d:list) {
    		   System.out.println(d.getName());
    		   System.out.println("------------------------");
    		   for(Emp x:d.getEmployees())//in lazy loading
    			                    //query for employees only done in this line
    			   System.out.println(x.getName()+" "+x.getCity());
    	   }
    	 /*  Transaction t=session.beginTransaction();
    	   Dept d=new Dept(2,"Marketing");
    	   
    	   Emp e1=new Emp(4,"Manish","Chennai","Manager");
    	   Emp e2=new Emp(5,"Ramesh","Indore","Clerk");
    	   Emp e3=new Emp(6,"Jose","Jaipur","Supervisor");
    	   
    	   e1.setDept(d);e2.setDept(d);e3.setDept(d);//many to one
    	   
    	   Set<Emp> set=new HashSet<Emp>();
    	   set.add(e1);set.add(e2);set.add(e3);
    	   
    	   d.setEmployees(set);//one to many
    	   
    	   session.save(d);
    	   
    	   t.commit();*/
    	   
    	   
	}
}
