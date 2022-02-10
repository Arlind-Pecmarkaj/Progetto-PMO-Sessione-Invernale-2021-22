package parcheggio.model.posto;

import static parcheggio.enumerations.TassaParcheggio.*;

/**
 *  Questa classe specializza la tipologia del posto
 *  in posto per sole Moto.
 *  
 *  L'Id di questi posti inizia con 'M' per
 *  contraddistinguerli da quelli delle altre tipologie.
 * 
 */

public class PostoMoto extends AbstractPosto {
	
	
	public PostoMoto() {
		this("0000");
	}
	
	public PostoMoto(String postoId) {
		super.setPosto(postoId);
	}
	
	
	/**
	 * 	Compone una parte variabile univoca con il codice "M"
	 * 	che sta ad indicare che il posto è riservato alle Moto.
	 * 	L'Id di un posto corrisponde con il numero suo numero.
	 * 	
	 * 	@param postoMotoID
	 * 	@return l'Id composto di un posto per moto
	 */
	@Override
	protected String setId(String postoMotoId) {
		return "M" + postoMotoId;
	}

	/**
	 * 	Calcola la tariffa oraria di questo posto in un certo parcheggio.
	 * 
	 * 	@param standardTax
	 * 	@return tariffa oraria per moto
	 */
	@Override
	protected double setCostoOrario() {
		return STANDARD_TAX / TASSA_MOTO.getTaxValue();
	}

}
