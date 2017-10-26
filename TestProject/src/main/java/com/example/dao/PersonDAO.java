package com.example.dao;

import java.util.HashMap;

import com.example.model.Person;

public interface PersonDAO 
{
	Person insertUser(Person person);

	Person signInUser(Person person1);

	HashMap<String, String> getTopGainers();

	HashMap<String, String> getTopLosers();
}
