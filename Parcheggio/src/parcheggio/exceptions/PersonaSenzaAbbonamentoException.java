package parcheggio.exceptions;

@SuppressWarnings("serial")
public class PersonaSenzaAbbonamentoException extends RuntimeException{
	public PersonaSenzaAbbonamentoException(String msg) {
		super(msg);
	}
}
