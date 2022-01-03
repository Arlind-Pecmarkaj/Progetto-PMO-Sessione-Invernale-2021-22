package parcheggio.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import parcheggio.exceptions.PostiFiniti;
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
	final private int id;
	private String name;
	private LinkedList<AbstractPosto> postiDisponibili = new LinkedList<AbstractPosto>();
	final private int postiTotaliAuto;
//	private int autoParcheggiate = 0; // posti auto occupati
//	private int motoParcheggiate = 0; // posti moto occupati
	final private int postiTotaliMoto;
	private LinkedList<Monopattino> postiMonopattino = new LinkedList<Monopattino>();
	final private int postiTotaliMonopattini;
	private Sensore<Double> sensoreAltezza = new SensoreAltezza();
	private HashSet<Abbonamento> abbonamenti = new HashSet<Abbonamento>();

	// costruttore
	public Parcheggio(int id, String n, int nPostiAuto, int nPostiMoto, int nPostiMonopattino) {
		this.id = id;
		this.name = n;
		this.postiTotaliAuto = nPostiAuto;
		this.postiTotaliMoto = nPostiMoto;
		
		for(int i = 0; i < this.postiTotaliAuto; i++)
			this.postiDisponibili.add(new PostoAuto(new SensoreCarburante()));
		
		for(int i = 0; i < this.postiTotaliMoto; i++)
			this.postiDisponibili.add(new PostoMoto());
		
		if(nPostiMonopattino != 0) {
			this.postiTotaliMonopattini = nPostiMonopattino;
		} else {
			this.postiTotaliMonopattini = 0;
			this.postiMonopattino = null;
		}
	}// end costruttore

	public LinkedList<AbstractPosto> getPostiDisponibili() {
		return postiDisponibili;
	}// end metodo getPostoDisponibili()

	public LinkedList<Monopattino> getPostiMonopattino() {
		return postiMonopattino;
	}// end metodo getPostiMonopattino()

	/* metodo per aggiungere un veicolo al parcheggio, se � presente un posto libero */
	public void aggiungiVeicolo(Veicolo v){
		if(v instanceof Auto) {
			this.filtraAggiungi(p -> p instanceof PostoAuto == true, v);
		} else if(v instanceof Moto){
			// stessa cosa per le moto
			this.filtraAggiungi(p -> p instanceof PostoMoto == true, v);
		}
	}// end metodo aggiungiVeicolo
	
	/* metodo per liberare un posto del parcheggio
	 * restituisce il prezzo da pagare
	 */
	public double liberaPosto(Posto p/*, Persona pe*/) {
		double prezzo = 0;
		Optional<AbstractPosto> postoDaLiberare = this.postiDisponibili.stream()
				             								   .filter(x -> x.equals(p))
				             								   .findAny();
		if(postoDaLiberare.isPresent()) {
			if(!this.abbonamenti.contains(((AbstractPosto) p).getVeicolo().getAbbonamento())/*pe.getVeicolo().getAbbonamento())*/) {
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
	
	/* metodo per controllare se � presente un posto libero, in caso contrario
	 * lancia un'eccezione (PostiFiniti) 
	 */
	private void filtraAggiungi(Predicate<AbstractPosto> filtro, Veicolo v){
		Optional<AbstractPosto> tmp = this.postiDisponibili.stream()
				   								   		   .filter(p -> p.isLibero() == true)
				   								           .filter(filtro)
				   								           .findFirst();
		if(tmp.isPresent()) {
			if(v instanceof Auto) {
				if((double)this.sensoreAltezza.effettuaRilevamento((Auto)v) <= 4.0) {
					tmp.get().occupaPosto(v);
				} else {
//					throw new AltezzaMassimaSuperata("Eccezione: L'altezza del veicolo ha superato il limite consentito");
				}
			}
		} else {
			throw new PostiFiniti("Eccezione: I posti sono finiti");
		}
	}// end metodo filtraAggiungi
	
	public Monopattino noleggiaMonopattino(Persona p) {
		Monopattino m = null;
		if(/*this.abbonamenti.contains(*/p.getAbbonamento() != null)/*)*/{
			if(this.postiMonopattino.size() != 0 && this.postiMonopattino.getLast().getDisponibile()) {
				m = this.postiMonopattino.getLast();
				this.postiMonopattino.removeLast();
			}
		}
		return m;
	}// end metodo noleggiaMonopattino()
	
	public double restituisciMonopattino(Persona p, Monopattino m) {
		double prezzo = 0;
		if(!p.possiedeAbbonamentoPremium()) {
			prezzo = Monopattino.COSTO * (m.getFineNoleggio() - m.getOraNoleggiato());
		}
		this.postiMonopattino.add(m);
		return prezzo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + postiTotaliAuto;
		result = prime * result + postiTotaliMonopattini;
		result = prime * result + postiTotaliMoto;
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
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postiTotaliAuto != other.postiTotaliAuto)
			return false;
		if (postiTotaliMonopattini != other.postiTotaliMonopattini)
			return false;
		if (postiTotaliMoto != other.postiTotaliMoto)
			return false;
		return true;
	}
	
}// end classe