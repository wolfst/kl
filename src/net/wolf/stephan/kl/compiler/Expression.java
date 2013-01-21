package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;

public interface Expression {
	public String evaluate(LineWriter writer, Map<String, String> variables)  throws IOException;
}
