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
	    double dist;
	    for (int i = 0; i <= 2 * radius; i++) {
	    for (int j = 0; j <= 2 * radius; j++) {
	        dist = Math.sqrt((i - radius) * (i - radius) +
	                         (j - radius) * (j - radius));
	        if (dist > radius - 0.5 && dist < radius + 0.5)
	        System.out.print("*");
	        else
	        System.out.print(" ");
	    }
	 
	    System.out.print("\n");
	    }

	}
	public static void main(String args[]) {
		
		System.out.println("Total Area is : " + calculateArea());
		display();
	
	}
}
