package parcheggio.model.monopattino;

public class Monopattino {
	private boolean disponibile;
	private long oraNoleggiato = System.currentTimeMillis();
	private long fineNoleggio;
	static final public double COSTO = 5.0;
	
	public Monopattino() {
		this.disponibile = true;
	}
	
	public boolean getDisponibile() {
		return this.disponibile;
	}

	public long getOraNoleggiato() {
		return oraNoleggiato;
	}

	public void setOraNoleggiato(long oraNoleggiato) {
		this.oraNoleggiato = oraNoleggiato;
	}

	public long getFineNoleggio() {
		return fineNoleggio;
	}

	public void setFineNoleggio(long fineNoleggio) {
		this.fineNoleggio = fineNoleggio;
	}
}
