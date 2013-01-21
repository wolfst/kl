package net.wolf.stephan.kl.compiler;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SkipStatement implements Statement {

	@Override
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables) {
		return new TreeSet<String>();

	}

}
