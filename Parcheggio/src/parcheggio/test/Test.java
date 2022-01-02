package parcheggio.test;

import java.io.FileNotFoundException;

import parcheggio.controller.ReaderParcheggio;

/* Classe riservata ai test delle classi e dei metodi.
 * Specificare nominativo, piccolo commento sul test da effettuare
 * ed eventuali problemi se fossero presenti.
 * NOTA: separare ogni fase di test. */

public class Test {
        /* Test Netbeans */
	public static void main(String[] args) {
		/* TEST DI ARLIND PECMARKAJ 
		 * Test del file reader
		 * Problemi riscontrati:
		 *  - 30/12/2021 Il metodo funziona, ma senza l'implementazione finale di Parcheggio e Posto da eccezione.
		 */
		try {
			ReaderParcheggio rP = new ReaderParcheggio("C:\\Users\\arlin\\Documents\\PMO\\Progetto-PMO-Sessione-Invernale-2021-22\\Parcheggio\\salvataggio\\Veicoli.txt");
			rP.read();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		

	}

}
