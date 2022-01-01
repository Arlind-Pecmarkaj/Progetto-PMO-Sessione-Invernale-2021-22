package parcheggio.model.posto;

import java.time.LocalDateTime;

/**
 * 
 * 	@author tomma
 * 
 * 	Interfaccia generica per rappresentare il concetto di "posto veicolo" inteso come spazio
 * 	disponibile per parcheggiare un veicolo. Tale interfaccia deve garantire le seguenti 
 * 	funzionalità fondamentali:
 * 		- segnalare se un posto è libero o meno
 * 		- fornire il tempo di permanenza del veicolo nel posto
 */
public interface IPosto {

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
	LocalDateTime tempoOccupazione();
}
