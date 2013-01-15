package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class VariableExpression implements Expression {

	private String variable;

	@Override
	public int evaluate(Map<String, Integer> variables) {
		if(variables.get(variable) == null){
			System.out.println("Variable '"+variable+"' was read before being initialized -> assuming 0 as value");
			return 0;
		}
		return variables.get(variable);
	}

	public VariableExpression(String variable) {
		super();
		this.variable = variable;
	}

}
