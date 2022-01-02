/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcheggio.model.veicolo;

/**
 *
 * @author marti
 */
public class Auto extends Veicolo{
    private double altezza;
    
    public Auto(String targa, int annoImm, Alimentazione carburante,
                    String marca, String modello, String nomeProprietario,
                    String cognomeProprietario, double altezza){
        super(targa, annoImm, carburante, marca, modello, nomeProprietario, cognomeProprietario);
        this.altezza = altezza;
    }
    
    public double getAltezza(){
        return this.altezza;
    }
    
    public void setAltezza(double alt){
        this.altezza = alt;
    }
    
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
