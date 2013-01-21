package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;

public class CompareCondition implements Condition {
	private String operator;
	private Expression left;
	private Expression right;

	public CompareCondition(String operator, Expression left, Expression right) {
		super();
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	@Override
	public String evaluate(LineWriter writer, Map<String, String> variables) throws IOException {
		String register = Global.getNextRegister();
		String left = this.left.evaluate(writer, variables);
		String right = this.right.evaluate(writer, variables);
		
		
		writer.write(register + " = icmp ");
		if(operator.equals(">"))writer.write("sgt");
		else if(operator.equals(">="))writer.write("sge");
		else if(operator.equals("<"))writer.write("slt");
		else if(operator.equals("<="))writer.write("sle");
		else if(operator.equals("!="))writer.write("ne");
		else writer.write("eq");
		writer.write(" i32 "+left+", "+right);
		return register;
	}


}
