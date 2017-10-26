package com.example;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.PersonBean;
import com.example.model.Person;

@RestController

class DemoController2{
	
	@RequestMapping(value = "/registerUser",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public Person secondPage(@RequestBody Person person)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		System.out.println(person.getSsn());
		
		Person cityOut =  p.insertUser(person);
	//	System.out.println(cityOut.getName() + " " + cityOut.getCountry());
		return person;
	}
	
	@RequestMapping(value = "/signInUser",method=RequestMethod.POST, consumes="application/json",produces="application/json")
	public Person signIn(@RequestBody Person person)
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		//System.out.println(person.getSsn());
		
		Person cityOut =  p.signInUser(person);
	    System.out.println(cityOut.getFName() + " " + cityOut.getLName());
		return person;
	}
	
	@RequestMapping(value = "/getTopGainers",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getTopGainers()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getTopGainers();
	}
	
	@RequestMapping(value = "/getTopLosers",method=RequestMethod.POST, produces="application/json")
	public HashMap<String,String> getTopLosers()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext.xml");
		PersonBean p = (PersonBean) applicationContext.getBean("db");
		return p.getTopLosers();
	}
	
}
