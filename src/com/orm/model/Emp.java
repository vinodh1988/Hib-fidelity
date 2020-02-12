package com.orm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Emp {
  @Id
    private Integer eno;
  @Column
    private String name;
  @Column 
    private String city;
  @Column
    private String desig;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "dno")
    private Dept dept;
    
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}
public Emp(Integer eno, String name, String city, String desig) {
	super();
	this.eno = eno;
	this.name = name;
	this.city = city;
	this.desig = desig;
}


public Dept getDept() {
	return dept;
}
public void setDept(Dept dept) {
	this.dept = dept;
}
public Integer getEno() {
	return eno;
}
public void setEno(Integer eno) {
	this.eno = eno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getDesig() {
	return desig;
}
public void setDesig(String desig) {
	this.desig = desig;
}
  
  
}
