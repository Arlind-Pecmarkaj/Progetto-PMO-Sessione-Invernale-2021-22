package parcheggio.model.monopattino;

/* import utilizzati: */
import java.time.Instant;
import parcheggio.model.persona.Pers;
import parcheggio.model.persona.Persona;

public class MonopattinoImpl implements Monopattino{
	final private String id;
	private boolean disponibile;
	private Instant oraNoleggiato; //= System.currentTimeMillis();
	private Instant fineNoleggio;
	private Pers persona;
	static final public double COSTO = 0.10;
	
	public MonopattinoImpl(int index) {
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
	
	public void setPersona(Pers p) {
		this.persona = (Persona) p;
	}
	
	public Pers getPersona() {
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
		MonopattinoImpl other = (MonopattinoImpl) obj;
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
