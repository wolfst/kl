package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AssignmentStatement implements Statement {
	String variable;
	Expression expression;
	
	public AssignmentStatement(String variable, Expression expression) {
		super();
		this.variable = variable;
		this.expression = expression;
	}

	@Override
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables) throws IOException {
		variables.put(variable, expression.evaluate(writer, variables));		
		Set<String> assignments = new TreeSet<String>();
		assignments.add(variable);
		return assignments;
	}

}
