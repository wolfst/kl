package net.wolf.stephan.kl.interpreter;

import java.util.Map;

public class AbortStatement implements Statement {

	@Override
	public void evaluate(Map<String, Integer> variables) {
		System.out.println("abort was called, starting infinite loop");
		while(true);

	}

}
