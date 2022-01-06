package parcheggio.controller;

import java.io.IOException;

public interface ReaderWriter <T> {
	
	public T read() throws IOException;
	public void write(T t);
	
}
