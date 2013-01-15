package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class VariableExpression implements Expression {

	private String variable;

	@Override
	public int evaluate(Map<String, Integer> variables) {
		return variables.get(variable);
	}

	public VariableExpression(String variable) {
		super();
		this.variable = variable;
	}

}
