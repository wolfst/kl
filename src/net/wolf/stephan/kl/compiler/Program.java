package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.HashMap;

public class Program {
	private HashMap<String, String> variables = new HashMap<String, String>();
	private Statement first;
	public Program(Statement first) {
		super();
		this.first = first;
	}
	public void evaluate(LineWriter file) throws IOException{
		
		file.writeLn("; define string constant \"%d\\n\"");
		file.writeLn("@s = internal constant [4 x i8] c\"%d\\0A\\00\"");
		file.writeLn("declare i32 @printf(i8 *, ...)");
		file.writeLn("define i32 @main() { ");
		file.writeLn(" %ps = getelementptr [4 x i8]* @s, i64 0, i64 0");
		
		

		
		first.evaluate(file, variables);
		String outputRegister = new VariableExpression("x").evaluate(file, variables);
		file.writeLn("call i32 (i8 *, ...)* @printf(i8* %ps, i32 "+outputRegister+")");
		file.writeLn("ret i32 "+outputRegister);
		file.writeLn("}");
		
	}
	public void setVariable(String variable, int value) {
		variables.put(variable, "i32 "+value);
		
	}
}