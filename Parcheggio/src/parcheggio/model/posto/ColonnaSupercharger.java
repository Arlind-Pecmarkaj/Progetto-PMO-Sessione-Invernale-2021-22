package parcheggio.model.posto;
import parcheggio.model.veicolo.*;

public class ColonnaSupercharger implements Supercharger {
	private double kWh;
	private double tempoRicarica;
	
	public ColonnaSupercharger() {
		this.kWh = 75.0;
		this.tempoRicarica = 0.0;
	}
	
	public void setTempoRicarica(double tempoRicarica) {
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
	
	@Override
	public void ricaricaVeicolo(int percentualeRaggiungere, Veicolo veicoloElettrico) {
		double percentualeAttuale = (100 * veicoloElettrico.getCarburanteAttuale()) / veicoloElettrico.getCapienzaMassima(); // 100kW rimasti => X% => max => 100%
		double percentualeRicaricare = percentualeRaggiungere - percentualeAttuale; // 70% - 10% => ricarico 60%
		double kWRicaricare = (veicoloElettrico.getCapienzaMassima() * percentualeRicaricare) / 100;
		
		veicoloElettrico.setCarburanteAttuale(veicoloElettrico.getCarburanteAttuale() + kWRicaricare);
		this.setTempoRicarica(kWRicaricare / this.getkWh()); // numero di ore di ricarica: 0,5 => 30 minuti
	}
	
}
