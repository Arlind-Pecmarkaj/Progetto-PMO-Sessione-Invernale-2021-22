package parcheggio.enumerations;

/**
 *  Questo tipo enumeratore mi definisce un coefficiente
 *  che viene utilizzato per definire una tassa relativa 
 *  alla tipologia di parcheggio
 *  
 */

public enum TassaParcheggio {
							// se la tassa fissa del parcheggio X è 1 euro...
	TASSA_AUTO(0.8),		// => 1,25 euro all'ora			
	TASSA_MOTO(2.0),		// => 0,50 euro all'ora
	TASSA_ELETTRICO(0.4);	// => 2,50 euro all'ora
	
	
	private final double taxValue;
	
	TassaParcheggio(final double taxValue) {
		this.taxValue = taxValue;
	}
	
	public double getTaxValue() {
		return this.taxValue;
	}
}
