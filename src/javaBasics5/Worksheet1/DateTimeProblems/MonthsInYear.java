package javaBasics5.Worksheet1.DateTimeProblems;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.DateTimeException;

import java.io.PrintStream;
import java.lang.NumberFormatException;

public class MonthsInYear {
	 public static void main(String[] args) {
		 int year = 0;
		 System.out.println("Enter the year to convert");
		 Scanner in = new Scanner(System.in);
		 try {
			 year = in.nextInt();
		 }catch (InputMismatchException exe) {
			 System.out.println("Not a proper year");
			throw exe;
		 }
		 
		 try {
			 Year test = Year.of(year);
		 }catch(DateTimeException exe) {
			 System.out.println("Invalid year");
			 throw exe;
		 }
		 
		 System.out.printf("For the year %d: %n", year);
		 for(Month month : Month.values()) {
			 YearMonth yearMonth = YearMonth.of(year, month);
			 System.out.printf("%s: %d days ,%n", month, yearMonth.lengthOfMonth());
		 }
		
		 
		 
	 }
}
