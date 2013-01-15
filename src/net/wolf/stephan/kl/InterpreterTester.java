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

	private Interpreter createInterpreter(String testString) throws Exception {
		CharStream stream = new ANTLRStringStream(testString);
		KLLexer lexer = new KLLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		KLParser parser = new KLParser(tokens);
		CommonTreeNodeStream nodeStream = new CommonTreeNodeStream(
				parser.evaluator().tree);
		
		parser.reset();
		System.out.println(parser.evaluator().tree.toStringTree());

		Interpreter interpreter = new Interpreter(nodeStream);
		return interpreter;
	}

	@Test
	public void testSimpleAssignment() throws Exception {
		Interpreter interpreter = createInterpreter("x:= 1");

		Program p = interpreter.evaluator();
		assertEquals(1, p.evaluate());
	}
	
	@Test
	public void testIfThen() throws Exception {
		Interpreter interpreter = createInterpreter("if a > 0 then x:= 1 else x:=2 fi");

		Program p = interpreter.evaluator();
		p.setVariable("a", 1);
		assertEquals(1, p.evaluate());
	}
	@Test
	public void testIfElse() throws Exception {
		Interpreter interpreter = createInterpreter("if a > 0 then x:= 1 else x:=2 fi");

		Program p = interpreter.evaluator();
		p.setVariable("a", 0);
		assertEquals(2, p.evaluate());
	}
	@Test
	public void testSequence() throws Exception {
		Interpreter interpreter = createInterpreter("y:= 2; x:=3; y:=1");

		Program p = interpreter.evaluator();
		assertEquals(3, p.evaluate());
	}
	@Test
	public void testExpressions() throws Exception {
		Interpreter interpreter = createInterpreter("x:= a; x:= ((x-1) + 1)*4/2");

		Program p = interpreter.evaluator();
		int a = rand.nextInt(1000);
		p.setVariable("a", a);
		assertEquals(a*2, p.evaluate());
	}
	@Test
	public void testWhile() throws Exception {
		Interpreter interpreter = createInterpreter("x:=0; while a > 0 do a:= a-1; x := x+1 od");

		Program p = interpreter.evaluator();
		int a = rand.nextInt(100);
		p.setVariable("a", a);
		int expected;
		if(a >0) expected = a;
		else expected = 1;
		assertEquals(expected, p.evaluate());
	}

	@Test
	public void testFak() throws Exception {
		Interpreter interpreter = createInterpreter("y:= 1; while x>0 do y:= y*x; x:= x-1 od; x:=y");

		Program p = interpreter.evaluator();
		int a = rand.nextInt(12);
		int fak = 1;
		for(int i =1; i<= a; ++i)
			fak = fak*i;
		p.setVariable("x", a);
		assertEquals(fak, p.evaluate());
	}


}
