package javaBasics5.Worksheet2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Assignment2thru4 {


public static List<Integer> rightDigit(List<Integer> nums) {
  nums.replaceAll(n -> n % 10);
  return nums;
}
	
public static List<Integer> doubling(List<Integer> nums) {
	  nums.replaceAll(n -> n * 2);
	  return nums;
	}	
	
public static List<String> noX(List<String> strings) {
	  strings.replaceAll(n -> n.replace("x", ""));
	  return strings;
	}

public static void main(String[] args) {
			List<Integer> numList = new ArrayList<Integer>();
			List<String> stringList = new ArrayList<String>();
			
			numList.add(10);
			numList.add(7);
			numList.add(2);
			numList.add(9);
			numList.add(32);
			
			
			stringList.add("ax");
			stringList.add("xxax");
			stringList.add("xxcx");
			stringList.add("x");
			
			System.out.println("List before operations: ");
			System.out.println(Arrays.toString(numList.toArray()));
	      
			System.out.println("List after right digit: ");
			System.out.println(rightDigit(numList));
			
			System.out.println("List after doubling numbers: ");
			System.out.println(doubling(numList)+ "\n");
	
			System.out.println("String List before operations: ");
			System.out.println(Arrays.toString(stringList.toArray()));
			
			System.out.println("List after removing x's : ");
			System.out.println(noX(stringList));
	
	}

}
