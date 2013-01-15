package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class ConstExpression implements Expression{
	int value;

	public ConstExpression(int value) {
		super();
		this.value = value;
	}

	@Override
	public int evaluate(Map<String, Integer> variables) {
		return value;
	}

}
