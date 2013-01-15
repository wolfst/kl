package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public interface Expression {
	public int evaluate(Map<String, Integer> variables);
}
