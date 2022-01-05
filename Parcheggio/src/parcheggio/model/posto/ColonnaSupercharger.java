package parcheggio.model.posto;

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
	
	
	
}
