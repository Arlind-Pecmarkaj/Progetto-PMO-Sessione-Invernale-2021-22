package parcheggio.test;

import java.time.LocalDate;
import java.util.*;

import parcheggio.controller.ReaderWriterFromFile;
import parcheggio.model.GestioneParcheggio;
import parcheggio.model.ParcheggioImpl;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.persona.Persona;
import parcheggio.model.veicolo.Alimentazione;
import parcheggio.model.veicolo.Auto;
import parcheggio.model.veicolo.Moto;
import parcheggio.view.GUI.GUIGestione;

/* Classe riservata ai test delle classi e dei metodi tramite Main.
 * Specificare nominativo, piccolo commento sul test da effettuare
 * ed eventuali problemi se fossero presenti.
 * NOTA: separare ogni fase di test. */

public class Test {
	public static void main(String[] args) {
		
		/* TEST DI MARTIN BERARDI
		 * Test dei veicoli
		 * Funziona tutto
		 * 
		 * Aggiornamento del 08/01/2022:
		 * Le istanze danno errore perchè ho aggiunto capienzaMassima e carburanteAttuale
		 */
        Auto a1 = new Auto("ABC123ABC", 2021, Alimentazione.GPL, "Toyota", "Yaris", "Martin", "Berardi", 1.5, 50, 30);
        Moto m1 = new Moto("AAA999ZZZ", 1999, Alimentazione.BENZINA, "Honda", "SuHonda", "Radja", "Nainggolan", 300.0, 30.0);
        System.out.println(a1);
        System.out.println("\n");
        System.out.println(m1);
        
        Auto a2 = new Auto("AAA000ZZZ", 2022, Alimentazione.DIESEL, "Mustang", "Mach-E", "Fabio", "Quagliarella", 1.5, 70, 30);
        Auto a3 = new Auto("GHF128SHD", 1914, Alimentazione.METANO, "Fiat", "Panda", "Vladimir", "Putin", 2.3, 60, 10);
        Auto a4 = new Auto("SHD237SHD", 1921, Alimentazione.GPL, "Clementoni", "Sapientino", "Paolo", "Bonolis", 0.2, 60, 4);
        Auto a5 = new Auto("ASK128SDJ", 3642, Alimentazione.ELETTRICA, "Hyundai", "Eddai", "Mike", "Tyson", 1.4, 500, 300);

        System.out.println("\n");
        
        if(a1.equals(a2)) /*Il metodo equals confronta le altezze delle auto*/
       	System.out.println("Ok Auto");
        
        Moto m2 = new Moto("AAA999ZZZ", 1999, Alimentazione.BENZINA, "Honda", "SuHonda", "Radja", "Nainggolan", 300.0, 80);
        
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
        
        System.out.println("-------------------------------------------------------------------------------------------");
        
		/* TEST DI ARLIND PECMARKAJ 
		 * Test del file reader
		 * Problemi riscontrati:
		 *  - 30/12/2021 Il metodo funziona, ma senza l'implementazione finale di Parcheggio e Posto da eccezione.
		 *  - 06/01/2022 Il metodo funziona.
		 */
        GestioneParcheggio g = new GestioneParcheggio();
        ReaderWriterFromFile rP = new ReaderWriterFromFile("C:\\Users\\leona\\Documents\\Università\\PMO\\Progetto\\progetto_pmo\\Parcheggio\\salvataggio\\Veicoli.txt");
		try {	
			g = rP.read();
		} catch (Exception e) {
			System.out.println("Errore reader: " + e);
		}		
		System.out.println("Situazione con gestionale: " + g);
		g.aggiornaAbbonamenti();
		
		System.out.println("-------------------------------------------------------------------------------------------");
		
		/* TEST DI Leonardo Bigelli 
		 * Test del file Parcheggio.java
		 * Problemi riscontrati:
		 *  -impossibile effettuare il test di liberaPosto();
		 *  -rimane da effettuare il test ai due metodi per gestire il noleggio di monopattini;
		 */
		ParcheggioImpl p = new ParcheggioImpl("prova", "parcheggio_prova", 5,3,1,0, 1000);//parcheggio senza monopattini
		System.out.println(p.getPostiDisponibili()); /* OK */
		System.out.println(p.getPostiDisponibili().size());	/* OK */
		p.aggiungiVeicolo(new Auto("ABC123ABC", 2021, Alimentazione.GPL, "Toyota", "Yaris", "Martin", "Berardi", 1.5, 50, 3)); /* OK */
		System.out.println(p.getPostiDisponibili()); /* problema con la tariffa(?) */
		/* p.liberaPosto(posto), NON E' POSSIBILE DA PROVARE */
		p.aggiungiVeicolo(new Moto("AAA999ZZZ", 1999, Alimentazione.BENZINA, "Honda", "SuHonda", "Radja", "Nainggolan", 300.0, 56)); /* OK */
		System.out.println(p.getPostiDisponibili()); /* OK */
		System.out.println("\nVeicoli presenti:");
		System.out.println(p.listaVeicoliPresenti()); /* OK */
		
		/* TEST DI ARLIND PECMARKAJ
		 * Test del file writer
		 * Problemi: al 06/01/2022 nessuno.
		 */
		System.out.println("-------------------------------------------------------------------------------------------");
		Persona pers = new Persona("PRRRR0001038", "Mario", "Verdi", LocalDate.of(1976, 4, 3), "Italia");
		Abbonamento ab  = new Abbonamento(20221, "CC532FD", pers, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
		Abbonamento ab2 = new Abbonamento(20222, "FG554AB", pers, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31), true);
		g.aggiungiParcheggio(p);
		g.aggiungiAbbonamento(ab);
		g.aggiungiAbbonamento(ab2);
		
		/* TEST DI ARLIND PECMARKAJ
		 * Test GUI Gestione
		 * 
		 */
		new GUIGestione(g, rP);

	}

}
