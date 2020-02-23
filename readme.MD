# Object relational mapping

Object-relational mapping (ORM) is a mechanism that makes it possible to address, access and manipulate objects without having to consider how those objects relate to their data sources. ORM lets programmers maintain a consistent view of objects over time, even as the sources that deliver them, the sinks that receive them and the applications that access them change.

Based on abstraction, ORM manages the mapping details between a set of objects and underlying relational databases

# Hibernate

Object-Relational-Mapping tool basically takes away the pain (perceived) of writing SQL. As object-oriented programmers, we tend to think in terms of objects.

* An ORM tool keeps your object model separate from your persistence model, i.e., your java code need not know about your database tables. You write your data modification code in a programming language of your choice and the ORM tool will map that to the database for you.


* In most cases, you don’t have to write much SQL and you really don’t end up writing SQL that caters to a specific database vendor (in the highly unlikely case that you decide to switch databases after writing an application).

* ORMs also take away the repetitive, mind-numbing job of writing code that maps object properties to columns and vice-versa.

## Hibernate framework

Well, an Object-Relational-Mapping tool basically takes away the pain (perceived) of writing SQL. As object-oriented programmers, we tend to think in terms of objects.

An ORM tool keeps your object model separate from your persistence model, i.e., your java code need not know about your database tables. You write your data modification code in a programming language of your choice and the ORM tool will map that to the database for you.

In most cases, you don’t have to write much SQL and you really don’t end up writing SQL that caters to a specific database vendor (in the highly unlikely case that you decide to switch databases after writing an application).

ORMs also take away the repetitive, mind-numbing job of writing code that maps object properties to columns and vice-versa.

# Hibernate  API

### Session Factory

SessionFactory is a crucial interface of Hibernate API. It is coming from org.hibernate.SessionFactory package. The main goal of this interface is to provide the Hibernate Session instances.

In Hibernate, We can create the sessionFactory in the following way

```
package com.orm.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class SessionProvider {
	
	public static SessionFactory getSessionFactory(){
		
		ServiceRegistry serviceRegistry;
		SessionFactory sessionFactory;
		try{
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .getMetadataBuilder()
			    .build();

		sessionFactory = metadata.getSessionFactoryBuilder()
			   
			    .build();
		return sessionFactory;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
```
