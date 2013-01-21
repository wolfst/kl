package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class SequenceStatement implements Statement {
	private Statement first;
	private Statement next;
	public SequenceStatement(Statement first, Statement next) {
		super();
		this.first = first;
		this.next = next;
	}
	
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables) throws IOException{
		// Can be improved
		Set<String> assignements = first.evaluate(writer, variables);
		Set<String> assignments2 = next.evaluate(writer, variables);
		assignements.addAll(assignments2);
		return assignements;
	}
	
}
