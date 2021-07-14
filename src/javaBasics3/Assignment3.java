package javaBasics3;

import java.io.*;
import java.util.Scanner;

public class Assignment3 {
	static String path = System.getProperty("user.dir") + "\\src\\test3.txt"; 
	private static char searchChar;
	private FileReader hostFile = new FileReader(path);
	
	
	Assignment3(FileReader file , char search) throws IOException{
		this.hostFile = file;
		this.searchChar = search;
	}
	
	
	//Function to read file and return a single searchable string
	private String readFile() throws IOException {
		String holder;
		
		try(BufferedReader bufRead = new BufferedReader(hostFile)){
			StringBuilder sb = new StringBuilder();
			String line = bufRead.readLine();
			
			while(line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = bufRead.readLine();
			}
		
		holder = sb.toString();
		}
		
		return holder;
	}
	
	
	private int countOccurences(String holder) {
		int count = 0;
		
		for(int i = 0; i < holder.length(); i++) {
			if(holder.charAt(i) == searchChar) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		
	    FileReader file01 = new FileReader(path);
	    System.out.println("Hello what character would you like to seach for?");
	    
	    Scanner in = new Scanner(System.in);
	    searchChar = in.next().charAt(0);
	    
	    Assignment3 cl1 = new Assignment3(file01, searchChar);

	    System.out.println(cl1.countOccurences(cl1.readFile()));
	}
}
