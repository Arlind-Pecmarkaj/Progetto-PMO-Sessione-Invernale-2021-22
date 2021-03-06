package parcheggio.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import parcheggio.model.*;
import parcheggio.model.ParcheggioImpl;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.persona.Persona;
import parcheggio.model.posto.*;

public class ReaderWriterFromFile implements ReaderWriter<GestioneParcheggio> {
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private final String pathName;
	
	public ReaderWriterFromFile(String path) {
		this.pathName = path;
	}
	
	@Override
	public GestioneParcheggio read() {
		/* Formattazione del file:
		 * <NR Parcheggi>
		 * <ID> <nome> <nr posti auto> <nr posti moto> <nr posti elettrico> <nr monopattini> <altezza max>
		 * ...
		 * <Nr Abbonamenti>
		 * <Targa> <Codice Fiscale> <Nome> <Cognome> <Data Nascita> <Nazione> <Data Inizio> <Data Fine>
         * ...
		 */
		GestioneParcheggio gestione = new GestioneParcheggio();
		try {
			this.reader = new BufferedReader(new FileReader(this.pathName));
			/* Variabili temporanee per salvare i dati di ogni parcheggio. */
			int   nrParcheggi;
			String         id;
			String       nome;
			int   nrPostiAuto;
			int   nrPostiMoto;
			int   nrPostiElet;
			int nrMonopattini;
			double    altezza;
			
			String input = this.reader.readLine();
			if (input != null) {
				nrParcheggi = Integer.parseInt(input);
				for (int i = 0; i < nrParcheggi; i++) {
					input = this.reader.readLine();
					String[] splittedInput = input.split("\\s+"); // Ad ogni lettura il Buffered reader legge ogni riga dunque devo separarla.
					id            = splittedInput[0];
					nome          = splittedInput[1];
					nrPostiAuto   = Integer.parseInt(splittedInput[2]);
					nrPostiMoto   = Integer.parseInt(splittedInput[3]);
					nrPostiElet   = Integer.parseInt(splittedInput[4]);
					nrMonopattini = Integer.parseInt(splittedInput[5]);
					altezza       = Double.parseDouble(splittedInput[6]);
					gestione.aggiungiParcheggio(new ParcheggioImpl(id, nome, nrPostiAuto, nrPostiMoto, nrPostiElet, nrMonopattini, altezza));
				}
				
				// Leggo il numero di abbonamenti;
				input = this.reader.readLine();
				int nrAbbonamenti = Integer.parseInt(input);
				
				// Variabili temporanee per la memorizzazione di una riga e per la creazione di un abbonamento
				int       idAbb;
				String    targa;
				String    codiceFiscale;
				String    nomePers;
				String    cognome;
				LocalDate dataNascita;
				String    nazione;
				LocalDate dataInizio;
				LocalDate dataFine;
				Boolean   premium;
				
				for (int i = 0; i < nrAbbonamenti; i++) {
					// Leggo la riga e separo i campi che mi servono.
					input = this.reader.readLine();
					String[] splittedInput = input.split("\\s+");
					
					idAbb         = Integer.parseInt(splittedInput[0]);
					targa         = splittedInput[1];
					codiceFiscale = splittedInput[2];
					nomePers      = splittedInput[3];
					cognome       = splittedInput[4];
					dataNascita   = LocalDate.parse(splittedInput[5]);
					nazione       = splittedInput[6];
					dataInizio    = LocalDate.parse(splittedInput[7]);
					dataFine      = LocalDate.parse(splittedInput[8]);
					premium       = Boolean.parseBoolean(splittedInput[9]);
					Abbonamento a = new Abbonamento(idAbb, targa, 
							                        new Persona(codiceFiscale, nomePers, cognome, dataNascita, nazione), 
							                        dataInizio, dataFine, premium);
					gestione.aggiungiAbbonamento(a);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Problemi nell'apertura del file: " + e);
		} 
		
		return gestione;
	}

	@Override
	public void write(GestioneParcheggio g) {
		try {
			this.writer = new BufferedWriter(new FileWriter(this.pathName));
			// Scrivo il numero di parcheggi;
			writer.write(g.getParcheggi().size() + "\n");
			for (Parcheggio p : g.getParcheggi()) {
				writer.write(((ParcheggioImpl) p).getId() 
						     + " " + ((ParcheggioImpl) p).getName() 
						     + " " + p.getNPostiSpecifici(posto -> posto instanceof PostoAuto)
						     + " " + p.getNPostiSpecifici(posto -> posto instanceof PostoMoto)
						     + " " + p.getNPostiSpecifici(posto -> posto instanceof PostoElettrico)
						     + " " + ((ParcheggioImpl) p).getPostiMonopattino().size()
						     + " " + ((ParcheggioImpl) p).getAltezzaMassimaConsentita()
						     + "\n");
			}
			writer.write(g.getAbbonamenti().size() + "\n");
			for (Abbonamento a : g.getAbbonamenti()) {
				writer.write(a.getId() 
						     + " " + a.getTarga()
						     + " " + a.getPersona().getCodFiscale()
						     + " " + a.getPersona().getNome()
						     + " " + a.getPersona().getCognome()
						     + " " + a.getPersona().getDataNascita()
						     + " " + a.getPersona().getNazione()
						     + " " + a.getDataInizio()
						     + " " + a.getDataFine()
							 + " " + a.isPremium()
							 + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Errore nell'apertura del file: " + e);
		}
	}

}
