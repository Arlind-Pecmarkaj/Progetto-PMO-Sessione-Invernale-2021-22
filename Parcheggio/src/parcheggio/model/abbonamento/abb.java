package parcheggio.model.abbonamento;

import java.time.LocalDate;

import parcheggio.model.persona.Persona;

public interface abb {
	
	/*
	 * methods 
	 */
	
	// return id del veicolo
	int getId();
	
	// return della targa veicolo 
	String getTarga();
	
	// return della persona 
	Persona getPersona();
	
	// return della data d'inizio
	LocalDate getDataInizio();
	
	// return della data di scadenza
	LocalDate getDataFine();
	
	// return abbonamento premium
	boolean isPremium();

}
