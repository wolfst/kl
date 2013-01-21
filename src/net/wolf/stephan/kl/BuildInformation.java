package net.wolf.stephan.kl;

public class BuildInformation {
	
	final static String version = "1.00";
	private static void printInfo(){
		System.out.println("Copyright (C) 2013 Stephan Wolf <wolfst@in.tum.de>");
		System.out.println();
		System.out.println("This is free software; see the source for copying conditions.  There is NO");
		System.out.println("warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.");
		System.out.println();
	}
	public static void printCompilerInfo(){
		System.out.println("KL Compiler -  Version " + version);
		printInfo();
	}
	public static void printInterpreterInfo() {
		System.out.println("KL Interpreter -  Version " + version);
		printInfo();
	}
	

}
