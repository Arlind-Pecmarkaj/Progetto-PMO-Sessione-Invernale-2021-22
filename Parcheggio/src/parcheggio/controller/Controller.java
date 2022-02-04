package parcheggio.controller;

import java.io.IOException;
import java.util.Optional;

import parcheggio.model.GestioneParcheggio;
import parcheggio.view.GUI.GUIGestione;

public class Controller {
	private Optional<GestioneParcheggio>      gp;
	private ReaderWriterFromFile readerAndWriter;

	public Controller(String path) {
		this.readerAndWriter = new ReaderWriterFromFile(path);
	}
	
	public void start() throws IOException {
		this.readFromFile();
		if (this.gp.isPresent() && !(this.gp.get().getParcheggi().isEmpty())) {
			new GUIGestione(gp.get(), readerAndWriter);
		} else {
			throw new IOException("Problema nell'apertura del file o nella creazione dei parcheggi.");
		}
	}
	
	public void readFromFile() {
		this.gp = Optional.of(readerAndWriter.read());
	}
	
	public void writeFromFile() {
		if (gp.isPresent())
			this.readerAndWriter.write(gp.get());
	}
	
	public GestioneParcheggio getGestioneParcheggio() {
		return this.gp.get();
	}
	

}
