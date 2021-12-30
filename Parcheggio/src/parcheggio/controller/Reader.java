package parcheggio.controller;

public interface Reader <T> {
	
	public T read();
	public void write(T t);
	
}
