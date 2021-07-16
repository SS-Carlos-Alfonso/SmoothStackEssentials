package javaBasics5.Worksheet1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreeLetterStrings {
	public static List<String> getStrings(List<String> list) {
		return list.stream()
				  .filter(s -> s.startsWith("a"))
				  .filter(s -> s.length() == 3)
				  .collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		List<String> stringList = Arrays.asList("amazing" , "Wow", "Thats Awesome", "Cool", "Rad", "awe Yeah" , "awe" , "ate" , "evil", "ata");
		System.out.println(getStrings(stringList));

	}
}
