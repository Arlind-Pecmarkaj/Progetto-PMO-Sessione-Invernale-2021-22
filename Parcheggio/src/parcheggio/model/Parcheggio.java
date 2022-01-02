package parcheggio.model;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import parcheggio.exceptions.PostiFiniti;
import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.sensore.Sensore;
import parcheggio.model.sensore.SensoreAltezza;
import parcheggio.model.veicolo.Auto;
import parcheggio.model.veicolo.Moto;

public class Parcheggio {
	private LinkedList<Posto> postiDisponibili = new LinkedList<Posto>();
	final private int postiTotaliAuto;
//	private int autoParcheggiate = 0; // posti auto occupati
//	private int motoParcheggiate = 0; // posti moto occupati
	final private int postiTotaliMoto;
	private LinkedList<Monopattino> postiMonopattino = new LinkedList<Monopattino>();
	final private int postiTotaliMonopattini;
	private Sensore sensoreAltezza = new SensoreAltezza();
	
	// costruttore
	public Parcheggio(int nPostiAuto, int nPostiMoto, int nPostiMonopattino) {
		this.postiTotaliAuto = nPostiAuto;
		this.postiTotaliMoto = nPostiMoto;
		
		for(int i = 0; i < this.postiTotaliAuto; i++)
			this.postiDisponibili.add(new PostoAuto());
		
		for(int i = 0; i < this.postiTotaliMoto; i++)
			this.postiDisponibili.add(new PostoMoto());
		
		if(nPostiMonopattino != 0) {
			this.postiTotaliMonopattini = nPostiMonopattino;
		} else {
			this.postiTotaliMonopattini = 0;
			this.postiMonopattino = null;
		}
	}// end costruttore

	public LinkedList<Posto> getPostiDisponibili() {
		return postiDisponibili;
	}// end metodo getPostoDisponibili()

	public LinkedList<Monopattino> getPostiMonopattino() {
		return postiMonopattino;
	}// end metodo getPostiMonopattino()

	/* metodo per aggiungere un veicolo al parcheggio, se � presente un posto libero */
	public void aggiungiVeicolo(Veicolo v){
		if(v instanceof Auto) {
			this.filtraAggiungi(p -> p instanceof PostiAuto == true, v);
		} else if(v instanceof Moto){
			// stessa cosa per le moto
			this.filtraAggiungi(p -> p instanceof PostiMoto == true, v);
		}
	}// end metodo aggiungiVeicolo
	
	/* metodo per liberare un posto del parcheggio
	 * restituisce il veciolo occupante, se presente
	 */
	public Optional<Veicolo> liberaPosto(Posto p) {
		Optional<Veicolo> v = Optional.empty();
		Optional<Posto> postoDaLiberare = this.postiDisponibili.stream()
				             								   .filter(x -> x.equals(p))
				             								   .findAny();
		if(postoDaLiberare.isPresent()) {
			v = postoDaLiberare.get().getVeicoloOccupante();
			postoDaLiberare.get().setVeicoloOccupante(null);
		}
		return v;
	}// end metodo liberaPosto()
	
	/*
	 * restituisce tutti i veicoli presenti nel parcheggio
	 */
	public Set<Veicolo> listaVeicoliPresenti() {
		return this.postiDisponibili.stream()
						            .filter(p -> p.getVeicoloOccupante() != null)
						            .map(p -> p.getVeicoloOccupante())
						            .collect(Collectors.toSet());
	}// end metodo listaVeicoliPresenti()
	
	/* metodo per controllare se � presente un posto libero, in caso contrario
	 * lancia un'eccezione (PostiFiniti) 
	 */
	private void filtraAggiungi(Predicate<Posto> filtro, Veicolo v){
		Optional<Posto> tmp = this.postiDisponibili.stream()
				   								   .filter(p -> p.setVeicoloOccupante() == null)
				   								   .filter(filtro)
				   								   .findFirst();
		if(tmp.isPresent()) {
			if(v instanceof Auto) {
				if((double)this.sensoreAltezza.effettuaRilevamento((Auto)v) <= 4.0) {
					tmp.get().setVeicoloOccupante(v);
				} else {
//					throw new AltezzaMassimaSuperata("Eccezione: L'altezza del veicolo ha superato il limite consentito");
				}
			}
		} else {
			throw new PostiFiniti("Eccezione: I posti sono finiti");
		}
	}// end metodo filtraAggiungi

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postiTotaliAuto;
		result = prime * result + postiTotaliMonopattini;
		result = prime * result + postiTotaliMoto;
		return result;
	}

	/*
	 * un parcheggio � uguale se ha lo stesso numero di posti
	 * per le auto, per le moto e per i monopattini
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parcheggio other = (Parcheggio) obj;
		if (postiTotaliAuto != other.postiTotaliAuto)
			return false;
		if (postiTotaliMonopattini != other.postiTotaliMonopattini)
			return false;
		if (postiTotaliMoto != other.postiTotaliMoto)
			return false;
		return true;
	}
	
}// end classe