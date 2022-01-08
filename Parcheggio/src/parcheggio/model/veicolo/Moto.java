package parcheggio.model.veicolo;

/**
 *
 * @author marti
 */
public class Moto extends Veicolo{
    
    public Moto(String targa, int annoImm, Alimentazione carburante,
                    String marca, String modello, String nomeProprietario,
                    String cognomeProprietario, double capienzaMassima, double carburanteAttuale){

    	super(targa, annoImm, carburante, marca, modello,
    			nomeProprietario, cognomeProprietario,
    			capienzaMassima, carburanteAttuale);
    }

    /*--- Equals ---*/
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
