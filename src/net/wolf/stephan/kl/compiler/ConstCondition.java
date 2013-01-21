package net.wolf.stephan.kl.compiler;

import java.util.Map;


public class ConstCondition implements Condition {
	private boolean value;

	public ConstCondition(boolean value) {
		super();
		this.value = value;
	}

	@Override
	public String evaluate(LineWriter writer, Map<String, String> variables) {
		if(value) return "1";
		return "0";
	}
	

}
