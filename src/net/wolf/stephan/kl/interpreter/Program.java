package net.wolf.stephan.kl.interpreter;

import java.util.HashMap;

public class Program {
	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	private Statement first;
	public Program(Statement first) {
		super();
		this.first = first;
	}
	public int evaluate(){
		
		first.evaluate(variables);
		return variables.get("x");
	}
	public void setVariable(String variable, int value) {
		variables.put(variable, value);
		
	}
}
