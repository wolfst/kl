package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WhileStatement implements Statement {
	private Condition c;
	private Statement s;

	public WhileStatement(Condition c, Statement s) {
		super();
		this.c = c;
		this.s = s;
	}

	@Override
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables) throws IOException {

		String beforeLoop = "beforeLoop" + Global.getNextID();
		String conditionLabel = "condition" + Global.getNextID();
		String loopBodyLabel = "loop" + Global.getNextID();
		String loopEndLabel = "loopend" + Global.getNextID();

		// Reserve ids for phi variables
		Map<String, String> phiVariables = new HashMap<String, String>();
		for (String variable : variables.keySet()) {
			phiVariables.put(variable, Global.getNextRegister());
		}

		// Define a node to identify the incoming control flow for the phi statement
		writer.writeLn("br  label %" + beforeLoop);
		writer.writeLn(beforeLoop+":");
		writer.writeLn("br  label %" + conditionLabel);

		// - Loop body -
		writer.writeLn(loopBodyLabel + ":");
		Map<String, String> variablesInsideLoop = new HashMap<String, String>();
		variablesInsideLoop.putAll(phiVariables);
		Set<String> assignements = s.evaluate(writer, variablesInsideLoop);
		writer.writeLn("br label %" + conditionLabel);

		// - Condition -
		writer.writeLn(conditionLabel + ":");
		// Build phi nodes
		for(String var : assignements){
			if(phiVariables.get(var) == null) continue;
			writer.writeLn(phiVariables.get(var) + " = phi i32 [ " + variablesInsideLoop.get(var) + ", %" + loopBodyLabel + " ], [" + variables.get(var) + ", %" + beforeLoop + "]");
			variables.put(var, phiVariables.get(var));
		}
		writer.writeLn("br i1 " + c.evaluate(writer, variables) + ", label %" + loopBodyLabel + ", label %" + loopEndLabel);

		// - End -
		writer.writeLn(loopEndLabel + ":");
		return assignements;
	}

}
