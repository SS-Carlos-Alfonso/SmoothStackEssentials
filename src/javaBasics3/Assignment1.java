package javaBasics3;

import java.io.File;

public class Assignment1 {

	//Recursive program to print out files and directories
	 static void RecursivePrint(File[] arr,int index,int level) 
     {
        
         if(index == arr.length)
             return;
           
         for (int i = 0; i < level; i++)
             System.out.print("\t");
           
         if(arr[index].isFile())
             System.out.println(arr[index].getName());
             
         else if(arr[index].isDirectory())
         {
             System.out.println("[" + arr[index].getName() + "]"); 
             RecursivePrint(arr[index].listFiles(), 0, level + 1);
         }
      
         RecursivePrint(arr,++index, level);
    }
	
    public static void main(String[] args)
    {
        String maindirpath = "C:\\Users\\caste\\Desktop\\Project 2";
        File maindir = new File(maindirpath);
        if(maindir.exists() && maindir.isDirectory())
        { 
            File arr[] = maindir.listFiles();
            System.out.println("Files from directory : " + maindir);
            RecursivePrint(arr,0,0); 
       } 
    }
}
