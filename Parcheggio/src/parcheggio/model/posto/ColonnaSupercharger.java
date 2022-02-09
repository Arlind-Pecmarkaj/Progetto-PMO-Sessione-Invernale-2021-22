package parcheggio.model.posto;

import parcheggio.exceptions.IllegalChargerException;
import parcheggio.model.veicolo.*;


public class ColonnaSupercharger implements Supercharger {
	private double kWh;
	private double tempoRicarica;
	
	public ColonnaSupercharger() {
		this.kWh = 75.0;
		this.tempoRicarica = 0;
	}
	
	private void setTempoRicarica(double tempoRicarica) {
		this.tempoRicarica = tempoRicarica;
	}
	
	/**
	 *  Metodo per ottenere il tempo di ricarica del veicolo
	 *  con un formato "human readable" (HR)
	 * 
	 */
	public String getTempoRicaricaHR() {
		double min = this.tempoRicarica * 60;
		return String.format("%02d min", (int) Math.ceil(min));
	}
	
	/**
	 *  Metodo per ottenere la ricarica attuale del veicolo
	 *  elettrico in termini di percentuale
	 *  
	 *  segue dalla relazione: attuale% = (100% * kwh-attuale) / kwh-max
	 *  
	 */
	public double getPercentualeAttuale(Veicolo veicoloElettrico) {
		double percentualeAttuale = (100 * veicoloElettrico.getCarburanteAttuale()) / veicoloElettrico.getCapienzaMassima();
		return percentualeAttuale;
	}

	
	
	/************************************************************
	 * 	METODI DELL'INTERFACCIA Supercharger DA IMPLEMENTARE	*
	 ************************************************************/

	public double getkWh() {
		return this.kWh;
	}
	
	public double getTempoRicarica() {
		return this.tempoRicarica;
	}
	
	public void ricaricaVeicolo(int percentualeRaggiungere, Veicolo veicoloElettrico) {
		if(percentualeRaggiungere <= getPercentualeAttuale(veicoloElettrico)) throw new IllegalChargerException("Attenzione! La batteria ha già raggiunto questo livello.");
		if(percentualeRaggiungere > 100) throw new IllegalChargerException("Attenzione! La batteria raggiunge al massimo il 100%");
		
		double percentualeRicaricare = percentualeRaggiungere - getPercentualeAttuale(veicoloElettrico);
		double kWRicaricare = (veicoloElettrico.getCapienzaMassima() * percentualeRicaricare) / 100;
		this.setTempoRicarica(kWRicaricare / this.getkWh());
		veicoloElettrico.setCarburanteAttuale(veicoloElettrico.getCarburanteAttuale() + kWRicaricare);
	}
	
}
