package parcheggio.model.veicolo;

/**
 *
 * @author marti
 */
public class Auto extends Veicolo{
    private double altezza;
    
    public Auto(String targa, int annoImm, Alimentazione carburante,
                    String marca, String modello, String nomeProprietario,
                    String cognomeProprietario, double altezza,
                    double capienzaMassima, double carburanteAttuale){
        super(targa, annoImm, carburante, marca, modello,
        		nomeProprietario, cognomeProprietario,
        		capienzaMassima, carburanteAttuale);
        this.altezza = altezza;
    }
    
    public double getAltezza(){
        return this.altezza;
    }
    
    public void setAltezza(double alt){
        this.altezza = alt;
    }
    
    
    /*--- Equals ---*/
	public boolean equals(Auto v) {
		return this.getAltezza() == v.getAltezza();
	}
	

	/**
     *
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + "\nAltezza: " + this.altezza;
    }
}
