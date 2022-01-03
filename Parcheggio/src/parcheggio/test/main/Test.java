package parcheggio.test.main;

import java.util.*;

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
		 * Test dei veicoli
		 * Funziona tutto
		 */
        Auto a1 = new Auto("ABC123ABC", 2021, Alimentazione.GPL, "Toyota", "Yaris", "Martin", "Berardi", 1.5);
        Moto m1 = new Moto("AAA999ZZZ", 1999, Alimentazione.BENZINA, "Honda", "SuHonda", "Radja", "Nainggolan", 300.0);
        System.out.println(a1);
        System.out.println("\n");
        System.out.println(m1);
        
        Auto a2 = new Auto("AAA000ZZZ", 2022, Alimentazione.DIESEL, "Mustang", "Mach-E", "Fabio", "Quagliarella", 1.5);
        Auto a3 = new Auto("GHF128SHD", 1914, Alimentazione.METANO, "Fiat", "Panda", "Vladimir", "Putin", 2.3);
        Auto a4 = new Auto("SHD237SHD", 1921, Alimentazione.GPL, "Clementoni", "Sapientino", "Paolo", "Bonolis", 0.2);
        Auto a5 = new Auto("ASK128SDJ", 3642, Alimentazione.ELETTRICA, "Hyundai", "Eddai", "Mike", "Tyson", 1.4);

        System.out.println("\n");
        
        if(a1.equals(a2)) /*Il metodo equals confronta le altezze delle auto*/
        	System.out.println("Ok Auto");
        
        Moto m2 = new Moto("AAA999ZZZ", 1999, Alimentazione.BENZINA, "Honda", "SuHonda", "Radja", "Nainggolan", 300.0);
        
        if(m1.equals(m2))
        	System.out.println("Ok Moto");
        
        List<Auto> listAuto = new ArrayList<>();
        listAuto.add(a1);
        listAuto.add(a2);
        listAuto.add(a3);
        listAuto.add(a4);
        listAuto.add(a5);
        
        listAuto.stream()
        		.filter(p -> p.getAltezza() <= 1.5)
        		.map(Auto::getTarga)
        		.forEach(System.out::println);
        
        
        
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
