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
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < width; j++) {
				if (i == 0 || i ==length-1) {
					System.out.print(" - ");
					if(j == width - 1) {
						System.out.println();
					}
				}else if(j == 0){
					System.out.print("|");
				}else if(j == width-1){
					System.out.print("|\n");
				}else {
					System.out.print("    ");
				}
			}
		}

	}
	
	public static void main(String args[]) {
	
		System.out.println("Total Area is : " + calculateArea());
		display();
	
	}

}
