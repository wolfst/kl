package net.wolf.stephan.kl.compiler;

import java.util.Map;

public class ConstExpression implements Expression{
	Integer value;

	public ConstExpression(int value) {
		super();
		this.value = value;
	}

	@Override
	public String evaluate(LineWriter writer, Map<String, String> variables) {
		return value.toString();
	}

}
