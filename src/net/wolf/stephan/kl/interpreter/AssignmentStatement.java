package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class AssignmentStatement implements Statement {
	String variable;
	Expression expression;
	
	public AssignmentStatement(String variable, Expression expression) {
		super();
		this.variable = variable;
		this.expression = expression;
	}

	@Override
	public void evaluate(Map<String, Integer> variables) {
		variables.put(variable, expression.evaluate(variables));
	}

}
