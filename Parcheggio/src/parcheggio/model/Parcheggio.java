package parcheggio.model;

import java.util.LinkedList;
import java.util.Optional;

import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.veicolo.Auto;

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

	public void aggiungiVeicolo(Veicolo v) throws PostiFiniti{
		//Veicolo tmp = null;
		if(v instanceof Auto) {
		//	tmp = v;
			Optional<Posto> tmp =this.postiDisponibili.stream()
								 				      .filter(p -> p.setVeicoloOccupante() == null)
								 				      .filter(p -> p instanceof PostiAuto == true)// filtra solo i posti per le auto(?)
								 				      .findFirst();
			if(tmp.isPresent()) {
				tmp.get().setVeicoloOccupante(v);
			} else {
				throw new PostiFiniti();
			}
		} else {
			// stessa cosa per le moto
			Optional<Posto> tmp = this.postiDisponibili.stream()
				      								   .filter(p -> p.setVeicoloOccupante() == null)
				      								   .filter(p -> p instanceof PostiMoto == true)// filtra solo i posti per le auto(?)
				      								   .findFirst();
			if(tmp.isPresent()) {
				tmp.get().setVeicoloOccupante(v);
			} else {
				throw new PostiFiniti();
			}
		}
		
		/*	if(this.autoParcheggiate < this.postiTotaliAuto) {// stream
				for(Posto p: this.postiDisponibili) {// non va bene!!!
					if(p instanceof PostiAuto) {
						if(p.getVeicoloOccupante() == null) {
							p.setVeicoloOccupante(v);
						}
					}
				}
			}*/
	}
	
	private <X> Optional<Posto> filtraPostiLiberi(X Posto){
		Optional<Posto> tmp = this.postiDisponibili.stream()
				   .filter(p -> p.setVeicoloOccupante() == null)
				   .filter(p -> p instanceof PostiMoto == true)// filtra solo i posti per le auto(?)
				   .findFirst();
		if(tmp.isPresent()) {
			tmp.get().setVeicoloOccupante(v);
		} else {
			throw new PostiFiniti();
		}
		return null;
	}
	
}// end classe
