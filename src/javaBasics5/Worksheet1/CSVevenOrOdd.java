package javaBasics5.Worksheet1;

import java.util.*;
import java.util.stream.Stream;

import java.util.stream.Collectors;
public class CSVevenOrOdd {

	public static String getString(List<Integer> list) {
		return list.stream()
				  .map(i -> i % 2 == 0 ? "e" + i : "o" + i)
				  .collect(Collectors.joining(","));
	}
	
	public static void main(String[] args) {
		List<Integer> intList = Arrays.asList(1,2,5,6,12,65,45,12,48,78,65,95,87,21);
		System.out.println(getString(intList));

	}

}
