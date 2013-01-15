package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class MultExpression implements Expression {
	Expression left, right;
	

	public MultExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}


	@Override
	public int evaluate(Map<String, Integer> variables) {
		return left.evaluate(variables) * right.evaluate(variables);
	}

}
