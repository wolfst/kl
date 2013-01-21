package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AbortStatement implements Statement {

	@Override
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables) throws IOException {
		String abortLabel = "abort"+Global.getNextID();
		writer.writeLn("br label %"+abortLabel);
		writer.writeLn(abortLabel+":");
		writer.writeLn("br label %"+abortLabel);
		return new TreeSet<String>();
		
	}

}
