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
    private double velocita;
    
    public Moto(String targa, int annoImm, Alimentazione carburante,
                    String marca, String modello, String nomeProprietario,
                    String cognomeProprietario, double v){
        super(targa, annoImm, carburante, marca, modello, nomeProprietario, cognomeProprietario);
        this.velocita = v;
    }
    
    public double getVelocita(){
        return this.velocita;
    }
    
    public void setVelocita(double v){
        this.velocita = v;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + "\nVelocita': " + this.velocita;
    }
}
