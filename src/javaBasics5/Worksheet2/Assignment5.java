package javaBasics5.Worksheet2;

public class Assignment5 {
	
	
	public static boolean recursion(int start, int[] array, int target) {
	    if(start >= array.length)
	        return target == 0;
	          
	    int i = start;
	    int sum = 0;
	    
	    while(i < array.length && array[start] == array[i]) {
	        sum += array[i];
	        i++;
	    }
	                              
	    if(recursion(i, array, target - sum))
	        return true;
	                                        
	    if(recursion(i, array, target))
	        return true;
	                                                  
	    return false;
	}
	
	public static void main(String[] args) {
		int [] array = new int[] {1 , 2 , 4 , 8 ,1};
		int [] array2 = new int[] {2 , 4 , 8  ,1,  5};
		int [] array3 = new int[] {2,4,4,8};
		
		System.out.println(recursion(0, array, 10));
		System.out.println(recursion(0, array2, 14));
		System.out.println(recursion(0, array3, 14));
	}
}
