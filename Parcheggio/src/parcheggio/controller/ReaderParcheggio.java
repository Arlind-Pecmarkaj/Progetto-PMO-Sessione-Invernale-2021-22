package parcheggio.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		/* La formattazione del file è
		 * <NR Parcheggi>
		 * <Id1> <Nome> <Nr posti auto parcheggio 1> <Nr posti moto parcheggio 1>
		 * <Id2> <Nome> <Nr Posti auto parcheggio 2> <Nr posti moto parcheggio 2>
		 * ...
		 * <Idn> <Nome> <Nr Posti auto parcheggio n> <Nr posti moto parcheggio n>
		 * <Nr Abbonamenti>
		 * <Id Utente> <Targa>
		 * <Id Utente> <Targa>
		 * ...
		 * <Id Utente> <Targa>
		 */
		int nrParcheggi = 0;
		String id;
		int nrPostiAuto = 0;
		int nrPostiMoto = 0;
		
		try {
			String input = this.reader.readLine();
			if (input != null) {
				nrParcheggi = Integer.parseInt(input);
				System.out.println("NR PARCHEGGI: " + nrParcheggi);
				// gestioneParcheggi = new GestioneParcheggi(nrParcheggi);
				for (int i = 0; i < nrParcheggi; i++) {
					input = this.reader.readLine();
					String[] splittedInput = input.split("\\s+");
					id = splittedInput[0];
					nrPostiAuto = Integer.parseInt(splittedInput[1]);
					nrPostiMoto = Integer.parseInt(splittedInput[2]);
					System.out.println("ID " + id + " | posti auto " + nrPostiAuto + " | posti moto " + nrPostiMoto);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void write(GestioneParcheggio t) {
		// TODO Auto-generated method stub
		
	}

}
