grammar KL;

options{
  language = Java;
  output = AST;
  ASTLabelType = CommonTree;
}

@header {
package net.wolf.stephan.kl;
import java.util.HashMap;
}
@lexer::header {
package net.wolf.stephan.kl;
}



ID 	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT	:	'0'..'9'+
    ;

FLOAT	:
   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

evaluator
  :  r=seq_statement EOF!;
  
seq_statement:
  statement (';'^ seq_statement)?
;

statement
  : 'skip'
  | 'abort'
  | 'if' c=condition 'then' t=seq_statement ('else' e=seq_statement)? 'fi' -> ^('if' $c $t $e?) // without end if -> nondeterministic
  | assignment
  | 'while' c=condition 'do' s=seq_statement 'od' -> ^('while' $c $s)
;

condition
  :  'true'
  |  'false'
  | left=expression op=('>' | '>=' | '<' | '<=' | '=' | '!=')^ right=expression
;
// requires backtracking
// http://www.antlr.org/wiki/display/ANTLR3/How+to+remove+global+backtracking+from+your+grammar
//expression
//  : value_or_brackets '+'^ expression
//  | value_or_brackets '-'^ expression
//  | value_or_brackets '*'^ expression
//  | value_or_brackets '/'^ expression
//  | value_or_brackets
//;
expression
  : value_or_brackets (('+'^ expression) | ('-'^ expression) | ('/'^ expression) | ('*'^ expression))?
  ;

value_or_brackets
  : '('! expression ')'!
  | value
;
value
  : INT
  | ID
;
assignment
  : ID ':='^ expression
; 
