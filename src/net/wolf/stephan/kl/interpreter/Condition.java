package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public interface Condition {
	public boolean evaluate(Map<String, Integer> variables);

}
