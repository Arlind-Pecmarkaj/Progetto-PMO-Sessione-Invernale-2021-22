package parcheggio.model.posto;

import static parcheggio.enumerations.TassaParcheggio.*;
import parcheggio.model.sensore.SensoreCarburante;

public class PostoElettrico extends AbstractPosto {

	private SensoreCarburante sensoreCarburante;
	
	public PostoElettrico() { }
	
	public PostoElettrico(SensoreCarburante sensore) {
		this.sensoreCarburante = sensore;
	}
	
	/**
	 * 	metodo getter per accedere al sensore relativo ad un posto
	 */
	public SensoreCarburante getSensoreCarburante() {
		return this.sensoreCarburante;
	}
	
	/**
	 * 	Compone una parte variabile univoca con il codice "E"
	 * 	che sta ad indicare che il posto è riservato alle vetture Elettriche.
	 * 	L'ID di un posto corrisponde con il numero suo numero.
	 * 	
	 * 	@param postoElettricoID
	 * 	@return l'ID composto di un posto per vetture Elettriche
	 */
	@Override
	protected String setId(String postoElettricoID) {
		return "E" + postoElettricoID;
	}

	/**
	 * 	Calcola la tariffa oraria di questo posto in un certo parcheggio.
	 * 
	 * 	@param standardTax
	 * 	@return tariffa oraria per vettura elttrica
	 */
	@Override
	protected double setCostoOrario(double standardTax) {
		return standardTax / TASSA_ELETTRICO.getTaxValue();
	}

}
