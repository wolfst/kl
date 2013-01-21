package net.wolf.stephan.kl.compiler;

import java.util.Map;
import java.util.Set;

public class AbortStatement implements Statement {

	@Override
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables) {
		System.out.println("abort was called, starting infinite loop");
		while(true);

	}

}
