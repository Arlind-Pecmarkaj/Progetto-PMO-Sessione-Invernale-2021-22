package parcheggio.test.posto;


/**
 * 	@author tomma
 * 	
 * 	Classe per i test relativi alle funzionalità delle classi 
 * 	che implementano l'interfaccia IPosto
 */

import static org.junit.Assert.*;
import org.junit.*;
import parcheggio.enumerations.Alimentazione;
import parcheggio.model.posto.*;
import parcheggio.model.veicolo.*;


public class TestPosto {
	
	AbstractPosto postoMoto;
	Moto moto;

	@Before
	public void createData() {
		postoMoto = new PostoMoto();
		moto = new Moto("123", 1999, Alimentazione.BENZINA, "HONDA", "honda", "gianni", "fresca", 200.0, 0);
	}
	/*
	@Test
	public void testTempleteMethodSetPosto() {
		// controllo se viene settato il gisuto ID
		assertEquals("M1", postoMoto.getId()); 	
		// controllo se viene settata la giusta tariffa oraria
		assertEquals(0.5, postoMoto.getCostoOrario(), 0);
	}
	*/
	
	@Test
	public void testOccupaPosto() {
		postoMoto.occupaPosto(moto);
		assertEquals(moto.getTarga(), postoMoto.getVeicolo().get().getTarga());
	}
	
	@Test
	public void testLiberaPosto() {
		postoMoto.liberaPosto();
		assertFalse(postoMoto.getVeicolo().isPresent());
	}
	
	@Test
	public void testIsLibero() {
		postoMoto.occupaPosto(moto);
		assertFalse(postoMoto.isLibero());
		postoMoto.liberaPosto();
		assertTrue(postoMoto.isLibero());
	}
	
	@Test
	public void testTempoOccupazione() {
		postoMoto.occupaPosto(moto);
		try {
			// simulo 2 secondi di occupazione
			Thread.sleep(2000); 
		} catch (InterruptedException ex) {
			System.out.println("Eccezione catturata");
		}
		postoMoto.liberaPosto();
		postoMoto.tempoOccupazione();
		assertEquals(postoMoto.elapsedToString(), "00:00:02");
	}
	
	@Test(expected = NullPointerException.class)
	public void testTempoOccupazioneException() {
		postoMoto.tempoOccupazione();
	}
	
	@Test
	public void testCostoOccuapzione() {
		postoMoto.occupaPosto(moto);
		try {
			// simulo 2 secondi di occupazione
			Thread.sleep(2000); 
		} catch (InterruptedException ex) {
			System.out.println("Eccezione catturata");
		}
		postoMoto.liberaPosto();
		assertEquals(postoMoto.costoOccupazione(), 1.0);
	}
	/*
	@Test
	public void testPrintOrari() {
		System.out.println(postoMoto.getOrarioArrivo());
		System.out.println(postoMoto.getOrarioUscita());
//		System.out.println(postoMoto.tempoOccupazione());
		System.out.println(postoMoto.orarioToString(true));
		System.out.println(postoMoto.orarioToString(false));
		System.out.println(postoMoto.elapsedToString());
		
	}
	
	@Test
	public void testRicaricaAutoElettrica() {
		PostoElettrico postoElettrico = new PostoElettrico();
		postoElettrico.occupaPosto(new Auto("CIAO", 1999, Alimentazione.ELETTRICA,
                    						"FIAT", "600", "Massimo", "Minimo", 1.40, 300.4, 90.08));
		// ricarica fino al 70%
//		postoElettrico.getColonnaSupercharger().ricaricaVeicolo(70, postoElettrico.getVeicolo().get()); 
		// ricarica fino al 100%
		
		System.out.println(postoElettrico.getColonnaSupercharger().getPercentualeAttuale(postoElettrico.getVeicolo().get()));
		
		postoElettrico.getColonnaSupercharger().ricaricaVeicolo(100, postoElettrico.getVeicolo().get()); 
		
		System.out.println(postoElettrico.getColonnaSupercharger().getTempoRicarica());
		System.out.println(postoElettrico.getColonnaSupercharger().getTempoRicaricaHR());
		System.out.println(postoElettrico.getVeicolo().get().getCarburanteAttuale());
		
	}
	
	/*@Test(expected = IllegalStateException.class)
	public void testRicaricaAutoNONElettrica() {
		AbstractPosto postoElettrico = new PostoElettrico();
		postoElettrico.occupaPosto(new Auto("CIAO", 1999, Alimentazione.DIESEL,
                    						"FIAT", "600", "Massimo", "Minimo", 1.40, 40.5, 12.0));
		
	}*/

}