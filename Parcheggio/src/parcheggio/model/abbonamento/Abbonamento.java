package parcheggio.model.abbonamento;

import java.time.LocalDate;

import parcheggio.model.persona.Persona;

public class Abbonamento implements abb {
	
	/*
	 * fields 
	 */
	
	private final String           targa;
	private final Persona          persona;
	private final LocalDate        dataInizio;
	private final LocalDate	       dataFine;
	private final static boolean   premium = false;
	
	/*
	 * constructor 
	 */
	
	public Abbonamento(final String    targa,
					   final Persona   pers,
					   final LocalDate dataIn,
					   final LocalDate dataFine,
					         boolean   premium) {
		this.targa	    = targa;
		this.persona 	= pers;
		this.dataInizio = dataIn;
		this.dataFine   = dataFine;
		     premium    = true;
	}
	
	public Abbonamento(final String    targa,
					   final Persona   pers,
					   final LocalDate dataIn,
					   final LocalDate dataFine) {
		this(targa, pers, dataIn, dataFine, premium);
	}
	
	/*
	 * selectors
	 */

	public String getTarga() {
		return this.targa;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public LocalDate getDataInizio() {
		return this.dataInizio;
	}

	public LocalDate getDataFine() {
		return this.dataFine;
	}
	
	public static boolean isPremium() {
		return premium;
	}

	public String toString() {
		return "Targa: "        + this.targa +
				"Persona: "     + this.persona.toString() +
				"Data inizio: " + this.dataInizio + 
				"Data fine: "   + this.dataFine;
	}
}
