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
### Session and Transaction

the main runtime interface between a Java application and Hibernate. This is the central API class abstracting the notion of a persistence service.

The lifecycle of a Session is bounded by the beginning and end of a logical transaction. (Long transactions might span several database transactions.)

The main function of the Session is to offer create, read and delete operations for instances of mapped entity classes. Instances may exist in one of three states:

* transient: never persistent, not associated with any Session
* persistent: associated with a unique Session
* detached: previously persistent, not associated with any Session

Transient instances may be made persistent by calling save(), persist() or saveOrUpdate(). Persistent instances may be made transient by calling delete(). Any instance returned by a get() or load() method is persistent. Detached instances may be made persistent by calling update(), saveOrUpdate(), lock() or replicate(). The state of a transient or detached instance may also be made persistent as a new persistent instance by calling merge().

save() and persist() result in an SQL INSERT, delete() in an SQL DELETE and update() or merge() in an SQL UPDATE. Changes to persistent instances are detected at flush time and also result in an SQL UPDATE. saveOrUpdate() and replicate() result in either an INSERT or an UPDATE.

It is not intended that implementors be threadsafe. Instead each thread/transaction should obtain its own instance from a SessionFactory.

A Session instance is serializable if its persistent classes are serializable.

A typical transaction should use the following idiom:

```

 Session sess = factory.openSession();
 Transaction tx;
 try {
     tx = sess.beginTransaction();
     //do some work
     ...
     tx.commit();
 }
 catch (Exception e) {
     if (tx!=null) tx.rollback();
     throw e;
 }
 finally {
     sess.close();
 }

```

### Query and Hibernate Query Language

An object-oriented representation of a Hibernate query. A Query instance is obtained by calling Session.createQuery(). This interface exposes some extra functionality beyond that provided by Session.iterate() and Session.find():

* a particular page of the result set may be selected by calling setMaxResults(), setFirstResult()
* named query parameters may be used
* the results may be returned as an instance of 

## Hibernate Query Language

The Hibernate ORM framework provides its own query language called Hibernate Query Language or HQL for short. It is very powerful and flexible and has the following characteristics:

### SQL similarity: 

HQL’s syntax is very similar to standard SQL. If you are familiar with SQL then writing HQL would be pretty easy: from SELECT, FROM, ORDERBY to arithmetic expressions and aggregate functions, etc.
### Fully object-oriented: 

HQL doesn’t use real names of table and columns. It uses class and property names instead. HQL can understand inheritance, polymorphism and association.

### Case-insensitive for keywords: 
Like SQL, keywords in HQL are case-insensitive. That means SELECT, select or Select are the same.

### Case-sensitive for Java classes and properties: 
HQL considers case-sensitive names for Java classes and their properties, meaning Person and person are two different objects.

```
Query q=session.createQuery("from Person where name like 'A%'");
List<Person> list=q.list();
```

## Criteria and Criterion

Criteria is a simplified API for retrieving entities by composing Criterion objects. This is a very convenient approach for functionality like "search" screens where there is a variable number of conditions to be placed upon the result set.

The Session is a factory for Criteria. Criterion instances are usually obtained via the factory methods on Restrictions. eg.

```
 List cats = session.createCriteria(Cat.class)
     .add( Restrictions.like("name", "Iz%") )
     .add( Restrictions.gt( "weight", new Float(minWeight) ) )
     .addOrder( Order.asc("age") )
     .list();
```

#### Criterion

An object-oriented representation of a query criterion that may be used as a restriction in a Criteria query. Built-in criterion types are provided by the Restrictions factory class. This interface might be implemented by application classes that define custom restriction criteria.
  
```
  Criteria criteria=session.createCriteria(Person.class);
  Criterion c=Restrictions.like("name","V%");
  List<Person> list=c.list();
```
 ## Association Mappings

Association mappings are one of the key features of JPA and Hibernate. They model the relationship between two database tables as attributes in your domain model. That allows you to easily navigate the associations in your domain model and JPQL or Criteria queries.

JPA and Hibernate support the same associations as you know from your relational database model. You can use:

* one-to-one associations,
* many-to-one associations and
* many-to-many associations.

You can map each of them as a uni- or bidirectional association. That means you can either model them as an attribute on only one of the associated entities or on both. That has no impact on your database mapping, but it defines in which direction you can use the relationship in your domain model and JPQL or Criteria queries

## One to One mapping

One to One relation specifies that an entity(A) is associated to only a single instance of another entity(B). From database perspective, you can assume that if table A has a one to one mapping with table B, then each row of Table B will have a foreign key column which refers to a primary key of Table A.

Consider the following two tables

mysql> desc emp;

| Field | Type        | Null | Key | Default | Extra |
|-------|-------------|------|-----|---------|-------|
| eno   | int(11)     | NO   | PRI | NULL    |       |
| name  | varchar(30) | NO   |     | NULL    |       |
| city  | varchar(30) | NO   |     | NULL    |       |
| desig | varchar(30) | NO   |     | NULL    |       |
| dno   | int(11)     | YES  |     | NULL    |       |
|-------|-------------|------|-----|---------|-------|
5 rows in set (0.59 sec)

mysql> desc contact;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| eno    | int(11)     | NO   | PRI | NULL    |       |
| doorno | varchar(30) | NO   |     | NULL    |       |
| street | varchar(30) | NO   |     | NULL    |       |
| mobile | varchar(15) | NO   |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
4 rows in set (0.01 sec)

