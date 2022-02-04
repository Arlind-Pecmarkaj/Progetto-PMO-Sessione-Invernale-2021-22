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

	@Override
	public double getkWh() {
		return this.kWh;
	}
	
	@Override
	public double getTempoRicarica() {
		return this.tempoRicarica;
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
	
	public double getPercentualeAttuale(Veicolo veicoloElettrico) {
		double percentualeAttuale = (100 * veicoloElettrico.getCarburanteAttuale()) / veicoloElettrico.getCapienzaMassima(); // 100kW rimasti => X% => max => 100%
		return percentualeAttuale;
	}
	
	@Override
	public void ricaricaVeicolo(int percentualeRaggiungere, Veicolo veicoloElettrico) {
		if(percentualeRaggiungere <= getPercentualeAttuale(veicoloElettrico)) throw new IllegalChargerException("Attenzione! La batteria ha già raggiunto questo livello.");
		if(percentualeRaggiungere > 100) throw new IllegalChargerException("Attenzione! La batteria raggiunge al massimo il 100%");
		
		double percentualeRicaricare = percentualeRaggiungere - getPercentualeAttuale(veicoloElettrico); // 70% - 10% => ricarico 60%
		double kWRicaricare = (veicoloElettrico.getCapienzaMassima() * percentualeRicaricare) / 100;
		veicoloElettrico.setCarburanteAttuale(veicoloElettrico.getCarburanteAttuale() + kWRicaricare);
		this.setTempoRicarica(kWRicaricare / this.getkWh()); // numero di ore di ricarica: 0,5 => 30 minuti
		
	}
	
}
