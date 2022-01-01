package parcheggio.enumerations;

public enum TassaParcheggio {
							// se la tassa fissa del parcheggio X è 1€...
	TASSA_AUTO(0.8),		// => 1,25€ all'ora			
	TASSA_MOTO(2.0),		// => 0,50€ all'ora
	TASSA_ELETTRICO(0.4);	// => 2,50€ all'ora
	
	
	private final double taxValue;
	
	TassaParcheggio(final double taxValue) {
		this.taxValue = taxValue;
	}
	
	public double getTaxValue() {
		return this.taxValue;
	}
}
