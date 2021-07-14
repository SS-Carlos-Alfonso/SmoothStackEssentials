package javaBasics3;

import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.*;

public class Assignment2 {
	 public static void main( String[] args ) {
	      try {
	    	  
	    	  System.out.println("What would you like to append to the text?");
	    	  Scanner input = new Scanner(System.in);
	    	  
	    	  String data = input.nextLine();
	    	 String path = System.getProperty("user.dir") + "\\src\\NAME OF FILE IN WORKING DIRECTORY HERE"; 
	         File f1 = new File(path);
	         if(!f1.exists()) {
	            f1.createNewFile();
	         }

	         try {
	             Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.APPEND);
	         } catch (IOException e) {
	         }
	
	      } catch(IOException e){
	         e.printStackTrace();
	      }
	   }
}
