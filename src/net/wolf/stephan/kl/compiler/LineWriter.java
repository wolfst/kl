package net.wolf.stephan.kl.compiler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class LineWriter extends Writer {
	
	BufferedWriter writer;

	
	
	public LineWriter(BufferedWriter out) {
		writer = out;
	}

	public void writeLn(String str) throws IOException {
		writer.write(str);
		writer.newLine();
	}

	@Override
	public void close() throws IOException {
		writer.close();
		
	}

	@Override
	public void flush() throws IOException {
		writer.flush();
		
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		writer.write(cbuf, off, len);
		
	}


}
