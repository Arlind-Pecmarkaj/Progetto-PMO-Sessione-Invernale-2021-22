package parcheggio.model.sensore;

import parcheggio.model.veicolo.Auto;

public interface Sensore<X> {
	X effettuaRilevamento(Auto a); /* il valore di ritorno pu� essere double o Alimentazione */
}
