package net.wolf.stephan.kl;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import net.wolf.stephan.kl.interpreter.Interpreter;
import net.wolf.stephan.kl.interpreter.Program;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.Test;

public class InterpreterTester {
	private Random rand = new Random();

	@Test
	public void simpleAssignment() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		int a = rand.nextInt(1000);
		assertEquals(a, InterpreterProgram.interprete("test/"+methodName+".kl", false, a));
	}
	
	@Test
	public void ifThen() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		assertEquals(1, InterpreterProgram.interprete("test/"+methodName+".kl", false, 1));
	}
	@Test
	public void ifElse() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		assertEquals(2, InterpreterProgram.interprete("test/"+methodName+".kl", false, 0));
	}
	
	@Test
	public void sequence() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		;
		assertEquals(3, InterpreterProgram.interprete("test/"+methodName+".kl", false, 3));
	}
	@Test
	public void expression() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		int a = rand.nextInt(1000);
		assertEquals(a*2, InterpreterProgram.interprete("test/"+methodName+".kl", false, a));
	}
	@Test
	public void testWhile() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		int a = rand.nextInt(100);
		int expected;
		if(a >0) expected = a;
		else expected = 0;
		assertEquals(expected, InterpreterProgram.interprete("test/"+methodName+".kl", false, a));
	}
	@Test
	public void fak() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		int a = rand.nextInt(12);
		int fak = 1;
		for(int i =1; i<= a; ++i)
			fak = fak*i;
		assertEquals(fak, InterpreterProgram.interprete("test/"+methodName+".kl", false, a));
	}
	/*
	// This test must fail
	@Test(timeout 10)
	public void abort() throws Exception {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		InterpreterProgram.interprete("test/"+methodName+".kl", false, a);
	}*/


}
