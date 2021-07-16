package javaBasics5.Worksheet1.BasicLamdas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
	public static void main(String[] args)
    { 
		
		String [] array = {"Volvo", "BMW", "Ford", "Mazda", "Toyota" , "Tesla" , "e-Cars"};
		List<String> arrayList = Arrays.asList("Volvo", "BMW", "Ford", "Mazda", "Toyota" , "Tesla" , "e-Cars");
		
		System.out.println("Sort shortest to longest: ");
		System.out.print(Arrays.toString(array) + " ---> ");
		Arrays.sort(array,(String a, String b) -> a.length() - b.length());
		System.out.println(Arrays.toString(array) + "\n");
		
		System.out.println("Sort longest to shortest: ");
		System.out.print(Arrays.toString(array) + " ---> ");
		Arrays.sort(array,(String a, String b) -> b.length() -a.length());
		System.out.println(Arrays.toString(array) + "\n");
		
		System.out.println("Sort alpha. by first char: ");
		System.out.print(Arrays.toString(array) + " ---> ");
		Arrays.sort(array,(String a, String b) -> a.charAt(0) - b.charAt(0));
		System.out.println(Arrays.toString(array) + "\n");
		
		System.out.println("Sort by strings that contain 'e' first: ");
		System.out.print(Arrays.toString(array) + " ---> ");
		Arrays.sort(array, (s1, s2) -> { if(s1.toLowerCase().contains("e")) return -1; else return 1; });
		System.out.println(Arrays.toString(array) + "\n");     

		array = new String[] {"Volvo", "BMW", "Ford", "Mazda", "Toyota" , "Tesla" , "e-Cars"};
		
		System.out.println("Sort by strings that contain 'e' first: ");
		System.out.print(Arrays.toString(array) + " ---> ");
		Arrays.sort(array, (s1, s2) -> Utils.specialSort(s1,s2));
        System.out.println(Arrays.toString(array) + "\n");    
        

		
    }
	
private static int specialSort(String s1, String s2) {
 
    if(s1.toLowerCase().contains("e"))
        return -1; 
    else
        return 1; 
}
    
    //Helper method to print the array
    private static void printArray(String[] words) {
        for(int i = 0; i < words.length; i++) {
                System.out.print(words[i] + ", ");          
        }   
        System.out.println("\n");
    }
}
