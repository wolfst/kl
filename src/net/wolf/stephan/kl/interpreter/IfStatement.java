package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class IfStatement implements Statement {
	private Condition condition;
	private Statement thenStatement;
	private Statement elseStatement;

	public IfStatement(Condition condition, Statement thenStatement,
			Statement elseStatement) {
		super();
		this.condition = condition;
		this.thenStatement = thenStatement;
		this.elseStatement = elseStatement;
	}

	@Override
	public void evaluate(Map<String, Integer> variables) {
		if(condition.evaluate(variables)){
			thenStatement.evaluate(variables);
		}
		else{
			elseStatement.evaluate(variables);
		}

	}

}
