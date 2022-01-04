package parcheggio.model.abbonamento;

import java.time.LocalDate;

import parcheggio.model.persona.Persona;

public class Abbonamento implements abb {
	
	/*
	 * fields 
	 */
	
	private final String    targa;
	private final Persona   persona;
	private final LocalDate dataInizio;
	private final LocalDate	dataFine;
	
	/*
	 * constructor 
	 */
	
	public Abbonamento(final String    targa,
					   final Persona   pers,
					   final LocalDate dataIn,
					   final LocalDate dataFine) {
		this.targa	    = targa;
		this.persona 	= pers;
		this.dataInizio = dataIn;
		this.dataFine   = dataFine;
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
	
	public String toString() {
		return "Targa: "        + this.targa +
				"Persona: "     + this.persona.toString() +
				"Data inizio: " + this.dataInizio + 
				"Data fine: "   + this.dataFine;
	}
}
