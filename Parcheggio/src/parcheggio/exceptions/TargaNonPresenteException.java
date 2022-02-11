package parcheggio.exceptions;

@SuppressWarnings("serial")
public class TargaNonPresenteException extends RuntimeException{
	public TargaNonPresenteException(String msg) {
		super(msg);
	}
}
