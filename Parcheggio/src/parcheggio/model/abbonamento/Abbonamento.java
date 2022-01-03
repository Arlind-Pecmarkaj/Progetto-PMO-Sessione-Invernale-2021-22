package parcheggio.model.abbonamento;

import java.sql.Date;

import parcheggio.model.persona.Persona;

public class Abbonamento implements abb {
	
	/*
	 * fields 
	 */
	
	private final String  targa;
	private final Persona persona;
	private final Date    dataInizio;
	private final Date	  dataFine;
	
	/*
	 * constructor 
	 */
	
	public Abbonamento(final String  targa,
					   final Persona pers,
					   final Date    dataIn,
					   final Date    dataFine) {
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

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public Date getDataFine() {
		return this.dataFine;
	}
	
	public String toString() {
		return "Targa: "        + this.targa +
				"Persona: "     + this.persona.toString() +
				"Data inizio: " + this.dataInizio + 
				"Data fine: "   + this.dataFine;
	}
}
