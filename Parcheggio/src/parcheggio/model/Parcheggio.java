package parcheggio.model;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.persona.Persona;
import parcheggio.model.posto.Posto;
import parcheggio.model.veicolo.Veicolo;

/*
 * interfaccia per identificare un parcheggio
 */
public interface Parcheggio {

	int getNPostiSpecifici(Predicate<Posto> filtro);
	
	Posto aggiungiVeicolo(Veicolo v);
	
	double liberaPosto(Posto p);
	
	Set<Veicolo> listaVeicoliPresenti();
	
	Monopattino noleggiaMonopattino(Persona p);
	
	double restituisciMonopattino(Persona p, Monopattino m);
	
}
