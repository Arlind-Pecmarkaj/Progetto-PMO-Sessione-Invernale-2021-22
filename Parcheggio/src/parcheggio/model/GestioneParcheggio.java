package parcheggio.model;

import java.util.ArrayList;

public class GestioneParcheggio {
	ArrayList<Parcheggio> parcheggi;
	// ArrayList<Abbonamento> abbonamento;
	
	public GestioneParcheggio() {
		this.parcheggi = new ArrayList<>();
	}
	
	public void aggiungiParcheggio(Parcheggio p) {
		parcheggi.add(p);
	}
        
        public void aggiungiAbbonamento(/* Abbonamento a */) {
            //TODO
        }
        
        public void aggiornaAbbonamenti() {
            // TODO
        }

}
