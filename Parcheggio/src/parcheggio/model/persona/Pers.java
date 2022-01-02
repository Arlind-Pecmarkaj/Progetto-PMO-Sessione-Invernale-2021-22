package parcheggio.model.persona;

import java.sql.Date;

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
	Date getDataNascita();
	
	// return della nazione 
	String getNazione();
}
