package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;

public class DivExpression implements Expression {
	Expression left, right;
	

	public DivExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}


	@Override
	public String evaluate(LineWriter writer, Map<String, String> variables) throws IOException {
		String register = Global.getNextRegister();
		writer.writeLn(register+" = sdiv i32 "+left.evaluate(writer, variables) +", "+right.evaluate(writer, variables));
		return register;
	}

}
