package javaBasics2.Assignment3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Rectangle implements Shape {

	static Scanner in = new Scanner(System.in);
	static float length =0;
	static float width = 0;
	
	
	public static double calculateArea() {
		
		System.out.println("Please enter the length of the rectangle");
		try {
			length = in.nextFloat();
		}catch(InputMismatchException e){
			System.out.println("Please enter in only a float or integer");
		}
		
		System.out.println("Please enter the width of the rectangle");
		try {
			width = in.nextFloat();
		}catch(InputMismatchException e){
			System.out.println("Please enter only a float or integer");
		}
	
		double result =  length * width;
		return result;
				}

	
	public static void display() {
		System.out.println("Total Area is : " + calculateArea());
	}
	
	public static void main(String args[]) {
	
		display();
	
	}

}
