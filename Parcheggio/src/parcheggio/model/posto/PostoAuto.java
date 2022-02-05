package parcheggio.model.posto;

import static parcheggio.enumerations.TassaParcheggio.*;
import parcheggio.model.sensore.SensoreCarburante;

public class PostoAuto extends AbstractPosto {
	
	private SensoreCarburante sensoreCarburante;
	
//	costruttori per i test
	
	public PostoAuto() {
		this("A0000", new SensoreCarburante());
	};
	
	public PostoAuto(SensoreCarburante sensore) {
		this("A0000", sensore);
	}
	
	public PostoAuto(String postoId) {
		this(postoId, new SensoreCarburante());
	}
	
	public PostoAuto(String postoId, SensoreCarburante sensore) {
		super.setPosto(postoId);
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
	protected String setId(String postoAutoId) {
		return "A" + postoAutoId;
	}

	/**
	 * 	Calcola la tariffa oraria di questo posto in un certo parcheggio.
	 * 
	 * 	@param standardTax
	 * 	@return tariffa oraria per auto
	 */
	@Override
	protected double setCostoOrario() {
		return STANDARD_TAX / TASSA_AUTO.getTaxValue();
	}
}
