package javaBasics1;


public class JB1{
	
public static void number1() {
	int i ,j;
	
	for(i = 0; i < 5; i++) {
		
		for(j=0; j<=i ; j++) {
			if(i==4) {
				if(j != 4) {
					System.out.print("..");
				}else {
					System.out.print(".");
				}
			
			}else {
				System.out.print("*");
			}
		}
		System.out.println();
	}
}

public static void number2() {
	int i ,j;
	
	for(i = 5; i > 0; i--) {
		
		for(j=1; j<=i ; j++) {
			if(i==5) {
					System.out.print("..");
			}else {
				System.out.print("*");
			}
		}
		System.out.println();
	}
}


public static void number3() {
	int size = 7,odd = 1, nos = size/2+2; 
    for (int i = 1; i <= size/2+1; i++) { 
        for (int k = nos; k >= 1; k--) { 
                                           
            System.out.print(" ");
        }
        for (int j = 1; j <= odd; j++) { 
                                           
            System.out.print("*");
        }
        System.out.println();
        if (i < size/2+1) {
            odd += 2; 
            nos -= 1; 
        }else if(i == size/2+1 ) {
        	System.out.println("...........");
        }
        
    }
}

public static void number4() {
	int size = 7,odd = 7, nos = size/2+2; 
    for (int i = 1; i <= size/2+1; i++) { 
    	  
        if(i == 1 ) {
        	System.out.println("   ...........");
        }
        for (int k = nos; k >= 1; k--) {                                      
            System.out.print(" ");      
        }
        for (int j = 1; j <= odd; j++) {                                   
            System.out.print("*");
        }
        System.out.println();
        if (i < size/2+1) {
            odd -= 2; 
            nos += 1; 
        }
        
    }
}

public static void main(String args[]) {
	number1();
	System.out.println("-----------------------------------------------");
	number2();
	System.out.println("-----------------------------------------------");
	number3();
	System.out.println("-----------------------------------------------");
	number4();
}


}
	
