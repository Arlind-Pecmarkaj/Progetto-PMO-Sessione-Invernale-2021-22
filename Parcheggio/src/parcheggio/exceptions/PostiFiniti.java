package parcheggio.exceptions;

public class PostiFiniti extends RuntimeException {
	public PostiFiniti(String messaggio) {
		super(messaggio);
	}
}
