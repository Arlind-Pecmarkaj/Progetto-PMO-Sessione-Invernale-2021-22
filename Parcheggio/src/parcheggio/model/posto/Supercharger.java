package parcheggio.model.posto;
import parcheggio.model.veicolo.*;
/**
 * 
 * 	@author tomma
 * 
 * 	Interfaccia per rappresentare il concetto di Supercharger, 
 * 	ossia un dispositivo di ricarica per auto elettriche.
 * 	Tale interfaccia deve garantire le seguenti funzionalità fondamentali:
 * 		- dare informazioni circa le caratteristiche del Supercharger
 * 		- dare informazioni riguardanti la ricarica dell'auto elettrica
 * 		- interagire con l'auto
 * 
 */

public interface Supercharger {
	
	/**
	 * 
	 * 	Permette di conoscere quanti kWh (kiloWatt ora) il Supercharger
	 * 	e' capace di erogare.
	 * 
	 * 	@return quantita' di kW (kiloWatt) prodotti per ora
	 */
	double getkWh();
	
	/**
	 * 
	 * 	Permette di sapere in quanto tempo (misurato in ore)
	 *  l'auto elettrica verrà ricaricata
	 *
	 * 	@return durata della ricarica in ore
	 */
	double getTempoRicarica();
	
	/**
	 * 
	 *  Permette di ricaricare il veicolo che occupa il parcheggio
	 *  dando la possibilità di ricaricare fino alla 
	 *  percentuale di ricarica desiderata
	 *  
	 *  @param daRaggiungere - percentuale di ricarica da raggiungere
	 *  @param daRicaricare - il veicolo da ricare
	 */
	void ricaricaVeicolo(int daRaggiungere, Veicolo daRicaricare);
}
