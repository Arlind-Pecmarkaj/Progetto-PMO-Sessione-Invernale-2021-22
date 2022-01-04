package parcheggio.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import parcheggio.model.*;
import parcheggio.model.Parcheggio;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.persona.Persona;

public class ReaderParcheggio implements Reader<GestioneParcheggio> {
	
	private BufferedReader reader;
	private final String pathName;
	
	public ReaderParcheggio(String path) throws FileNotFoundException {
		this.pathName = path;
		this.reader = new BufferedReader(new FileReader(this.pathName));
	}
	
	@Override
	public GestioneParcheggio read() {
		/* La formattazione del file �
		 * <NR Parcheggi>
		 * <Id1> <Nome> <Nr posti auto parcheggio 1> <Nr posti moto parcheggio 1> <nr monopattini>
		 * ...
		 * <Nr Abbonamenti>
		 * <Targa> <Codice Fiscale> <Nome> <Cognome> <Data Nascita> <Nazione> <Data Inizio> <Data Fine>
         * ...
		 */
		GestioneParcheggio gestione = new GestioneParcheggio();
		try {
			
			int nrParcheggi = 0;
			int id;
			String nome;
			int nrPostiAuto = 0;
			int nrPostiMoto = 0;
			int nrMonopattini = 0;
			
			String input = this.reader.readLine();
			if (input != null) {
				nrParcheggi = Integer.parseInt(input);
				System.out.println("NR PARCHEGGI: " + nrParcheggi);
				// gestioneParcheggi = new GestioneParcheggi(nrParcheggi);
				for (int i = 0; i < nrParcheggi; i++) {
					input = this.reader.readLine();
					String[] splittedInput = input.split("\\s+");
					id = Integer.parseInt(splittedInput[0]);
					nome = splittedInput[1];
					nrPostiAuto = Integer.parseInt(splittedInput[2]);
					nrPostiMoto = Integer.parseInt(splittedInput[3]);
					nrMonopattini = Integer.parseInt(splittedInput[4]);
					gestione.aggiungiParcheggio(new Parcheggio(id, nome, nrPostiAuto, nrPostiMoto, nrMonopattini));
				}
				
				// Leggo il numero di abbonamenti;
				input = this.reader.readLine();
				int nrAbbonamenti = Integer.parseInt(input);
				
				// Variabili temporanee per la memorizzazione di una riga e per la creazione di un abbonamento
				String    targa;
				String    codiceFiscale;
				String    nomePers;
				String    cognome;
				LocalDate dataNascita;
				String    nazione;
				LocalDate dataInizio;
				LocalDate dataFine;
				
				for (int i = 0; i < nrAbbonamenti; i++) {
					// Leggo la riga e separo i campi che mi servono.
					input = this.reader.readLine();
					String[] splittedInput = input.split("\\s+");
					
					targa         = splittedInput[0];
					codiceFiscale = splittedInput[1];
					nomePers      = splittedInput[2];
					cognome       = splittedInput[3];
					dataNascita   = LocalDate.parse(splittedInput[4]);
					nazione       = splittedInput[5];
					dataInizio    = LocalDate.parse(splittedInput[6]);
					dataFine      = LocalDate.parse(splittedInput[7]);
					Abbonamento a = new Abbonamento(targa, new Persona(codiceFiscale, nomePers, cognome, dataNascita, nazione), dataInizio, dataFine);
					gestione.aggiungiAbbonamento(a);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return gestione;
	}

	@Override
	public void write(GestioneParcheggio g) {
		// TODO
	}

}
