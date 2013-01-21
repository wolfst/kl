package net.wolf.stephan.kl.compiler;

import java.util.Map;

public class VariableExpression implements Expression {

	private String variable;

	@Override
	public String evaluate(LineWriter writer, Map<String, String> variables) {
		if(variables.get(variable) == null){
			System.out.println("warning: variable '"+variable+"' was read before being initialized -> assuming 0 as value");
			return "0";
		}
		return variables.get(variable);
	}

	public VariableExpression(String variable) {
		super();
		this.variable = variable;
	}

}
