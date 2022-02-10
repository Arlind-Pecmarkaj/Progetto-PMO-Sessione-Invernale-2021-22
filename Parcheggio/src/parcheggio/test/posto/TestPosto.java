package parcheggio.test.posto;


/**
 * 	
 * 	Classe per i test relativi alle funzionalità
 *  fornite dall'interfaccia Posto e Supercharger
 * 
 */

import static org.junit.Assert.*;
import org.junit.*;

import static parcheggio.enumerations.Alimentazione.*;


import parcheggio.exceptions.IllegalChargerException;
import parcheggio.exceptions.NonElettricaException;

import parcheggio.model.posto.*;
import parcheggio.model.veicolo.*;


public class TestPosto {
	
	AbstractPosto postoMoto;
	Moto moto;
	
	PostoElettrico postoElettrico;
	Auto autoElettrica;

	@Before
	public void createData() {
		postoMoto = new PostoMoto();
		moto = new Moto("123", 1999, BENZINA, "HONDA", "honda", "gianni", "fresca", 200.0, 0);
		
		postoElettrico = new PostoElettrico();
		autoElettrica = new Auto("targaAuto", 2020, ELETTRICA , "Tesla", "Model 3", "George", "Maggi", 1.60, 300.4, 90.08);
		postoElettrico.occupaPosto(autoElettrica);
	}
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void testTempleteMethodSetPosto() {
		assertEquals("M0000", postoMoto.getId());
		assertEquals(0.5, postoMoto.getCostoOrario(), 0);
	}
	
	
	@Test
	public void testOccupaPosto() {
		postoMoto.occupaPosto(moto);
		assertEquals(moto.getTarga(), postoMoto.getVeicolo().get().getTarga());
	}
	
	@Test(expected = NonElettricaException.class)
	public void testOccupaPostoException() {
		AbstractPosto postoElettrico = new PostoElettrico();
		postoElettrico.occupaPosto(moto);
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
		assertEquals(1.0, postoMoto.costoOccupazione(), 0.0);
	}
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void testPrintOrari() {
		postoMoto.occupaPosto(moto);
		try {
			// simulo 2 secondi di occupazione
			Thread.sleep(2000); 
		} catch (InterruptedException ex) {
			System.out.println("Eccezione catturata");
		}
		postoMoto.liberaPosto();
		System.out.println(postoMoto.getOrarioArrivo());
		System.out.println(postoMoto.getOrarioUscita());
		System.out.println(postoMoto.orarioToString(true));
		System.out.println(postoMoto.orarioToString(false));
		System.out.println(postoMoto.elapsedToString());
		
	}
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void testRicaricaVeicolo() {
		assertEquals(30, Math.ceil(postoElettrico.getColonnaSupercharger().getPercentualeAttuale(postoElettrico.getVeicolo().get())), 0);
		postoElettrico.getColonnaSupercharger().ricaricaVeicolo(70, postoElettrico.getVeicolo().get());
		assertEquals(70, postoElettrico.getColonnaSupercharger().getPercentualeAttuale(autoElettrica), 0);
	}
	
	@Test
	public void testTempoRicaricaHR() {
		postoElettrico.getColonnaSupercharger().ricaricaVeicolo(100, postoElettrico.getVeicolo().get());
		assertEquals(postoElettrico.getColonnaSupercharger().getTempoRicaricaHR(), "169 min");
		assertEquals(100, Math.ceil(postoElettrico.getColonnaSupercharger().getPercentualeAttuale(postoElettrico.getVeicolo().get())), 0);
	}
	
	@Test(expected = IllegalChargerException.class)
	public void testRicaricaVeicoloException() {
		postoElettrico.getColonnaSupercharger().ricaricaVeicolo(10, postoElettrico.getVeicolo().get());
		postoElettrico.getColonnaSupercharger().ricaricaVeicolo(200, postoElettrico.getVeicolo().get());
	}

	
	

	
}