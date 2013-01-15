
package net.wolf.stephan.kl;

import net.wolf.stephan.kl.interpreter.Interpreter;
import net.wolf.stephan.kl.interpreter.Program;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.Test;

public class Tester {
	
	private KLParser createParser(String testString) throws Exception {
        CharStream stream = new ANTLRStringStream(testString);
        KLLexer lexer = new KLLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        KLParser parser = new KLParser(tokens);
        return parser;
    }
	
	@Test
	public void testSimpleAssignment() throws Exception{
		KLParser parser = createParser("y:= 1; while x > 0 do y:= y * x; x := x-1 od; x:=y ");
		
		
		System.out.println(parser.evaluator().tree.toStringTree());
		
		parser.reset();
		
		CommonTreeNodeStream nodeStream = new CommonTreeNodeStream(parser.evaluator().tree);
		
		Interpreter w = new Interpreter(nodeStream);
		
		Program p = w.evaluator();
		p.setVariable("x", 10);
		System.out.println(p.evaluate());
	}

}
