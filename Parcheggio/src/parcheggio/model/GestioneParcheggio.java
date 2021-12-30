package parcheggio.model;

import java.util.ArrayList;

import parcheggio.Parcheggio;

public class GestioneParcheggio {
	ArrayList<Parcheggio> parcheggi;
	
	public GestioneParcheggio() {
		this.parcheggi = new ArrayList<>();
	}
	
	public void aggiungiParcheggio(Parcheggio p) {
		parcheggi.add(p);
	}

}
