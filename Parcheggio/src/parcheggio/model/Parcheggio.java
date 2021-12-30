package parcheggio.model;

import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;

import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.veicolo.Auto;
import parcheggio.model.veicolo.Moto;

public class Parcheggio {
	private LinkedList<Posto> postiDisponibili 		 = new LinkedList<Posto>();
	final private int postiTotaliAuto;
	private int autoParcheggiate = 0; // posti auto occupati
	private int motoParcheggiate = 0; // posti moto occupati
	final private int postiTotaliMoto;
	private LinkedList<Monopattino> postiMonopattino = new LinkedList<Monopattino>();
	final private int postiTotaliMonopattini;
	
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
	}

	public LinkedList<Monopattino> getPostiMonopattino() {
		return postiMonopattino;
	}

	public void aggiungiVeicolo(Veicolo v){
		//Veicolo tmp = null;
		Optional<Posto> tmp;
		if(v instanceof Auto) {
		//	tmp = v;
			tmp = this.filtraPostiLiberi(p -> p instanceof PostiAuto == true);
			this.aggiungiConEccezione(v, tmp);
		} else if(v instanceof Moto){
			// stessa cosa per le moto
			tmp =	this.filtraPostiLiberi(p -> p instanceof PostiMoto == true);
			this.aggiungiConEccezione(v, tmp);
		}
		
	}
	
	private Optional<Posto> filtraPostiLiberi(Predicate<Posto> filtro){
		Optional<Posto> tmp = this.postiDisponibili.stream()
				   								   .filter(p -> p.setVeicoloOccupante() == null)
				   								   .filter(filtro)
				   								   .findFirst();
		return tmp;
	}
	
	private void aggiungiConEccezione(Veicolo v, Optional<Posto> tmp) {
		if(tmp.isPresent()) {
			tmp.get().setVeicoloOccupante(v);
		} else {
			throw new PostiFiniti();/* DA VEDERE!!! */
		}
	}
	
}// end classe
