KL Interpreter and Compiler
=======================================

This is a simple compiler and interpreter for a simple language called KL.
In a nutshell, KL supports integer variables, if-then-else statements and while-loops. See the grammar file for a detailed description.
By convention, the first parameter passed to a KL program is saved in the variable 'a' and the program will return the value of the variable 'x' as result.

The compiler and interpreter use the ANTLR framework for parsing and compilation into binary code is achieved via the LLVM framework and GCC.

Example KL program
--------

	// The first paramater provided to the program
	// will be saved in variable a
	x:=a;
	
	// Calculate x!
	y:= 1;
	while x>0 do
	y:= y*x; 
	x:= x-1
	od;
	
	// Return the result to variable x
	// so it will be printed
	x:= y


Download
--------

Here you can download directly the compiled [compiler](https://github.com/wolfst/kl/blob/master/klc.jar?raw=true) and [interpreter](https://github.com/wolfst/kl/blob/master/kli.jar?raw=true).

How to run the interpreter:

	java -jar kli.jar <input_file> <value_for_variable_a>
	
How to run the compiler:

	java -jar klc.jar --optimize <input_file> -o <output_file> && ./<output_file> <value_for_variable_a>

_Note that you have to install the LLVM framework before running!_

Tested on Mac OS X 64bit with LLVM 3.1.


LICENSE
-------

	KL Interpreter and Compiler
	Copyright (C) 2013 Stephan Wolf

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
	
	
LIBRARIES
---------

JUnit (junit-4.10) is licensed under the Common Public License v. 1.0
See http://junit.sourceforge.net/cpl-v10.html

ANTLR (antlr-runtime-3.5) is licensed under the ANTLR 3 License
See http://www.antlr.org/license.html
