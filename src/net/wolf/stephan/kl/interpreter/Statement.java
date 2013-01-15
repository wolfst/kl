package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public interface Statement {
	public void evaluate(Map<String, Integer> variables);
}
