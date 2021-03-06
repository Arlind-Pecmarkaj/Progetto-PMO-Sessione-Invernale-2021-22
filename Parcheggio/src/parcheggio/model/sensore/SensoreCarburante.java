package parcheggio.model.sensore;

/* import utilizzati: */
import parcheggio.enumerations.Alimentazione;
import parcheggio.model.veicolo.Auto;

public class SensoreCarburante implements Sensore<Alimentazione>{
	
	@Override
	public Alimentazione effettuaRilevamento(Auto a) {
		/* restituisco l'alimentazione del veicolo */
		return a.getCarburante();
	}

}
