package parcheggio.model.abbonamento;

import java.sql.Date;

import parcheggio.model.persona.Persona;

public interface abb {
	
	/*
	 * methods 
	 */
	
	// return della targa veicolo 
	String getTarga();
	
	// return della persona 
	Persona getPersona();
	
	// return della data d'inizio
	Date getDataInizio();
	
	// return della data di scadenza
	Date getDataFine();
}
