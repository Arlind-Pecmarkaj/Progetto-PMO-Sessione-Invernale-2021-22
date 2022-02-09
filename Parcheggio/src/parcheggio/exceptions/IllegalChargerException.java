package parcheggio.exceptions;

/**
 *  Eccezione lanciata all'interno del metodo ricaricaVeicolo
 *  nella classe ColonnaSupercharger.
 *  Utilizzata per catturare l'evento che si verifica
 *  quando la percentuale da raggiungere non è valida.
 * 
 */

public class IllegalChargerException extends IllegalStateException {

	private static final long serialVersionUID = 2189138802471653612L;
	
	public IllegalChargerException(String msg) {
		super(msg);
	}
}
