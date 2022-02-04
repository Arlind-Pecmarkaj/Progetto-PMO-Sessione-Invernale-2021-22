package parcheggio.model.monopattino;

import java.time.Instant;

import parcheggio.model.persona.Persona;

public class Monopattino {
	final private String id;
	private boolean disponibile;
	private Instant oraNoleggiato; //= System.currentTimeMillis();
	private Instant fineNoleggio;
	private Persona persona;
	static final public double COSTO = 0.10;
	
	public Monopattino(int index) {
		this.id = Integer.toString(index);
		this.disponibile = true;
	}
	
	public boolean getDisponibile() {
		return this.disponibile;
	}

	public Instant getOraNoleggiato() {
		return oraNoleggiato;
	}
	
	public void setDisponibile(boolean d) {
		this.disponibile = d;
	}

	public void setOraNoleggiato(Instant oraNoleggiato) {
		this.oraNoleggiato = oraNoleggiato;
	}

	public Instant getFineNoleggio() {
		return fineNoleggio;
	}

	public void setFineNoleggio(Instant instant) {
		this.fineNoleggio = instant;
	}
	
	public void setPersona(Persona p) {
		this.persona = p;
	}
	
	public Persona getPersona() {
		return this.persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Monopattino other = (Monopattino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Monopattino [id=" + id + ", disponibile=" + disponibile + "]";
	}
	
}
