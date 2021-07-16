package javaBasics5.Worksheet2;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Removed the static class denominator 
public class SampleSingleton {

	
	//Added empty constructor
	private SampleSingleton() {
		
	}
	
	
	private static Connection conn = null;
	
	private static SampleSingleton instance = null;
	
	//Added double-checking locking to the singleton
	public static SampleSingleton getInstance() {
		 if (instance == null){
	            synchronized(SampleSingleton.class){
	               if(instance == null){
	            	   instance = new SampleSingleton();
	               }
	            }
	            return instance ;
	        }
		return instance;
	}
	
}
