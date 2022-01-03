package parcheggio.model.abbonamento;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import parcheggio.model.persona.Persona;

public class Abbonamento {
	
	/*
	 * fields 
	 */
	private final Set<Persona> abbonati = new HashSet<>();
	
	public void aggiungiAbb(final String codFiscale,
							final String nome,
							final String cognome,
							final Date   dataNascita,
							final String nazione) {
		this.abbonati.add(new Persona(codFiscale, 
									  nome, cognome,dataNascita,nazione));
	}
	
	public void rimuoviAbb(final String codFiscale,
						   final String nome,
						   final String cognome,
						   final Date   dataNascita,
						   final String nazione) {
		this.abbonati.remove(new Persona(codFiscale, 
										 nome, cognome,dataNascita,nazione));
	}
}
