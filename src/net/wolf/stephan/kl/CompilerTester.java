package net.wolf.stephan.kl;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class CompilerTester {
	private Random rand = new Random();



	@Test
	public void simpleAssignment() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		int a = rand.nextInt(1000);
		assertEquals(a%256, CompilerProgram.runCommand("./test/"+methodName+" "+a+ " "+a));
	}
	
	@Test
	public void ifThen() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		assertEquals(1, CompilerProgram.runCommand("./test/"+methodName+ " 1"));
	}
	@Test
	public void ifElse() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		assertEquals(2, CompilerProgram.runCommand("./test/"+methodName+ " 0"));
	}
	
	@Test
	public void sequence() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		assertEquals(3, CompilerProgram.runCommand("./test/"+methodName));
	}
	@Test
	public void expression() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		int a = rand.nextInt(1000);
		assertEquals(a*2%256, CompilerProgram.runCommand("./test/"+methodName+" "+a));
	}
	@Test
	public void testWhile() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		int a = rand.nextInt(100);
		int expected;
		if(a >0) expected = a;
		else expected = 0;
		assertEquals(expected%256, CompilerProgram.runCommand("./test/"+methodName+ " "+a));
	}
	@Test
	public void fak() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		int a = rand.nextInt(12);
		int fak = 1;
		for(int i =1; i<= a; ++i)
			fak = fak*i;
		assertEquals(fak%256, CompilerProgram.runCommand("./test/"+methodName+ " "+a));
	}
	/*
	// This test must fail
	@Test(timeout 10)
	public void abort() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		CompilerProgram.compile("test/"+methodName+".kl", "test/"+methodName, false);
		CompilerProgram.runCommand("./test/"+methodName);
	}*/
}
