package parcheggio.model.posto;

import static parcheggio.enumerations.TassaParcheggio.*;

public class PostoMoto extends AbstractPosto {
	
	
	/**
	 * 	Compone una parte variabile univoca con il codice "M"
	 * 	che sta ad indicare che il posto è riservato alle Moto.
	 * 	L'ID di un posto corrisponde con il numero suo numero.
	 * 	
	 * 	@param postoMotoID
	 * 	@return l'ID composto di un posto per moto
	 */
	@Override
	protected String setId(String postoMotoID) {
		return "M" + postoMotoID;
	}

	/**
	 * 	Calcola la tariffa oraria di questo posto in un certo parcheggio.
	 * 
	 * 	@param standardTax
	 * 	@return tariffa oraria per moto
	 */
	@Override
	protected double setCostoOrario(double standardTax) {
		return standardTax / TASSA_MOTO.getTaxValue();
	}

}
