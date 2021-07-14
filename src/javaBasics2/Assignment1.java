package javaBasics2;

import java.io.*;
import java.util.*;

public class Assignment1 {

public static void main(String args[]) {
		System.out.println("Hello enter a number to add to running total (Integer/Decimal) - 0 will exit");
		
		float floatNum = 0f;
		float userInput = 0f;
		float userInput1 = 0f;
		Scanner myScan = new Scanner(System.in);

		try {
			 userInput = myScan.nextFloat();
		}catch (InputMismatchException e) {
			System.out.println("You must input a integer or float data type - Program now exiting");
		}
	
		
		if(userInput == 0) {
			System.exit(0);
		}
		
		floatNum += userInput;
		boolean exit = false;
		
		while(!exit) {
			System.out.println("Add another number or enter 0 to exit");
			try {
				userInput1 = myScan.nextFloat();
				
			}catch(InputMismatchException e){
				System.out.println("You must input a integer or float data type - Program now exiting");
				break;
			}
		
			if(userInput1 == 0) {
				exit = true;
			}else {
				floatNum += userInput1;
			}	
			System.out.print("Running Total:  ");
			System.out.print(floatNum + "\n");
		}
		
	}
}
