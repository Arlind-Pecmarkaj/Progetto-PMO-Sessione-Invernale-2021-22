package parcheggio.model;

/* import utilizzati: */
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import parcheggio.enumerations.Alimentazione;
import parcheggio.exceptions.AltezzaMassimaConsentitaException;
import parcheggio.exceptions.AutoMetanoNonAmmessaException;
import parcheggio.exceptions.PersonaSenzaAbbonamentoException;
import parcheggio.exceptions.PostiFinitiException;
import parcheggio.exceptions.TargaNonPresenteException;
import parcheggio.exceptions.TargheUgualiException;
import parcheggio.exceptions.MonopattiniEsauritiException;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.monopattino.MonopattinoImpl;
import parcheggio.model.persona.Pers;
import parcheggio.model.sensore.Sensore;
import parcheggio.model.sensore.SensoreAltezza;
import parcheggio.model.sensore.SensoreCarburante;
import parcheggio.model.veicolo.Auto;
import parcheggio.model.veicolo.Moto;
import parcheggio.model.veicolo.Veicolo;
import parcheggio.model.veicolo.VeicoloInt;
import parcheggio.model.posto.*;

public class ParcheggioImpl implements Parcheggio{
	/* Campi della classe */
	final private String id;
	private String name;
	private ArrayList<Posto> postiDisponibili = new ArrayList<Posto>();
	private ArrayList<Monopattino> postiMonopattino = new ArrayList<Monopattino>();
	private Sensore<Double> sensoreAltezza = new SensoreAltezza();
	private HashSet<Abbonamento> abbonamenti = new HashSet<Abbonamento>();
	final private double altezzaMassimaConsentita;

	// costruttore
	public ParcheggioImpl(String id, String n, int nPostiAuto, int nPostiMoto, int nPostiElettrici, int nPostiMonopattino, double a) {
		this.id = id;
		this.name = n;
		this.altezzaMassimaConsentita = a;
		
		/* istanzio gli oggetto di tipo PostoAuto */
		for(int i = 0; i < nPostiAuto; i++)
			this.postiDisponibili.add(new PostoAuto(Integer.toString(i), new SensoreCarburante()));
		
		/* istanzio gli oggetti di tipo PostoMoto */
		for(int i = 0; i < nPostiMoto; i++)
			this.postiDisponibili.add(new PostoMoto(Integer.toString(i)));
		
		/* istanzio gli oggetti di tipo PostoElettrico */
		for(int i = 0; i < nPostiElettrici; i++)
			this.postiDisponibili.add(new PostoElettrico(Integer.toString(i)));
		
		/* istanzio gli oggetti di tipo Monopattino */
		if(nPostiMonopattino != 0) {
			for(int i = 0; i < nPostiMonopattino; i++)
				this.postiMonopattino.add(new MonopattinoImpl(i));
		}
	}// end costruttore	
	
	/*
	 * restituisce l'id del parcheggio
	 */
	@Override
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
	public ArrayList<Posto> getPostiDisponibili() {
		return this.postiDisponibili;
	}// end metodo getPostoDisponibili()

	/*
	 * restituisce il numero di posti presenti nel parcheggio del tipo specificato dal parametro
	 * d'ingresso
	 */
	@Override
	public int getNPostiSpecifici(Predicate<Posto> filtro) {
		return this.postiDisponibili.stream()
								    .filter(filtro)
								    .collect(Collectors.toList())
								    .size();
	}// end metodo getNPostiSpecifici()
	
	/*
	 * restituisce i posti dei monopattini
	 */
	public ArrayList<Monopattino> getPostiMonopattino() {
		return this.postiMonopattino;
	}// end metodo getPostiMonopattino()
	
	/*
	 * restituisci tutti gli abbonamenti presenti nel sistema
	 */
	public HashSet<Abbonamento> getAbbonamenti() {
		return this.abbonamenti;
	}// end metodo getAbbonamenti()
	
	/*
	 * permette di impostare gli abbonamenti 
	 */
	@Override
	public void setAbbonamenti(List<Abbonamento> lista) {
		this.abbonamenti = new HashSet<Abbonamento>(lista);
	}// end metodo setAbbonamenti()

	/*
	 * metodo per aggiungere un abbonamento
	 */
	public void aggiungiAbbonamento(Abbonamento a) {
		this.abbonamenti.add(a);
	}// end aggiungiAbbonamento()

	/* metodo per aggiungere un veicolo al parcheggio, se e' presente un posto libero
	 * altrimenti viene lanciata un'eccezione.
	 */
	@Override
	public Posto aggiungiVeicolo(VeicoloInt v){
		Posto posto = null;
		
		// controllo se la targa e' stata inserita correttamente
		if(v.getTarga().equals("")) {
			throw new TargaNonPresenteException("Attenzione: non e' stata inserita la targa!");
		}
		
		// controllo di non inserire auto con targhe uguali
		Optional<VeicoloInt> veicoloGiaPresente = this.listaVeicoliPresenti().stream()
								   										     .filter(ve -> ve.getTarga().equals(v.getTarga()))
								   										     .findAny();
		if(veicoloGiaPresente.isPresent()) {
			throw new TargheUgualiException("Attenzione: auto con la stessa targa presente!");
		}
		
		/* controllo se il veicolo e' un auto o una moto */
		if(v instanceof Auto) {
			if(v.getCarburante().equals(Alimentazione.ELETTRICA)) {
				posto = this.filtraAggiungi(p -> p instanceof PostoElettrico, v);
			} else {
				posto = this.filtraAggiungi(p -> p instanceof PostoAuto, v);
			}
		} else if(v instanceof Moto){
			posto = this.filtraAggiungi(p -> p instanceof PostoMoto, v);
		}
		return posto;
	}// end metodo aggiungiVeicolo()
	
	/* metodo per liberare un posto del parcheggio
	 * restituisce il prezzo da pagare
	 */
	@Override
	public double liberaPosto(Posto p) {
		double prezzo = 0;
		/* controllo se e' presente o meno il posto da liberare.
		 * Se e' presente, ottengo la referenza rispetto alla collezione.
		 */
		Optional<Posto> postoDaLiberare = this.postiDisponibili.stream()
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
			postoDaLiberare.get().liberaPosto();	

			if(ab.isEmpty()) {
				// calcolo del prezzo per la sosta
				prezzo = postoDaLiberare.get().costoOccupazione();
			}
		}
		// approssimazione a due cifre decimali
		return (Math.round(prezzo * 100.0)/100.0);
	}// end metodo liberaPosto()
	
	/*
	 * restituisce tutti i veicoli presenti nel parcheggio
	 */
	@Override
	public Set<VeicoloInt> listaVeicoliPresenti() {
		return this.postiDisponibili.stream()
						            .filter(p -> p.isLibero() == false)
						            .map(p -> ((AbstractPosto) p).getVeicolo().get())
						            .collect(Collectors.toSet());
	}// end metodo listaVeicoliPresenti()
	 
	/*
	 * metodo per noleggiare un monopattino
	 */
	@Override
	public Monopattino noleggiaMonopattino(Pers p) {
		Monopattino m = null;
		/* i monopattini possono essere noleggiati solo da una persona
		 * munita di abbonamento
		 */
		Optional<Abbonamento> esiste = this.abbonamenti.stream()
													   .filter(a -> a.getPersona().equals(p))
													   .findAny();
		if(esiste.isPresent()){
			Optional<Monopattino> tmp = this.postiMonopattino.stream()
															 .filter(mo -> mo.getDisponibile())
															 .findFirst();
			// controllo se e' disponibile almeno un monopattino
			if(tmp.isPresent()) {
				tmp.get().setDisponibile(false);
				m = tmp.get();
				m.setPersona(p);
				m.setOraNoleggiato(Instant.now());
			} else {
				// lancia eccezione per mancanza di monopattini
				throw new MonopattiniEsauritiException("Attenzione: Monopattini non disponibili!");
			}
		} else {
			// lancia eccezione in quanto la persona non ha l'abbonamento
			throw new PersonaSenzaAbbonamentoException("Attenzione: L'utente e' senza abbonamento!");
		}
		
		return m;
	}// end metodo noleggiaMonopattino()
	
	/*
	 * metodo per restituire un monopattino, precedentemente noleggiato
	 */
	@Override
	public double restituisciMonopattino(Pers p, Monopattino m) {
		double prezzo = 0;
		// imposto l'ora di fine noleggio
		m.setFineNoleggio(Instant.now());
		/* se la persona e' munita di un abbonamento premium non paga
		 * l'utilizzo del monopattino
		 */
		if(this.abbonamenti.stream()
						   .filter(a -> (a.getPersona().equals(p) && a.isPremium()))
						   .findAny()
						   .isEmpty()) {
			prezzo = MonopattinoImpl.COSTO * (Duration.between(m.getOraNoleggiato(), m.getFineNoleggio()).getSeconds());
		}
		// identifica il monopattino presente nel parcheggio e lo imposta come disponibile
		this.postiMonopattino.stream()
							 .filter(mo -> mo.equals(m))
							 .findAny()
							 .get()
							 .setDisponibile(true);
		
		// arrotondamento a due cifre decimali
		return (Math.round(prezzo * 100.0)/100.0);
	}// end restituisciMonopattino()

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postiDisponibili == null) ? 0 : postiDisponibili.hashCode());
		result = prime * result + ((postiMonopattino == null) ? 0 : postiMonopattino.hashCode());
		return result;
	}// end hashCode()

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParcheggioImpl other = (ParcheggioImpl) obj;
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
	}// end equals()
	
	@Override
	public String toString() {
		return "ParcheggioImpl [id=" + id + ", name=" + name + ", postiDisponibili=" + postiDisponibili
				+ ", postiMonopattino=" + postiMonopattino + "]";
	}// end toString()

	/* metodo per controllare se e' presente un posto libero, in caso contrario
	 * lancia un'eccezione (PostiFinitiException). Se il veicolo ha un'altezza maggiore
	 * rispetto al limite consentito, viene lanciata un'eccezione (AltezzaMassimaConsentitaException).
	 * Non sono ammesse auto a metano in parcheggi sotterranei (lancio dell'eccezione AutoMetanoNonAmmessaException)
	 */
	private Posto filtraAggiungi(Predicate<? super Posto> filtro, VeicoloInt v){
		/* ottengo un posto libero presente nel parcheggio */
		Optional<Posto> tmp = this.postiDisponibili.stream()
				   								   .filter(p -> p.isLibero() == true)
				   								   .filter(filtro)
				   								   .findFirst();
		if(tmp.isPresent()) {
			/* se il veicolo e' un auto e' necessario controllare che l'altezza 
			 * di quest'ultima non superi il limite massimo consentito
			 * e, se il parcheggio e' sotterraneo, si controlla la sua alimentazione
			 */
			if(v instanceof Auto) {
				if((double)this.sensoreAltezza.effettuaRilevamento((Auto)v) <= this.altezzaMassimaConsentita) {
					if(this.id.startsWith("S")  && 
					   !(tmp.get() instanceof PostoElettrico) &&
					   ((PostoAuto) tmp.get()).getSensoreCarburante().effettuaRilevamento((Auto)v).equals(Alimentazione.METANO)) {
						// lancio eccezione per l'alimentazione non consentita
						throw new AutoMetanoNonAmmessaException("Attenzione: Le auto a metano non possono parcheggiare in un parcheggio sotteraneo.");
					}
					// occupo il posto auto
					tmp.get().occupaPosto((Veicolo) v);
					return tmp.get();
				} else {
					//lancia eccezione per altezza non consentita
					throw new AltezzaMassimaConsentitaException("Attenzione: L'altezza del veicolo ha superato il limite massimo consentito!");
				}
					
			} else {
				/* il veicolo e' una moto e non effettua il controllo dell'altezza */
				tmp.get().occupaPosto((Veicolo) v);
				return tmp.get();
			}
		// se il veicolo e' elettrico e non sono piu' disponibili posti elettrici
		// si controlla se sono disponibili o meno posti per auto normali
		} else if(v.getCarburante().equals(Alimentazione.ELETTRICA)){
			return this.filtraAggiungi(p -> p instanceof PostoAuto, v);
		} else {
			//lancia eccezione per posti finiti
			throw new PostiFinitiException("Attenzione: I posti sono finiti");
		}
	}// end metodo filtraAggiungi()

}// end classe