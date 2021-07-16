package javaBasics5.Worksheet1.DateTimeProblems;
import java.time.Month;
import java.time.Year;
import java.time.LocalDate;
import java.time.DateTimeException;

import java.time.temporal.TemporalQuery;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ChronoField;


public class Friday13 implements TemporalQuery<Boolean> {

	  
    public Boolean queryFrom(TemporalAccessor date) {
        
        return ((date.get(ChronoField.DAY_OF_MONTH) == 13) &&
                (date.get(ChronoField.DAY_OF_WEEK) == 5));
    }
	
	public static void main(String[] args) {
		    Month month = null;
		    int day = 0;
	        LocalDate date = null;

	         System.out.println("Enter the month of the date you wanna check");
			 Scanner in = new Scanner(System.in);
			 try {
				 month = Month.valueOf(in.nextLine().toUpperCase());
				 System.out.println("Enter the day of the date you wanna check");
				 day = in.nextInt();
			 }catch (InputMismatchException exe) {
				 System.out.println("Not a proper month/day");
				throw exe;
			 }

	        try {
	        	//Assuming we are checking only in our current year
	            date = Year.now().atMonth(month).atDay(day);
	        } catch (DateTimeException exc) {
	            System.out.printf("%s %s is not a valid date.%n", month, day);
	            throw exc;     
	        }

	        System.out.println(date.query(new Friday13()));

	}

}
