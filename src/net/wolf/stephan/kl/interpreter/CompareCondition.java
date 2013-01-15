package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class CompareCondition implements Condition {
	private String operator;
	private Expression left;
	private Expression right;

	public CompareCondition(String operator, Expression left, Expression right) {
		super();
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean evaluate(Map<String, Integer> variables) {
		int left = this.left.evaluate(variables);
		int right = this.right.evaluate(variables);
		
		if(operator.equals(">")) return left > right;
		else if(operator.equals(">=")) return left >= right;
		else if(operator.equals("<")) return left < right;
		else if(operator.equals("<=")) return left <= right;
		else if(operator.equals("!=")) return left != right;
		else return left == right;
	}


}
