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
		file.writeLn("@s = internal constant [12 x i8] c\"result: %d\\0A\\00\"");
		file.writeLn("@sss = internal constant [4 x i8] c\"%s\\0A\\00\"");
		file.writeLn("@ss = internal constant [26 x i8] c\"setting variable a to %d\\0A\\00\"");
		file.writeLn("declare i32 @printf(i8 *, ...)");
		file.writeLn("declare i32 @atoi(i8*)");
		
		
		file.writeLn("define i32 @main(i32 %argc, i8** %argv) { ");
		file.writeLn("%ps = getelementptr [12 x i8]* @s, i64 0, i64 0");
		file.writeLn("%pss = getelementptr [26 x i8]* @ss, i64 0, i64 0");
		file.writeLn("%psss = getelementptr [4 x i8]* @sss, i64 0, i64 0");
		
		file.writeLn("%atLeasOneParameter = icmp sge i32 %argc, 2");
		file.writeLn("br i1 %atLeasOneParameter, label %argument1, label %noargument1");
		
		file.writeLn("noargument1: ");
		file.writeLn("br label %cont");
		
		file.writeLn("argument1: ");
		file.writeLn("%firstArgumentCharPtrPtr = getelementptr inbounds i8** %argv, i64 1");
		file.writeLn("%firstArgumentCharPtr = load i8** %firstArgumentCharPtrPtr");
		file.writeLn("%firstArgument = call i32 @atoi(i8* %firstArgumentCharPtr)");
		
		//file.writeLn("call i32 (i8 *, ...)* @printf(i8* %psss, i8* %firstArgumentCharPtr)");
		file.writeLn("call i32 (i8 *, ...)* @printf(i8* %pss, i32 %firstArgument)");
		file.writeLn("br label %cont");
		file.writeLn("cont: ");
		file.writeLn("%a = phi i32 [ %firstArgument, %argument1 ], [ 0, %noargument1 ]");
		variables.put("a", "%a");
		
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