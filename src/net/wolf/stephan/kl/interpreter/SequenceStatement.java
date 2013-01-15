package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class SequenceStatement implements Statement {
	private Statement first;
	private Statement next;
	public SequenceStatement(Statement first, Statement next) {
		super();
		this.first = first;
		this.next = next;
	}
	
	public void evaluate(Map<String, Integer> variables){
		first.evaluate(variables);
		next.evaluate(variables);
	}
	
}
