package net.wolf.stephan.kl.compiler;

public class Global {
	static int id;
	static int getNextID(){
		return (++id);
	}
	static String getNextRegister(){
		return "%x"+(++id);
	}

}
