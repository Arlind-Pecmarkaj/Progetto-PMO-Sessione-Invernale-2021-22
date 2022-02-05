package parcheggio.model.monopattino;

import java.time.Instant;

import parcheggio.model.persona.Pers;
import parcheggio.model.persona.Persona;

public interface Monopattino {
	
	boolean getDisponibile();
	
	Instant getOraNoleggiato();
	
	void setDisponibile(boolean d);
	
	void setOraNoleggiato(Instant oraNoleggiato);
	
	Instant getFineNoleggio();
	
	void setFineNoleggio(Instant instant);
	
	void setPersona(Pers p);
	
	Pers getPersona();
}
