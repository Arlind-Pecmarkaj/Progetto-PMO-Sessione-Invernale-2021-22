package parcheggio.model;

/* import utilizzati: */
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.persona.Pers;
import parcheggio.model.posto.Posto;
import parcheggio.model.veicolo.Veicolo;

/*
 * interfaccia per identificare un parcheggio
 */
public interface Parcheggio {
	
	String getId();

	int getNPostiSpecifici(Predicate<Posto> filtro);
	
	void setAbbonamenti(List<Abbonamento> lista);
	
	Posto aggiungiVeicolo(Veicolo v);
	
	double liberaPosto(Posto p);
	
	Set<Veicolo> listaVeicoliPresenti();
	
	Monopattino noleggiaMonopattino(Pers p);
	
	double restituisciMonopattino(Pers p, Monopattino m);
	
}
