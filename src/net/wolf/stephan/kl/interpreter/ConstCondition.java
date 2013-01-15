package net.wolf.stephan.kl.interpreter;

import java.util.Map;


public class ConstCondition implements Condition {
	private boolean value;

	public ConstCondition(boolean value) {
		super();
		this.value = value;
	}

	@Override
	public boolean evaluate(Map<String, Integer> variables) {
		return value;
	}
	

}
