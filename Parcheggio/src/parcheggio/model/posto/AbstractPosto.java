package parcheggio.model.posto;

import java.time.*;
import java.time.format.*;
import java.util.Optional;

import parcheggio.enumerations.Alimentazione;
import parcheggio.exceptions.NonElettricaException;
import parcheggio.model.veicolo.Veicolo;

/**
 * 	
 * 	Classe astratta generica che implementa il contratto definito dall'interfaccia Posto
 * 	e quindi si preoccupa di implementare tutti i metodi che saranno comuni alle sue sottoclassi
 *  Inoltre, definiamo anche i campi comuni.
 * 	
 */

public abstract class AbstractPosto implements Posto {
	
	protected static final int STANDARD_TAX = 1; // tutti i "posti" hanno tassa fissa 1 euro
	
	private Optional<Veicolo> veicolo;
	private Instant orarioArrivo;
	private Instant orarioUscita;
	private Duration elapsedTime;
	private String id = "";
	private double costoOrario = 0.0;
	
	/**
	 * 	templete method FINAL per le sottoclassi
	 */
	protected final void setPosto(String postoId) {
		this.id = setId(postoId);
		this.costoOrario = setCostoOrario();
		this.veicolo = Optional.empty();
	}

	
	/**
	 * 	metodi astratti che devono essere implementati dalle sottoclassi
	 */

	protected abstract String setId(String postoId);
	protected abstract double setCostoOrario();

	
	/**
	 * 	metodi getters
	 */
	
	public Optional<Veicolo> getVeicolo() {
		return this.veicolo;
	}
	
	public Instant getOrarioArrivo() {
		return this.orarioArrivo;
	}

	public Instant getOrarioUscita() {
		return this.orarioUscita;
	}

	public String getId() {
		return this.id;
	}
	
	public double getCostoOrario() {
		return this.costoOrario;
	}
	
	
	/**
	 * 	metodi setters
	 */
	
	private void setVeicolo(Veicolo veicolo) {
		this.veicolo = Optional.of(veicolo);
	}
	
	private void setOrarioArrivo(Instant orarioArrivo) {
		this.orarioArrivo = orarioArrivo;
	}
	
	private void setOrarioUscita(Instant orarioUscita) {
		this.orarioUscita = orarioUscita;
	}
	
	
	
	/****************************************************
	 * 	METODI DELL'INTERFACCIA Posto DA IMPLEMENTARE	*
	 ****************************************************/
	

	public final void occupaPosto(Veicolo v) throws NonElettricaException {
		if(this instanceof PostoElettrico && v.getCarburante() != Alimentazione.ELETTRICA) throw new NonElettricaException("Possonon accedere solo veicoli elettrici.");
		
		setVeicolo(v);
		setOrarioArrivo(Instant.now());
	}

	public final void liberaPosto() {
		this.veicolo = Optional.empty();
		setOrarioUscita(Instant.now());
	}
	
	public final boolean isLibero() {
		return !(this.veicolo.isPresent());
	}

	public final Duration tempoOccupazione() throws NullPointerException {
		if(getOrarioArrivo() == null || getOrarioUscita() == null) throw new NullPointerException();
			
		elapsedTime = Duration.between(getOrarioArrivo(), getOrarioUscita());		
		return elapsedTime;
	}

	public final double costoOccupazione() {
		double daPagare = 0.0;
		try {			
			daPagare = this.getCostoOrario() * this.tempoOccupazione().getSeconds();
		} catch(NullPointerException ex) {
			System.out.println(ex);
		}
		return daPagare;
	}

	
	
	/*********************
	 * 	UTILITY METHODS	 *
	 *********************/
	
	@Override
	public String toString() {
		return 	"Posto " + this.getId() 
				+ " con tariffa " + this.getCostoOrario() 
				+ (this.veicolo.isPresent() ? 
						", occupato con orario di arrivo: " + this.getOrarioArrivo() : 
						", libero con ultimo orario di uscita: " + this.getOrarioUscita());
	}
	

	public String orarioToString(boolean option) { // true: orario arrivo; false: orario uscita;
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault());
		if(option) return formatter.format(getOrarioArrivo());
		else return formatter.format(getOrarioUscita());
	}
	
	public String elapsedToString() {
		tempoOccupazione();
		return String.format("%02d:%02d:%02d", this.elapsedTime.toHours(), this.elapsedTime.toMinutes(), this.elapsedTime.toSeconds());
	}
}
