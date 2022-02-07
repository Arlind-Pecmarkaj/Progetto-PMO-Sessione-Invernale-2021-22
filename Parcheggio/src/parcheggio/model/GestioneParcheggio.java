package parcheggio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.persona.Persona;

public class GestioneParcheggio {
	List<Parcheggio> parcheggi;
	List<Abbonamento>  abbonamenti;
	
	public GestioneParcheggio() {
		this.parcheggi   = new ArrayList<>();
		this.abbonamenti = new ArrayList<>();
	}
	
	public Parcheggio getParcheggio(int index) {
		return this.parcheggi.get(index);
	}
	
	public void aggiungiParcheggio(Parcheggio p) {
		if (this.parcheggi.stream().filter(pr -> pr.getId().equals(p.getId())).findAny().isEmpty()) {
			parcheggi.add(p);
			p.setAbbonamenti(this.abbonamenti);
		}
	}
        
    public void aggiungiAbbonamento(Abbonamento a) {
    	if (!abbonamenti.contains(a)) {
    		this.abbonamenti.add(a);
    		this.aggiornaAbbonamenti();
    	}
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
    	
    	for (Parcheggio p : this.parcheggi) {
    		p.setAbbonamenti(this.abbonamenti);
    	}
    }
    
    @Override
    public String toString() {
		return "NR PARCHEGGI: " + this.parcheggi.size() + " : NR ABBONAMENTI " + this.abbonamenti.size();
    }

}
