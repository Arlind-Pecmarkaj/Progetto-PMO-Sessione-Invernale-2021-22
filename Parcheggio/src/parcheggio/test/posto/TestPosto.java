package parcheggio.test.posto;


/**
 * 	@author tomma
 * 	
 * 	Classe per i test relativi alle funzionalità delle classi 
 * 	che implementano l'interfaccia IPosto
 */


//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.*;
import parcheggio.model.posto.*;
import parcheggio.model.veicolo.*;


public class TestPosto {
	/*
	AbstractPosto postoMoto;

	@Before
	public void createData() {
		// inizializzazione prima di eseguire un qualunque test
		postoMoto = new PostoMoto();
		postoMoto.occupaPosto(new Moto("123", 1999, Alimentazione.BENZINA,
                "HONDA", "honda", "gianni",
                "fresca", 200.0, 0));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postoMoto.liberaPosto();
		postoMoto.tempoOccupazione();
	}
	
	@Test
	public void testTempleteMethodSetPosto() {
		// controllo se viene settato il gisuto ID
		assertEquals("M1", postoMoto.getId()); 	
		// controllo se viene settata la giusta tariffa oraria
		assertEquals(0.5, postoMoto.getCostoOrario(), 0);
	}
	
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
	public void testIsLibero() {
		// controllo se un parcheggio è libero
		assertTrue(postoMoto.isLibero());
	}*/
	
	@Test
	public void testRicaricaAutoElettrica() {
		PostoElettrico postoElettrico = new PostoElettrico();
		postoElettrico.occupaPosto(new Auto("CIAO", 1999, Alimentazione.ELETTRICA,
                    						"FIAT", "600", "Massimo", "Minimo", 1.40, 300.4, 90.08));
		// postoElettrico.ricaricaAuto(70); // ricarica fino al 70%
		postoElettrico.ricaricaAuto(100); // ricarica fino al 100%
		System.out.println(postoElettrico.getColonnaSupercharger().getTempoRicarica());
		System.out.println(postoElettrico.getVeicolo().get().getCarburanteAttuale());
		
	}
	
	/*@Test(expected = IllegalStateException.class)
	public void testRicaricaAutoNONElettrica() {
		AbstractPosto postoElettrico = new PostoElettrico();
		postoElettrico.occupaPosto(new Auto("CIAO", 1999, Alimentazione.DIESEL,
                    						"FIAT", "600", "Massimo", "Minimo", 1.40, 40.5, 12.0));
		
	}*/

}