package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface Statement {
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables)  throws IOException;
}
