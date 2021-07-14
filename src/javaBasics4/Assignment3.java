package javaBasics4;

import java.util.LinkedList;

public class Assignment3 {
	public static void main(String[] args)
	        throws InterruptedException
	    {
	        final controller controller = new controller();
	  
	        
	        Thread producer = new Thread(new Runnable() {
	            @Override
	            public void run()
	            {
	                try {
	                	controller.produce();
	                }
	                catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	  
	        // Create consumer thread
	        Thread consumer = new Thread(new Runnable() {
	            @Override
	            public void run()
	            {
	                try {
	                	controller.consume();
	                }
	                catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	  
	    
	        producer.start();
	        consumer.start();
	  
	  
	        producer.join();
	        consumer.join();
	    }
	
	
	public static class controller{
		
		LinkedList<Integer> list = new LinkedList<>();
		int size = 10;
		
		public void produce() throws InterruptedException{
			
			int data = 0;
			while(true) {
				synchronized(this) {
					while(list.size() == size)
						wait();
					
					System.out.println("Producer made-" + data);
					list.add(data++);
					notify();
					Thread.sleep(1000);
					
				}
			}
		}
		
		public void consume() throws InterruptedException{
	
			while(true) {
				synchronized(this) {
					while(list.size() == 0)
						wait();
					
					int ret = list.removeFirst();
					System.out.println("Consumer removed-" + ret);
					
					notify();
					Thread.sleep(1000);
					
				}
			}
		}
		
	}
}
