package com.demo.utility;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;



   public class ApplicationContextUtils  implements ApplicationContextAware{

	@SuppressWarnings("resource")
	@Override
	public void setApplicationContext(ApplicationContext context)throws BeansException {
	  	context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
	}




	public static ApplicationContext getContext() {
		context=new ClassPathXmlApplicationContext("applicationContext.xml");
		return context;
	}




	public static void setContext(ApplicationContext context) {
		ApplicationContextUtils.context = context;
	}

	public static ApplicationContext context;
	 
	
	
	
}
