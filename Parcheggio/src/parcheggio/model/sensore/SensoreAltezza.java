package parcheggio.model.sensore;

import parcheggio.model.veicolo.Auto;

public class SensoreAltezza implements Sensore<Double>{

	@Override
	public Double effettuaRilevamento(Auto a) {
		/* restituisco l'altezza del veicolo che entra nel parcheggio */
		return new Double(a.getAltezza());
	}

}
