package parcheggio.model.posto;

import static parcheggio.enumerations.TassaParcheggio.*;
import parcheggio.model.sensore.SensoreCarburante;

public class PostoAuto extends AbstractPosto {
	
	private SensoreCarburante sensoreCarburante;
	
	public PostoAuto() {
		super.setPosto("A0000", 0.0);
	};
	
	public PostoAuto(String postoId, double standardTax, SensoreCarburante sensore) {
		super.setPosto(postoId, standardTax);
		this.sensoreCarburante = sensore;
	}
	
	/**
	 * 	metodo getter per accedere al sensore relativo ad un posto
	 */
	public SensoreCarburante getSensoreCarburante() {
		return this.sensoreCarburante;
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
