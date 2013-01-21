package net.wolf.stephan.kl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import net.wolf.stephan.kl.compiler.Compiler;
import net.wolf.stephan.kl.compiler.LineWriter;
import net.wolf.stephan.kl.compiler.Program;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.Test;

public class CompilerTester {
	private Random rand = new Random();

	private LineWriter createFile(String outputFile) throws IOException {
		// Create file
		FileWriter fstream = new FileWriter(outputFile);
		return new LineWriter(new BufferedWriter(fstream));

	}
	
	private int runCommand(String cmd){
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            
            int ret = p.waitFor();
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            String s;
            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
            return ret;
        }
        catch (IOException e) {
        	System.out.println("Could not execute '"+cmd+"'");
            e.printStackTrace();
            System.exit(-1);
        }catch(InterruptedException e){
        	System.out.println("The execution of '"+cmd+"' was interrupted!");
        	System.exit(-1);
        }
		return -1;
	}

	private Compiler createCompiler(String testString){

		CharStream stream = new ANTLRStringStream(testString);
		KLLexer lexer = new KLLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		KLParser parser = new KLParser(tokens);
		CommonTreeNodeStream nodeStream = new CommonTreeNodeStream(
				parser.evaluator().tree);

		parser.reset();
		System.out.println(parser.evaluator().tree.toStringTree());

		Compiler interpreter = new Compiler(nodeStream);
		return interpreter;
	}

	@Test
	public void testSimpleAssignment() throws Exception {
		//Compiler compiler = createCompiler("x:= 1");
		//
		//"x:= (1+3-2)*4/2"
		Compiler compiler = createCompiler("x:=10;y:= 1; while x>0 do y:= y*x; x:= x-1 od; x:=y");
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		LineWriter file = createFile(methodName+".ll");
		Program p = compiler.evaluator();
		p.evaluate(file);
		file.flush();
		file.close();
		
		
		runCommand("llc -march=x86-64 -disable-cfi "+methodName+".ll");
		runCommand("g++ -o "+methodName+" "+methodName+".s");
		runCommand("./"+methodName);
		//assertEquals(1, );
		
	}
}
