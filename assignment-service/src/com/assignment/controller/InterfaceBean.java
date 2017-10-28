package com.assignment.controller;

 

 

public class InterfaceBean implements  Comparable<InterfaceBean> {
	
	private String name;
	private int age;
	public InterfaceBean(String string, int i) {
		name = string;
	      age = i;
	}
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
	 

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(InterfaceBean o) {
		// TODO Auto-generated method stub
		return (this.name).compareTo(o.name);
	}

	 

	

}
