package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;

public class MinusExpression implements Expression {
	Expression left, right;
	

	public MinusExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}


	@Override
	public String evaluate(LineWriter writer, Map<String, String> variables) throws IOException {
		String register = Global.getNextRegister();
		writer.writeLn(register+" = sub i32 "+left.evaluate(writer, variables) +", "+right.evaluate(writer, variables));
		return register;
	}


}
