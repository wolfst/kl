package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class WhileStatement implements Statement {
	private Condition c;
	private Statement s;
	

	public WhileStatement(Condition c, Statement s) {
		super();
		this.c = c;
		this.s = s;
	}


	@Override
	public void evaluate(Map<String, Integer> variables) {
		while(c.evaluate(variables)) s.evaluate(variables);
	}

}
