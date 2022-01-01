package parcheggio.model.posto;

import java.time.Instant;
import java.time.Duration;
import java.util.Optional;

// import per secondo metodo
import parcheggio.enumerations.Alimentazione;
import parcheggio.model.sensore.Sensore;
// FINE IMPORT PER SECONDO METODO
import parcheggio.model.veicolo.Veicolo;


/**
 * 	@author tomma
 * 	
 * 	Classe astratta generica che implementa il contratto definito dall'interfaccia IPosto
 * 	e quindi si preoccupa di implementare tutti i metodi che saranno comuni alle sotto-classi
 * 	di APosto. Inoltre, definiamo anche i campi comuni.
 * 	
 * 	@note il carattere A all'inizio del nome della classe viene inteso come Abstract
 */

public abstract class APosto implements IPosto {
	
	private Optional<IVeicolo> veicolo;
	private Optional<Sensore<Alimentazione>> sensoreCarburante;
	private Instant orarioArrivo;
	private Instant orarioUscita;
	private final String id;
	private final double costoOrario;
	
	/**
	 * 	templete method FINAL per le sottoclassi
	 */
	public final void setPosto(String postoId, double standardTax) {
		this.id = setId(postoId);
		this.costoOrario = setCostoOrario(standardTax);
		this.veicolo = Optional.empty();
//		if(this instanceof PostoAuto || this instanceof PostoElettrico)
//			this.sensoreCarburante = Optional.of(setSensoreCarburante());
//		else
//			this.sensoreCarburante = Optional.empty();
	}

	
	/**
	 * 	metodi astratti che devono essere implementati dalle sottoclassi
	 */
//	protected abstract Sensore<Alimentazione> setSensoreCarburante();
	protected abstract String setId(String postoID);
	protected abstract double setCostoOrario(double standardTax);

	
	/**
	 * 	metodi getters
	 */
//	protected Optional<IVeicolo> getVeicolo() {
//		return this.getVeicolo();
//	}
	
	protected Instant getOrarioArrivo() {
		return orarioArrivo;
	}

	protected Instant getOrarioUscita() {
		return orarioUscita;
	}

	public String getId() {
		return this.id;
	}
	
	protected double getCostoOrario() {
		return this.costoOrario;
	}
	
	/**
	 * 	metodi setters
	 */
	protected void setVeicolo(IVeicolo veicolo) {
		this.veicolo = Optional.of(veicolo);
	}
	
	protected void setOrarioArrivo(Instant orarioArrivo) {
		this.orarioArrivo = orarioArrivo;
	}
	
	protected void setOrarioUscita(Instant orarioUscita) {
		this.orarioUscita = orarioUscita;
	}
	
	/****************************************************
	 * 	METODI DELL'INTERFACCIA IPosto DA IMPLEMENTARE	*
	 ****************************************************/
	
	/**
	 * 
	 */
	public final void occupaPosto(Veicolo v) {
		setVeicolo(v);
		this.orarioArrivo = Instant.now();
	}
	
	/**
	 * 
	 */
	public final void liberaPosto() {
		this.veicolo = Optional.empty();
		this.orarioUscita = Instant.now();
	}
	
	/**
	 * 	Verifica se un posto è libero o occupato.
	 *
	 * 	@return un valore booleano che mi dice se un posto è libero (true) o occupato (false)
	 */
	public final boolean isLibero() {
		return this.veicolo.isPresent();
	}
	
	/**
	 * 	Calcola il tempo di occupazione del posto occupato
	 * 
	 * 	@return tempo di occupazione del posto
	 */
	public final Duration tempoOccupazione() {
		Duration elapsedTime;
		
		elapsedTime = Duration.between(getOrarioArrivo(), getOrarioUscita());
		
		return elapsedTime;
	}
}
