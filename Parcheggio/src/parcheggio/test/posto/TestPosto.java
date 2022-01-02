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
import parcheggio.enumerations.Alimentazione;
import parcheggio.model.sensore.Sensore;

import java.util.*;

public class TestPosto {
	
	AbstractPosto postoMoto;

	@Before
	public void createData() {
		// inizializzazione prima di eseguire un qualunque test
		
		
		
//		IPosto postoAuto = new PostoAuto(new SensoreCarburante());
//		postoAuto.setPosto("1", 1.0);
		postoMoto = new PostoMoto();
		postoMoto.setPosto("1", 1.0);
//		IPosto postoMoto = new PostoMoto();
//		postoMoto.setPosto();
		
	}
	
	@Test
	public void testTempleteMethodSetPosto() {
		// controllo se viene settato il gisuto ID
		assertEquals("M1", postoMoto.getId()); 	
		// controllo se viene settata la giusta tariffa oraria
		assertEquals(0.5, postoMoto.getId());
	}
	
	@Test
	public void testIsLibero() {
		// controllo se un parcheggio è libero
		assertTrue(postoMoto.isLibero());
	}
	
	@Test
	public void testTempoOccupazione() {
		// TODO
	}
	
//	@Test(expected = IllegalStateException.class)

}