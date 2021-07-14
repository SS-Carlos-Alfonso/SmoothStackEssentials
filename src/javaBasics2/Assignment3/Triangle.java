package javaBasics2.Assignment3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Triangle implements Shape {

	
	static Scanner in = new Scanner(System.in);
	static float base =0;
	static float height =0;
	
	public static double calculateArea() {
		System.out.println("Please enter the Base of the triangle");
		try {
			base = in.nextFloat();
		}catch(InputMismatchException e){
			System.out.println("Please enter only a float or integer");
		}
		System.out.println("Please enter the Height of the triangle");
		try {
			height = in.nextFloat();
		}catch(InputMismatchException e){
			System.out.println("Please enter only a float or integer");
		}
		
		
		double result = 0.5*base*height;
		return result;
	}


	public static void display() {
		
        
        for (int i = 1; i <= (int)base; i++) {
            for (int j = (int)base; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

	}
	
	public static void main(String args[]) {
		
		System.out.println("Total Area is : " + calculateArea());
		display();
	
	}

}
