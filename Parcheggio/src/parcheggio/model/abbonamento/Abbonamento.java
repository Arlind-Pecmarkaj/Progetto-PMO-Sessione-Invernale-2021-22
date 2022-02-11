package parcheggio.model.abbonamento;

import java.time.LocalDate;

import parcheggio.model.persona.Persona;

public class Abbonamento implements abb {
	
	/*
	 * fields 
	 */
	
	private final int              id;
	private final String           targa;
	private final Persona          persona;
	private final LocalDate        dataInizio;
	private final LocalDate	       dataFine;
	private boolean                premium;
	
	/*
	 * constructor 
	 */
	
	public Abbonamento(final int       id,
					   final String    targa,
					   final Persona   pers,
					   final LocalDate dataIn,
					   final LocalDate dataFine,
					   final boolean   premium) {
		this.id         = id;
		this.targa	    = targa;
		this.persona 	= pers;
		this.dataInizio = dataIn;
		this.dataFine   = dataFine;
		this.premium    = premium;
		illegalArgumentAbbonamento();
	}
	
	public Abbonamento(final int       id,
					   final String    targa,
					   final Persona   pers,
					   final LocalDate dataIn,
					   final LocalDate dataFine) {
		this(id,targa, pers, dataIn, dataFine, false);
	}
	
	// illegal arguments 
	public void illegalArgumentAbbonamento() {
		if(targa.isBlank() || targa.isEmpty() || targa == null ||
		   dataInizio == null || dataFine == null)
			throw new IllegalArgumentException("campi vuoti!");
		
		if(targa.length() != 7)
			throw new IllegalArgumentException("Targa");
	}
	
	/*
	 * selectors
	 */
	
	public int getId() {
		return this.id;
	}

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
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean update) {
		this.premium = update;
	}
	
	public String toString() {
		return 	"Id: "           + this.id + 
				" Targa: "       + this.targa +
				" Persona: "     + this.persona.toString() +
				" Data inizio: " + this.dataInizio + 
				" Data fine: "   + this.dataFine;
	}
	
	/*
	 * hash code and equals 
	 */
	
	// l'id dell'abbonamento e' univoco 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abbonamento other = (Abbonamento) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
