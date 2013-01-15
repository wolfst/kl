package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class DivExpression implements Expression {
	Expression left, right;
	

	public DivExpression(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}


	@Override
	public int evaluate(Map<String, Integer> variables) {
		return left.evaluate(variables) / right.evaluate(variables);
	}

}
