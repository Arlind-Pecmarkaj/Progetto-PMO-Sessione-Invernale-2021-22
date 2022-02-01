package parcheggio.model.posto;

import java.time.Duration;
import parcheggio.model.veicolo.Veicolo;

/**
 * 
 * 	@author tomma
 * 
 * 	Interfaccia generica per rappresentare il concetto di "posto veicolo" inteso come spazio
 * 	disponibile per parcheggiare un veicolo. Tale interfaccia deve garantire le seguenti 
 * 	funzionalità fondamentali:
 * 		- tenere traccia dei movimenti del veicolo
 * 		- segnalare se un posto è libero o meno
 * 		- fornire il tempo di permanenza del veicolo nel posto
 * 
 */
public interface Posto {
	
	/**
	 * 	Associa un veicolo ad un posto salvando l'orario di arrivo del veicolo
	 */
	void occupaPosto(Veicolo v);
	
	/**
	 * 	Il veicolo viene disassociato dal posto che occupava
	 */
	void liberaPosto();

	/**
	 * 	Verifica se un posto è libero o occupato.
	 *
	 * 	@return un valore booleano che mi dice se un posto è libero (true) o occupato (false)
	 */
	boolean isLibero();
	
	/**
	 * 	Calcola il tempo di occupazione del posto occupato
	 * 
	 * 	@return tempo di occupazione del posto
	 */
	Duration tempoOccupazione();
	
	/**
	 * 
	 * 
	 * */
	double costoOccupazione();
}
