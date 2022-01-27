package parcheggio.model;

import java.util.Optional;
import java.util.Set;

import parcheggio.model.posto.Posto;
import parcheggio.model.veicolo.Veicolo;

/*
 * interfaccia per identificare un parcheggio
 */
public interface Parcheggio {

	void aggiungiVeicolo(Veicolo v);
	
	double liberaPosto(Posto p);
	
	Set<Optional<Veicolo>> listaVeicoliPresenti();
	
}
