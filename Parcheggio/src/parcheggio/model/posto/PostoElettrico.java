package parcheggio.model.posto;

import static parcheggio.enumerations.TassaParcheggio.*;

import java.util.Optional;

import parcheggio.enumerations.Alimentazione;
import parcheggio.model.sensore.SensoreCarburante;
import parcheggio.model.veicolo.Auto;

public class PostoElettrico extends AbstractPosto {

	private SensoreCarburante sensoreCarburante;
	private ColonnaSupercharger colonnaSupercharger;
	
	public PostoElettrico() { 
		this("E0000", 0.0, new SensoreCarburante());
	}
	
	public PostoElettrico(String postoId, double standardTax, SensoreCarburante sensore) {
		super.setPosto(postoId, standardTax);
		this.sensoreCarburante = sensore;
		this.colonnaSupercharger = new ColonnaSupercharger();
	}
	
	/**
	 * 	metodo getter per accedere al sensore relativo ad un posto
	 */
	public SensoreCarburante getSensoreCarburante() {
		return this.sensoreCarburante;
	}
	
	public void ricaricaAuto(int percentualeRaggiungere) {
		int daRicaricare = percentualeRaggiungere - super.getVeicolo().get().getSerbatoio(); // 70% - 10% => ricarico 60%
		double daRicaricarekWh = (super.getVeicolo().get().getCapacitaSerbatoio() * daRicaricare) / 100;
		this.colonnaSupercharger.setTempoRicarica(daRicaricarekWh / this.colonnaSupercharger.getkWh()); // numero di ore di ricarica: 0,5 => 30 minuti
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
