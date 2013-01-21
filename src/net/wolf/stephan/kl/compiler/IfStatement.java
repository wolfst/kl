package net.wolf.stephan.kl.compiler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IfStatement implements Statement {
	private Condition condition;
	private Statement thenStatement;
	private Statement elseStatement;

	public IfStatement(Condition condition, Statement thenStatement,
			Statement elseStatement) {
		super();
		this.condition = condition;
		this.thenStatement = thenStatement;
		this.elseStatement = elseStatement;
	}

	@Override
	public Set<String> evaluate(LineWriter writer, Map<String, String> variables) throws IOException {
		
		String thenLabel = "then"+Global.getNextID();
		String elseLabel = "else"+Global.getNextID();
		String nextLabel = "next"+Global.getNextID();
		
		writer.writeLn("br i1 "+ condition.evaluate(writer, variables) + ", label %"+thenLabel+", label %"+elseLabel);
		
		// Then branch
		writer.writeLn(thenLabel+":");
		Map<String,String> thenVariables = new HashMap<String, String>();
		thenVariables.putAll(variables);
		Set<String> assignementsThen = thenStatement.evaluate(writer, thenVariables);
		writer.writeLn("br label %"+nextLabel);
		
		// Else branch
		writer.writeLn(elseLabel+":");
		Map<String,String> elseVariables = new HashMap<String, String>();
		elseVariables.putAll(variables);
		Set<String> assignementsElse = elseStatement.evaluate(writer, elseVariables);
		writer.writeLn("br label %"+nextLabel);

		// Merge branche afterwards
		writer.writeLn(nextLabel+":");
		assignementsElse.retainAll(assignementsThen);
		
		for(String variable : assignementsElse){
			String newRegister = Global.getNextRegister();
			writer.writeLn(newRegister + " = phi i32 [ "+thenVariables.get(variable)+", %"+thenLabel+" ], ["+elseVariables.get(variable)+", %"+elseLabel+"]");
			variables.put(variable, newRegister);
		}
		
		
		return assignementsElse;
	}

}
