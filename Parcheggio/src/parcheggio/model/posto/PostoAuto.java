package parcheggio.model.posto;

import parcheggio.enumerations.Alimentazione;
import static parcheggio.enumerations.TassaParcheggio.*;
import parcheggio.model.sensore.Sensore;

public class PostoAuto extends AbstractPosto {
	
	private Sensore<Alimentazione> sensoreCarburante;
	
	public PostoAuto(Sensore<Alimentazione> sensore) {
		this.sensoreCarburante = sensore;
	}
	
	/**
	 * 	metodo getter per accedere al sensore relativo ad un posto
	 */
	public Sensore<Alimentazione> getSensoreCarburante() {
		return this.sensoreCarburante;
	}
	
	// SECONDO METODO
	protected Sensore<Alimentazione> setSensoreCarburante() {
		return null;
		
	}
	
	/**
	 * 	Compone una parte variabile univoca con il codice "A"
	 * 	che sta ad indicare che il posto è riservato alle auto.
	 * 	L'ID di un posto corrisponde con il numero suo numero.
	 * 	
	 * 	@param postoAutoID
	 * 	@return l'ID composto di un posto per auto
	 */
	@Override
	protected String setId(String postoAutoID) {
		return "A" + postoAutoID;
	}

	/**
	 * 	Calcola la tariffa oraria di questo posto in un certo parcheggio.
	 * 
	 * 	@param standardTax
	 * 	@return tariffa oraria per auto
	 */
	@Override
	protected double setCostoOrario(double standardTax) {
		return standardTax / TASSA_AUTO.getTaxValue();
	}
}
