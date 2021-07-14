package javaBasics4.assignment4;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTest {

	@Test
	public void slopeHorizontal() {
		//Test Horizontal Line
		Line test1 = new Line(0,0,10,0);
		assertEquals(0,test1.getSlope(),0.001);
	}
	
	@Test
	public void distanceHorizontal() {
		Line test1 = new Line(0,0,10,0);
		assertEquals(10, test1.getDistance(),0.001);
	}
	@Test
	public void parallelHorizontal() {
		Line test1 = new Line(0,0,10,0);
		Line test2 = new Line(0,10,10,10);
		assertEquals(true, test1.parallelTo(test2));
	}
	
	
	@Test
	public void slopeNegHorizontal() {
		//Test Horizontal Line
		Line test1 = new Line(0,0,-10,0);
		assertEquals(0,test1.getSlope(),0.001);
	}

	@Test
	public void distNegHorizontal() {
		Line test1 = new Line(0,0,-10,0);
		assertEquals(10, test1.getDistance(),0.001);
	}

	@Test
	public void parallelNegHorizontal() {
		Line test1 = new Line(0,0,-10,0);
		Line test2 = new Line(0,10,10,10);
		assertEquals(true, test1.parallelTo(test2));
	}
	
	@Test
	public void slopeVertical() {
		//Test Vertical Line
		Line test1 = new Line(0,0,0,10);
		try{
			//A Vertical line has an undefined slope so 
			//an exception should be thrown
			test1.getSlope();
			fail();
		}catch(Exception e) {
			assertEquals(true,true);
		}
	}

	@Test
	public void distVerticle() {
		Line test1 = new Line(0,0,0,10);
		assertEquals(10, test1.getDistance(),0.001);
	}

	@Test
	public void parallelVerticle() {
		Line test1 = new Line(0,0,0,10);
		Line test2 = new Line(10,0,10,10);
		try{
			//A Vertical line has an undefined slope so 
			//an exception should be thrown considering the 
			//parallel line program uses the slope to find
			//Parallels
			test1.parallelTo(test2);
			fail();
		}catch(Exception e) {
			assertEquals(true,true);
		}
	
	}
	
	@Test
	public void slopeNegVertical() {
		//Test Vertical Line
		Line test1 = new Line(0,0,0,-10);
		try{
			//A Vertical line has an undefined slope so 
			//an exception should be thrown
			test1.getSlope();
			fail();
		}catch(Exception e) {
			assertEquals(true,true);
		}
	}

	@Test
	public void distNegVerticle() {
		Line test1 = new Line(0,0,0,-10);
		assertEquals(10, test1.getDistance(),0.001);
	}

	@Test
	public void parallelNegVerticle() {
		Line test1 = new Line(0,0,0,-10);
		Line test2 = new Line(10,0,10,-10);
		try{
			//A Vertical line has an undefined slope so 
			//an exception should be thrown considering the 
			//parallel line program uses the slope to find
			//Parallels
			test1.parallelTo(test2);
			fail();
		}catch(Exception e) {
			assertEquals(true,true);
		}
	
	}

}
