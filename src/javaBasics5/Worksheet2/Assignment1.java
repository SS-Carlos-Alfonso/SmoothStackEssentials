package javaBasics5.Worksheet2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


interface doOp {
	boolean check(int a);
}

class Operations{
	public static boolean doOperation(doOp op, int num) {
		return op.check(num);
	}
	

    public static doOp isOdd(){
        return a -> (a % 2 != 0)? true:false;
    }
    
    public static doOp isPrime(){
           return a ->{ for (int i = 2; i * i <= a; i++) {
                if (a % i == 0) {
                    return false;
                }
            }
            return true;
               };
    }

    public static doOp isPalindrome() {
        return a -> a == Integer.parseInt(new StringBuilder(String.valueOf(a)).reverse().toString()) ? true : false;
    }
	
}



public class Assignment1 {
	
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		List<Integer> list = new ArrayList<Integer>();
		Operations obs = new Operations();
		doOp ops;
		
		System.out.println("Please enter the number of test cases: ");
		int testCases = in.nextInt();
		int loop = 0;
		int count = 0;
		
		boolean result = false;
		String output = null;
	
			while(count != testCases*2) {
				list.add(in.nextInt());
				count++;
			}
				
			int[] array = list.stream().mapToInt(i->i).toArray();
	
			loop++;
		
			for(int i = 0; i < (testCases*2)-1; i++) {
				if(i % 2 == 0) {
					if(array[i] == 1) {
						ops = obs.isOdd();
						result = obs.doOperation(ops, array[i+1]);
						output = (result) ? "ODD" : "EVEN";
						System.out.println(output);
					}else if(array[i] == 2) {
						ops = obs.isPrime();
						result = obs.doOperation(ops, array[i+1]);
						output = (result) ? "PRIME" : "COMPOSIT";
						System.out.println(output);
					}else {
						ops = obs.isPalindrome();
						result = obs.doOperation(ops, array[i+1]);
						output = (result) ? "PALINDROME" : "NOT PALINDROME";
						System.out.println(output);
					}
				}
		
			}
		
		
		
		
		
		
	}
}
