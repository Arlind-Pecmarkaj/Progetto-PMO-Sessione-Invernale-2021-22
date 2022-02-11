package parcheggio.model.persona;

import java.time.LocalDate;

public class Persona implements Pers {
	
	private final String    codFiscale;
	private final String    nome;
	private final String    cognome;
	private final LocalDate dataNascita;
	private final String    nazione;
	
	// costruttore 
	public Persona(final String    codFiscale,
				   final String    nome,
				   final String    cognome,
				   final LocalDate dataNascita,
				   final String    nazione) {
		this.codFiscale  = codFiscale;
		this.nome        = nome;
		this.cognome	 = cognome;
		this.dataNascita = dataNascita;
		this.nazione     = nazione;
		illegalArgumentPersona();	
	}
	
	// illegal arguments 
	public void illegalArgumentPersona() {
		if(codFiscale.isBlank() || codFiscale.isEmpty()|| codFiscale == null || codFiscale.length() != 16)
				throw new IllegalArgumentException("Codice Fiscale");
		
		if(nome.isBlank() || nome.isEmpty() || nome == null || nome.length() <= 2)
			throw new IllegalArgumentException("Nome");
		
		if(cognome.isBlank() || cognome.isEmpty() || cognome == null || cognome.length() <= 2)
			throw new IllegalArgumentException("Cognome");
		
		if( dataNascita == null || dataNascita.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Data di nascita");
		
		if( nazione.isBlank() || nazione.isEmpty() || nazione == null)
			throw new IllegalArgumentException("Nazione");
	}
	
	/*
	 * selectors 
	 */
	
	public String getCodFiscale() {
		return this.codFiscale;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public LocalDate getDataNascita() {
		return this.dataNascita;
	}
	
	public String getNazione() {
		return this.nazione;
	}
	
	public String toString() {
		return "Codice fiscale: "   + this.codFiscale  + " " +
			   " Nome: "            + this.nome        + " " + 
			   " Cognome: "         + this.cognome     + " " + 
			   " Data di nascita: " + this.dataNascita + " " + 
			   " Nazione: "         + this.nazione;
	}
	
	/*
	 * hash code and equals 
	 */
	
	// il codice fiscale e' univoco
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFiscale == null) ? 0 : codFiscale.hashCode());
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
		Persona other = (Persona) obj;
		if (codFiscale == null) {
			if (other.codFiscale != null)
				return false;
		} else if (!codFiscale.equals(other.codFiscale))
			return false;
		return true;
	}
}
