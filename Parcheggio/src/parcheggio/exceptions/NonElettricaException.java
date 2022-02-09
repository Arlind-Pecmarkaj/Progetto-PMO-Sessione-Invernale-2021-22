package parcheggio.exceptions;


/**
 *  Eccezione lanciata all'interno del metodo occupaPosto
 *  nella classe AbstractPosto.
 *  Utilizzata per catturare l'evento che si verifica
 *  quando un veicolo non elettrico parcheggia in un posto 
 *  riservato a veicoli elettrici.
 * 
 */

public class NonElettricaException extends RuntimeException{

	private static final long serialVersionUID = 2056825932544738166L;
	
	public NonElettricaException(String msg) {
		super(msg);
	}

}
