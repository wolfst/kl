tree grammar Compiler;

options {
  language = Java;
  tokenVocab = KL;
  ASTLabelType = CommonTree;
}

@header {
package net.wolf.stephan.kl.compiler;
}
@members {
  Map<String, Integer> variables = new HashMap<String, Integer>();
}

evaluator returns [Program p]
  :  s=statement {$p = new Program($s.s);};

statement returns [Statement s]
  : 'skip' {$s = new SkipStatement();}
  | 'abort' {$s = new AbortStatement();}
  | ^('if' condition t=statement (e=statement)?) {$s = new IfStatement($condition.c, $t.s, $e.s);}
  | ^(':=' var=ID v=expression) {$s = new AssignmentStatement($var.text, $v.e);}
  | ^(';' f=statement n=statement) {$s = new SequenceStatement($f.s, $n.s);}
  | ^('while' c=condition st=statement) {$s = new WhileStatement($c.c, $st.s);}
;

condition returns [Condition c]
  :  'true' {$c = new ConstCondition(true);}
  |  'false' {$c = new ConstCondition(false);}
  | ^(op=('>' | '>=' | '<' | '<=' | '=') left=expression right=expression) {
    c = new CompareCondition($op.text, $left.e, $right.e);
  }
;

expression returns [Expression e]
  : INT {$e = new ConstExpression(Integer.parseInt($INT.text));}
  | ID {$e = new VariableExpression($ID.text);}
  | ^('+' l=expression r=expression) {$e = new PlusExpression($l.e, $r.e);}
  | ^('-' l=expression r=expression) {$e = new MinusExpression($l.e, $r.e);}
  | ^('*' l=expression r=expression) {$e = new MultExpression($l.e, $r.e);}
  | ^('/' l=expression r=expression) {$e = new DivExpression($l.e, $r.e);}
;
