package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.example.model.Person;

public class PersonDAOImpl implements PersonDAO 
{
	private JdbcTemplate jdbcTemplate;
	 
    public JdbcTemplate getJdbcTemplate() 
    {
        return jdbcTemplate;
    }
 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) 
    {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public Person insertUser(Person person1) 
	{
		final Person person = new Person();
        String quer = "Insert into Person values('"+person1.getFName()+"','"+person1.getLName()+"','"+person1.getDob()+"','01-JAN-03 05.10.11.317000000','"+person1.getPassword()+"','"+person1.getLogin()+"','"+person1.getState()+"','"+person1.getCity()+"','"+person1.getStreet()+"',"+person1.getZip()+",'"+person1.getSsn()+"')";
        jdbcTemplate.execute(quer);
        return person1; 
	}
	
	@Override
	public Person signInUser(Person person1) 
	{
		final Person person = new Person();
        String quer = "Select * from Person where email = '"+person1.getLogin()+"' and pwd = '" + person1.getPassword() + "'";
        jdbcTemplate.execute(quer);
        return (Person) jdbcTemplate.query(quer, new ResultSetExtractor<Person>() {
        
        	public Person extractData(ResultSet rs) throws SQLException, DataAccessException {
     
            if (rs.next()) 
            {
            	person.setFName(rs.getString(1));
            	person.setLName(rs.getString(2));
            }
            System.out.println(person1.getLogin());
            return person;
       
	}});
   }
	
	@Override
	public HashMap<String, String> getTopGainers() 
	{
		String quer = "SELECT * FROM (SELECT ST.SYMBOL, (ST.OPEN - ST.CLOSE) AS GAIN FROM STOCKTRENDS ST WHERE ST.OPEN - ST.CLOSE > 0  AND ST.TIMESTAMP = '01-MAR-17' ORDER BY GAIN DESC) WHERE ROWNUM <=10";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
	
	@Override
	public HashMap<String, String> getTopLosers() 
	{
		String quer = "SELECT * FROM (SELECT ST.SYMBOL, (ST.OPEN - ST.CLOSE) AS LOSS FROM STOCKTRENDS ST WHERE ST.TIMESTAMP = '01-MAR-17' ORDER BY LOSS) WHERE ROWNUM <=10";
		HashMap<String,String> hm = new HashMap<String,String>();
		
		return (HashMap<String, String>) jdbcTemplate.query(quer, new ResultSetExtractor<HashMap<String,String>>() 
		{
	        
        	public HashMap<String,String> extractData(ResultSet rs) throws SQLException, DataAccessException 
        	{
        		while (rs.next()) 
        		{
        			hm.put(rs.getString(1), rs.getString(2));
        		}
             return hm;
        	}
	});
	}
}
