package com.example.business;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.PersonDAO;
import com.example.model.Person;

public class PersonBean 
{
	@Autowired
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) 
	{
		this.personDAO = personDAO;
	}

	public Person insertUser(Person person) 
	{
		return personDAO.insertUser(person);
	}

	public Person signInUser(Person person) 
	{
		return personDAO.signInUser(person);
	}

	public HashMap<String, String> getTopGainers() 
	{
		// TODO Auto-generated method stub
		return personDAO.getTopGainers();
	}

	public HashMap<String, String> getTopLosers() 
	{
		// TODO Auto-generated method stub
		return personDAO.getTopLosers();
	}


}
