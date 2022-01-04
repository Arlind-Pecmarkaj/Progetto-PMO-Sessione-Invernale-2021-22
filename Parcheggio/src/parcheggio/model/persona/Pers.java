package parcheggio.model.persona;

import java.time.LocalDate;

public interface Pers {
	
	/*
	 * methods 
	 */
	
	// return codice fiscale
	String getCodFiscale();
	
	// return del nome
	String getNome();
	
	// return del cognome
	String getCognome();
	
	// return della data di nascita
	LocalDate getDataNascita();
	
	// return della nazione 
	String getNazione();
}
