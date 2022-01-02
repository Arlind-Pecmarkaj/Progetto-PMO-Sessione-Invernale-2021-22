package parcheggio.model.persona;

import java.sql.Date;

public class Persona implements Pers {
	
	/*
	 * fields 
	 */
	
	private final String codFiscale;
	private final String nome;
	private final String cognome;
	private final Date   dataNascita;
	private final String nazione;
	
	/*
	 * constructor 
	 */
	
	public Persona(final String codFiscale,
				   final String nome,
				   final String cognome,
				   final Date   dataNascita,
				   final String nazione) {
		this.codFiscale  = codFiscale;
		this.nome        = nome;
		this.cognome	 = cognome;
		this.dataNascita = dataNascita;
		this.nazione     = nazione;
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
	
	public Date getDataNascita() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFiscale == null) ? 0 : codFiscale.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((nazione == null) ? 0 : nazione.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (nazione == null) {
			if (other.nazione != null)
				return false;
		} else if (!nazione.equals(other.nazione))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
