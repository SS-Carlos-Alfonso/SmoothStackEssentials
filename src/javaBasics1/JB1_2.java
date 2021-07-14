package javaBasics1;
import java.lang.*;
import java.util.*;

public class JB1_2 {
	public static void main(String args[]) {
		
		int max = 100;
	    int min = 1;
	    int range = max - min + 1;
		int rand = (int)(Math.random() * range) + min;
		Scanner myScan = new Scanner(System.in);
		int numberOfAttempts = 1;
		
		while(numberOfAttempts <= 5) {
		
			System.out.println("Please Guess a number between 1-100");
			String userInput = myScan.nextLine();
			int number = Integer.parseInt(userInput);
			
			if(number - rand < 0) {
				if(rand - number <= 10) {
					System.out.println("Congratulations you won the correct answer was:");
					System.out.println(rand);
					break;
				}else {
					System.out.println("Incorrect attempt please try again");
					numberOfAttempts++;
				}
			}else if(number - rand > 0){
				if(number - rand <= 10) {
					System.out.println("Congratulations you won the correct answer was:");
					System.out.println(rand);
					break;
				}else {
					System.out.println("Incorrect attempt please try again");
					numberOfAttempts++;
					}
		}
			if(numberOfAttempts == 6) {
				System.out.println("Sorry you lost please try again");
				System.out.print("The answer was: ");
				System.out.print(rand);
			}
		}
		
		
	}
}
