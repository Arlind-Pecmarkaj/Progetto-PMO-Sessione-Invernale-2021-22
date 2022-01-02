package parcheggio.test.Main;

import parcheggio.controller.ReaderParcheggio;
import parcheggio.model.veicolo.Alimentazione;
import parcheggio.model.veicolo.Auto;
import parcheggio.model.veicolo.Moto;

/* Classe riservata ai test delle classi e dei metodi tramite Main.
 * Specificare nominativo, piccolo commento sul test da effettuare
 * ed eventuali problemi se fossero presenti.
 * NOTA: separare ogni fase di test. */

public class Test {
	public static void main(String[] args) {
		
		/* TEST DI MARTIN BERARDI
		 * Test dei tipi Veicolo
		 */
        Auto a1 = new Auto("ABC23ABC", 2021, Alimentazione.GPL, "Toyota", "Yaris", "Martin", "Berardi", 1.5, 5);
        Moto m1 = new Moto("AAA999ZZZ", 1999, Alimentazione.BENZINA, "Honda", "SuHonda", "Radja", "Nainggolan", 300.0);
        System.out.println(a1);
        System.out.println("\n");
        System.out.println(m1);
        /*Auto a2 = new Auto("ABC23ABC", 2021, Alimentazione.GPL, "Toyota", "Yaris", "Martin", "Berardi", 1.5, 5);
        if(a1.equals(a2))
        	System.out.println("Ok");*/
        
        
		/* TEST DI ARLIND PECMARKAJ 
		 * Test del file reader
		 * Problemi riscontrati:
		 *  - 30/12/2021 Il metodo funziona, ma senza l'implementazione finale di Parcheggio e Posto da eccezione.
		 */
		try {
			ReaderParcheggio rP = new ReaderParcheggio("C:\\Users\\arlin\\Documents\\PMO\\Progetto-PMO-Sessione-Invernale-2021-22\\Parcheggio\\salvataggio\\Veicoli.txt");
			//rP.read();
		} catch (Exception e) {
			System.out.println(e);
		}
		

	}

}
