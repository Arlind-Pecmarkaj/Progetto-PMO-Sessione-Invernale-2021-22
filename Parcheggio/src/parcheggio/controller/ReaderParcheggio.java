package parcheggio.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import parcheggio.model.*;

public class ReaderParcheggio implements Reader<GestioneParcheggio> {
	
	private BufferedReader reader;
	private final String pathName;
	
	public ReaderParcheggio(String path) throws FileNotFoundException {
		this.pathName = path;
		this.reader = new BufferedReader(new FileReader(this.pathName));
	}
	
	@Override
	public GestioneParcheggio read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(GestioneParcheggio t) {
		// TODO Auto-generated method stub
		
	}

}
