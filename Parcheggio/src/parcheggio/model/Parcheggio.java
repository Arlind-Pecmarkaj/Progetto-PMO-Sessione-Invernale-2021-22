package parcheggio.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import parcheggio.exceptions.AltezzaMassimaConsentitaException;
import parcheggio.exceptions.PersonaSenzaAbbonamentoException;
import parcheggio.exceptions.PostiFinitiException;
import parcheggio.exceptions.MonopattiniEsauritiException;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.persona.Persona;
import parcheggio.model.sensore.Sensore;
import parcheggio.model.sensore.SensoreAltezza;
import parcheggio.model.sensore.SensoreCarburante;
import parcheggio.model.veicolo.Auto;
import parcheggio.model.veicolo.Moto;
import parcheggio.model.veicolo.Veicolo;
import parcheggio.model.posto.*;

public class Parcheggio {
	/* Campi della classe */
	final private String id;
	private String name;
	private LinkedList<AbstractPosto> postiDisponibili = new LinkedList<AbstractPosto>();
	private LinkedList<Monopattino> postiMonopattino = new LinkedList<Monopattino>();
	private Sensore<Double> sensoreAltezza = new SensoreAltezza();
	private HashSet<Abbonamento> abbonamenti = new HashSet<Abbonamento>();
	final private double altezzaMassimaConsentita;

	// costruttore
	public Parcheggio(String id, String n, int nPostiAuto, int nPostiMoto, int nPostiMonopattino, double a) {
		this.id = id;
		this.name = n;
		this.altezzaMassimaConsentita = a;
		
		/* istanzio gli oggetto di tipo PostoAuto */
		for(int i = 0; i < nPostiAuto; i++)
			this.postiDisponibili.add(new PostoAuto(new SensoreCarburante()));
		
		/* istanzio gli oggetti di tipo PostoMoto */
		for(int i = 0; i < nPostiMoto; i++)
			this.postiDisponibili.add(new PostoMoto());
		
		/* istanzio gli oggetti di tipo Monopattino */
		if(nPostiMonopattino != 0) {
			for(int i = 0; i < nPostiMonopattino; i++)
				this.postiMonopattino.add(new Monopattino());
		}
	}// end costruttore	
	
	/*
	 * restituisce l'id del parcheggio
	 */
	public String getId() {
		return this.id;
	}// end metodo getId()

	/*
	 * restituisce il nome del parcheggio
	 */
	public String getName() {
		return this.name;
	}// end metodo getName()

	/*
	 * restituisce l'altezza massima consentita per le auto
	 */
	public double getAltezzaMassimaConsentita() {
		return this.altezzaMassimaConsentita;
	}// end metodo getAltezzaMassimaConsentita()

	/*
	 * restituisce tutti i posti del parcheggio
	 */
	public LinkedList<AbstractPosto> getPostiDisponibili() {
		return this.postiDisponibili;
	}// end metodo getPostoDisponibili()

	/*
	 * restituisce il numero di posti per le auto presenti nel parcheggio
	 */
	public int getNPostiAuto() {
		return this.postiDisponibili.stream()
								    .filter(p -> p instanceof PostoAuto)
								    .collect(Collectors.toList())
								    .size();
	}// end metodo getNPostiAuto()
	
	/*
	 * restituisce il numero di posti per le moto presenti nel parcheggio
	 */
	public int getNPostiMoto() {
		return this.postiDisponibili.stream()
								    .filter(p -> p instanceof PostoMoto)
								    .collect(Collectors.toList())
								    .size();
	}// end metodo getNPostiMoto()
	
	public LinkedList<Monopattino> getPostiMonopattino() {
		return postiMonopattino;
	}// end metodo getPostiMonopattino()
	
	public void setAbbonamenti(List<Abbonamento> lista) {
		this.abbonamenti = new HashSet<Abbonamento>(lista);
	}// end metodo setAbbonamenti()

	/* metodo per aggiungere un veicolo al parcheggio, se e' presente un posto libero
	 * altrimenti viene lanciata un'eccezione.
	 */
	public void aggiungiVeicolo(Veicolo v){
		/* controllo se il veicolo e' un auto o una moto */
		if(v instanceof Auto) {
			this.filtraAggiungi(p -> p instanceof PostoAuto, v);
		} else if(v instanceof Moto){
			this.filtraAggiungi(p -> p instanceof PostoMoto, v);
		}
	}// end metodo aggiungiVeicolo
	
	/* metodo per liberare un posto del parcheggio
	 * restituisce il prezzo da pagare
	 */
	public double liberaPosto(Posto p) {
		double prezzo = 0;
		/* controllo se e' presente o meno il posto da liberare */
		Optional<AbstractPosto> postoDaLiberare = this.postiDisponibili.stream()
																	   .filter(x -> x.equals(p))
																	   .findAny();
		if(postoDaLiberare.isPresent()) {
			/* controllo se il veicolo parcheggiato nel posto da liberare
			 * abbia o meno un abbonamento, se cosi' non fosse
			 * paga il parcheggio
			 */
			Optional<Abbonamento> ab = this.abbonamenti.stream()
													   .filter(a -> a.getTarga().equals(((AbstractPosto) p).getVeicolo().get().getTarga()))
													   .findAny();
			if(ab.isEmpty()) {
				prezzo = postoDaLiberare.get().getCostoOrario() * (postoDaLiberare.get().getOrarioUscita().getNano() -
																   postoDaLiberare.get().getOrarioArrivo().getNano());
			}
			postoDaLiberare.get().liberaPosto();
		}
		return prezzo;
	}// end metodo liberaPosto()
	
	/*
	 * restituisce tutti i veicoli presenti nel parcheggio
	 */
	public Set<Optional<Veicolo>> listaVeicoliPresenti() {
		return this.postiDisponibili.stream()
						            .filter(p -> p.isLibero() == false)
						            .map(p -> p.getVeicolo())
						            .collect(Collectors.toSet());
	}// end metodo listaVeicoliPresenti()
	
	/*
	 * metodo per noleggiare un monopattino
	 */
	public Monopattino noleggiaMonopattino(Persona p) {
		Monopattino m = null;
		/* i monopattini possono essere noleggiati solo da una persona
		 * munita di abbonamento
		 */
		Optional<Abbonamento> esiste = this.abbonamenti.stream()
													   .filter(a -> a.getPersona().equals(p))
													   .findAny();
		if(esiste.isPresent()){
			if(this.postiMonopattino.size() != 0 && this.postiMonopattino.getLast().getDisponibile()) {
				m = this.postiMonopattino.getLast();
				this.postiMonopattino.removeLast();
			} else {
				// lancia eccezione per mancanza di monopattini!!!
				throw new MonopattiniEsauritiException("Eccezione: Monopattini esauriti!");
			}
		} else {
			// lancia eccezione: la persona non ha l'abbonamento
			throw new PersonaSenzaAbbonamentoException("Eccezione: L'utente e' senza abbonamento!");
		}
		m.setOraNoleggiato(System.currentTimeMillis());
		
		return m;
	}// end metodo noleggiaMonopattino()
	
	/*
	 * metodo per restituire un monopattino, precedentemente noleggiato
	 */
	public double restituisciMonopattino(Persona p, Monopattino m) {
		double prezzo = 0;
		m.setFineNoleggio(System.currentTimeMillis());
		/* se la persona e' munita di un abbonamento premium non paga
		 * l'utilizzo del monopattino
		 */
		if(this.abbonamenti.stream()
						   .filter(a -> (a.getPersona().equals(p) && a.isPremium()))
						   .findAny()
						   .isEmpty()) {
			prezzo = Monopattino.COSTO * (m.getFineNoleggio() - m.getOraNoleggiato());
		}
		this.postiMonopattino.add(m);
		
		return prezzo;
	}

	/*
	 * metodo per aggiungere un abbonamento
	 */
	public void aggiungiAbbonamento(Abbonamento a) {
		this.abbonamenti.add(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postiDisponibili == null) ? 0 : postiDisponibili.hashCode());
		result = prime * result + ((postiMonopattino == null) ? 0 : postiMonopattino.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parcheggio other = (Parcheggio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postiDisponibili == null) {
			if (other.postiDisponibili != null)
				return false;
		} else if (!postiDisponibili.equals(other.postiDisponibili))
			return false;
		if (postiMonopattino == null) {
			if (other.postiMonopattino != null)
				return false;
		} else if (!postiMonopattino.equals(other.postiMonopattino))
			return false;
		return true;
	}
	
	/* metodo per controllare se e' presente un posto libero, in caso contrario
	 * lancia un'eccezione (PostiFiniti). Se il veicolo ha un'altezza maggiore
	 * rispetto al limite consentito, viene lanciata un'eccezione (ALtezzaMassimaSuperata)
	 */
	private void filtraAggiungi(Predicate<AbstractPosto> filtro, Veicolo v){
		/* ottengo un posto libero presente nel parcheggio */
		Optional<AbstractPosto> tmp = this.postiDisponibili.stream()
				   								   		   .filter(p -> p.isLibero() == true)
				   								           .filter(filtro)
				   								           .findFirst();
		if(tmp.isPresent()) {
			/* se il veicolo e' un auto e' necessario controllare che l'altezza 
			 * di quest'ultima non superi il limite massimo consentito
			 */
			if(v instanceof Auto) {
				if((double)this.sensoreAltezza.effettuaRilevamento((Auto)v) <= this.altezzaMassimaConsentita) {
					tmp.get().occupaPosto(v);
				} else {
					//lancia eccezione
					throw new AltezzaMassimaConsentitaException("Eccezione: L'altezza del veicolo ha superato il limite massimo consentito!");
				}
			} else {
				/* il veicolo e' una moto e non effettua il controllo dell'altezza */
				tmp.get().occupaPosto(v);
			}
		} else {
			//lancia eccezione
			throw new PostiFinitiException("Eccezione: I posti sono finiti");
		}
	}// end metodo filtraAggiungi
	
}// end classe