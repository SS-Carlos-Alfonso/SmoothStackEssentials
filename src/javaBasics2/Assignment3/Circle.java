package javaBasics2.Assignment3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Circle implements Shape {
	
	static Scanner in = new Scanner(System.in);
	static float radius =0;
	
	
	public static double calculateArea() {

		System.out.println("Please enter the radius of the circle");
		try {
			radius = in.nextFloat();
		}catch(InputMismatchException e){
			System.out.println("Please enter only a float or integer");
		}
		double result =  (radius*radius) * Math.PI;
		return result;
	}

	
	public static void display() {
		System.out.println("Total Area is : " + calculateArea());
	}
	public static void main(String args[]) {
		display();
	
	}
}
