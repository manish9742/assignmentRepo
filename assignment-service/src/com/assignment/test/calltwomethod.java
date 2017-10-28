package com.assignment.test;

import java.util.regex.Pattern;

public class calltwomethod {
	
	public static void main(String[] args) {
		 
		
		String regex = "^[0-3]?[1-9]/[0-3]?[1-9]/(?:[0-9]{2})?[0-9]{2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
		// Pattern p = Pattern.compile("^\\d{4}[-]?\\d{1,2}[-]?\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$"); ^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?
		
		String regex1 = "[^a-zA-Z0-9]";
		
		 Pattern p = Pattern.compile(regex);
	       
		System.out.println(p.matcher("17/02/2015 01:18:15").find());
		
		String st="This+ ? -S & is ## demo '";
		
		//String str="[^a-zA-Z0-9,://>]+";
		
		//System.out.println(st.replaceAll(str, ""));
		              
	}
	
	
	 
}
