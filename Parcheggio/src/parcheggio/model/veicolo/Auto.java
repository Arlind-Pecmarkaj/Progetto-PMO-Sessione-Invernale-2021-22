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
    private int nPosti;
    
    public Auto(String targa, int annoImm, Alimentazione carburante,
                    String marca, String modello, String nomeProprietario,
                    String cognomeProprietario, double altezza, int nPosti){
        super(targa, annoImm, carburante, marca, modello, nomeProprietario, cognomeProprietario);
        this.altezza = altezza;
        this.nPosti = nPosti;
    }
    
    public double getAltezza(){
        return this.altezza;
    }
    
    public int getNPosti(){
        return this.nPosti;
    }
    
    public void setAltezza(double alt){
        this.altezza = alt;
    }
    
    public void setNPosti(int np){
        this.nPosti = np;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + "\nAltezza: " + this.altezza
                + "\nN° posti: " + this.nPosti;
    }
}
