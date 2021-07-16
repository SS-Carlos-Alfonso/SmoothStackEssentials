package javaBasics5.Worksheet1.DateTimeProblems;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListMondays {

	public static void main(String[] args) {
			Month month = null;
			
			System.out.println("Enter the month to convert (Please write the full name of the month)");
			 Scanner in = new Scanner(System.in);
			 try {
				 month = Month.valueOf(in.nextLine().toUpperCase());
			 }catch (InputMismatchException exe) {
				 System.out.println("Not a proper month");
				throw exe;
			 }
			 
			 System.out.printf("For the month: %s: %n", month);
			 LocalDate date = Year.now().atMonth(month).atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
			 Month cur = date.getMonth();
			 while(cur == month) {
				 System.out.printf("%s%n", date);
				 date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
				 cur = date.getMonth();
			 }
			 

	}

}
