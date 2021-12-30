package parcheggio.model;

public class PostiFiniti extends Exception {
	public PostiFiniti(Throwable err) {
		super("Eccezione: I posti sono finiti", err);
	}
}
