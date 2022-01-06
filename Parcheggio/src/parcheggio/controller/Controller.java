package parcheggio.controller;

import java.util.Optional;

import parcheggio.model.GestioneParcheggio;

public class Controller {
	private Optional<GestioneParcheggio>      gp;
	private ReaderWriterFromFile readerAndWriter;
	
	public Controller(String path) {
		this.readerAndWriter = new ReaderWriterFromFile(path);
	}
	
	public void readFromFile() {
		this.gp = Optional.of(readerAndWriter.read());
	}
	
	public void writeFromFile() {
		if(gp.isPresent())
			this.readerAndWriter.write(gp.get());
	}
	
	public GestioneParcheggio getGestioneParcheggio() {
		return this.gp.get();
	}
	

}
