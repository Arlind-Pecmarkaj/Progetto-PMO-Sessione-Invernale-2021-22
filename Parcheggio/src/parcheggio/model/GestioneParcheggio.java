package parcheggio.model;

import java.util.ArrayList;

import parcheggio.model.Parcheggio;

public class GestioneParcheggio {
	ArrayList<Parcheggio> parcheggi;
	// ArrayList<Abbonamento> abbonamento;
	
	public GestioneParcheggio() {
		this.parcheggi = new ArrayList<>();
	}
	
	public void aggiungiParcheggio(Parcheggio p) {
		parcheggi.add(p);
	}

}
