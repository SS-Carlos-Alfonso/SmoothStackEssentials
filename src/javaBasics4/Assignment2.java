package javaBasics4;

class Wait{
static void sleep(long time)
{
    try
    {
        Thread.sleep(time);
    }
    catch (InterruptedException e)
    {
        e.printStackTrace();
    }
}
}


class Parent
{  
    synchronized void test1(Parent p2)
    {
        System.out.println("test1-begin");
        Wait.sleep(1000);
        p2.test2();
        System.out.println("test1-end");
    }
    synchronized void test2()
    {
        System.out.println("test2-begin");
        Wait.sleep(1000);
        System.out.println("test2-end");
    }
}



class Thread1 extends Thread
{
	private Parent p1;
	private Parent p2;
	
	public Thread1(Parent p1, Parent p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	@Override 
	public void run() {
		p1.test1(p2);
	}
}


class Thread2 extends Thread
{
	private Parent p1;
	private Parent p2;
	
	public Thread2(Parent p1, Parent p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	@Override 
	public void run() {
		p2.test1(p1);
	}
}


public class Assignment2 {

	public static void main(String[] args)
    { 
		Parent s1 = new Parent();
		Parent s2 = new Parent();

        Thread1 t1 = new Thread1(s1, s2);
        t1.start();
 
    
        Thread2 t2 = new Thread2(s1, s2);
        t2.start();
        Wait.sleep(2000);
    }
}
