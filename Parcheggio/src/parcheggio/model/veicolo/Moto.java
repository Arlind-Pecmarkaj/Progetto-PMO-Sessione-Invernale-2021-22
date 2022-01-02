/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcheggio.model.veicolo;

/**
 *
 * @author marti
 */
public class Moto extends Veicolo{
    
    public Moto(String targa, int annoImm, Alimentazione carburante,
                    String marca, String modello, String nomeProprietario,
                    String cognomeProprietario, double v){

    	super(targa, annoImm, carburante, marca, modello, nomeProprietario, cognomeProprietario);
    }

    public boolean equals(Moto m){
    	return this.getCarburante() == m.getCarburante();
    }
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + "\nAlimentazione: " + this.getCarburante();
    }
}
