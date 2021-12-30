package parcheggio.model.sensore;

import parcheggio.model.veicolo.Auto;

public interface Sensore<X> {
	X effettuaRilevamento(Auto a); /* il valore di ritorno può essere double o Alimentazione */
}
