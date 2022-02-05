package parcheggio.model.posto;

import static parcheggio.enumerations.TassaParcheggio.*;
import parcheggio.model.sensore.SensoreCarburante;

public class PostoElettrico extends AbstractPosto {

	private SensoreCarburante sensoreCarburante;
	private ColonnaSupercharger colonnaSupercharger;
	
	public PostoElettrico() { 
		this("E0000", new SensoreCarburante());
	}
	
	public PostoElettrico(String postoId) {
		this(postoId, new SensoreCarburante());
	}
	
	public PostoElettrico(String postoId, SensoreCarburante sensore) {
		super.setPosto(postoId);
		this.sensoreCarburante = sensore;
		this.colonnaSupercharger = new ColonnaSupercharger();
	}
	
	/**
	 * 	metodo getter per accedere al sensore relativo ad un posto
	 */
	public SensoreCarburante getSensoreCarburante() {
		return this.sensoreCarburante;
	}
	
	public ColonnaSupercharger getColonnaSupercharger() {
		return this.colonnaSupercharger;
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
	protected String setId(String postoElettricoId) {
		return "E" + postoElettricoId;
	}

	/**
	 * 	Calcola la tariffa oraria di questo posto in un certo parcheggio.
	 * 
	 * 	@param standardTax
	 * 	@return tariffa oraria per vettura elttrica
	 */
	@Override
	protected double setCostoOrario() {
		return STANDARD_TAX / TASSA_ELETTRICO.getTaxValue();
	}

}
