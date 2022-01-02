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
 * 	e quindi si preoccupa di implementare tutti i metodi che saranno comuni alle sottoclassi
 * 	di APosto. Inoltre, definiamo anche i campi comuni.
 * 	
 */

public abstract class AbstractPosto implements Posto {
	
	private 
	
	private Optional<Veicolo> veicolo;
	private Instant orarioArrivo;
	private Instant orarioUscita;
	private String id = "";
	private double costoOrario = 0.0;
	
	/**
	 * 	templete method FINAL per le sottoclassi
	 */
	public final void setPosto(String postoId, double standardTax) {
		this.id = setId(postoId);
		this.costoOrario = setCostoOrario(standardTax);
		this.veicolo = Optional.empty();
	}

	
	/**
	 * 	metodi astratti che devono essere implementati dalle sottoclassi
	 */

	protected abstract String setId(String postoID);
	protected abstract double setCostoOrario(double standardTax);

	
	/**
	 * 	metodi getters
	 */
	
	public Instant getOrarioArrivo() {
		return orarioArrivo;
	}

	public Instant getOrarioUscita() {
		return orarioUscita;
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
	
	/**
	 * 	Associa un veicolo ad un posto salvando l'orario di arrivo del veicolo
	 */
	public final void occupaPosto(Veicolo v) {
		setVeicolo(v);
		setOrarioArrivo(Instant.now());
	}
	
	/**
	 * 	Il veicolo viene disassociato dal posto che occupava
	 */
	public final void liberaPosto() {
		this.veicolo = Optional.empty();
		setOrarioUscita(Instant.now());
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
