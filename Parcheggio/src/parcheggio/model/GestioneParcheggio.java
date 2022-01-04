package parcheggio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.persona.Persona;

public class GestioneParcheggio {
	ArrayList<Parcheggio>    parcheggi;
	ArrayList<Abbonamento> abbonamenti;
	
	public GestioneParcheggio() {
		this.parcheggi = new ArrayList<>();
	}
	
	public Parcheggio getParcheggio(int index) {
		return this.parcheggi.get(index);
	}
	
	public void aggiungiParcheggio(Parcheggio p) {
		parcheggi.add(p);
	}
        
    public void aggiungiAbbonamento(Abbonamento a) {
         this.abbonamenti.add(a);
    }
    
    public Abbonamento getAbbonamento(Persona p) {
    	return this.abbonamenti.stream().filter(ab -> ab.getPersona().equals(p)).findAny().get();
    }
    
    public Abbonamento getAbbonamento(String targa) {
    	return this.abbonamenti.stream().filter(ab -> ab.getTarga().equals(targa)).findAny().get();
    }
    
    public List<Parcheggio> getParcheggi() {
    	return this.parcheggi;
    }
    
    public List<Abbonamento> getAbbonamenti() {
    	return this.abbonamenti;
    }
        
    public void aggiornaAbbonamenti() {
    	for (Abbonamento a : this.abbonamenti) {
    		if (a.getDataFine().isBefore(LocalDate.now()))
    			this.abbonamenti.remove(a);
    	}
    }

}
