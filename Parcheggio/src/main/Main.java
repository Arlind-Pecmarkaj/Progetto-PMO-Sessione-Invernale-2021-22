package main;

import java.io.IOException;

import parcheggio.controller.Controller;

public class Main {

	public static void main(String[] args) {
		Controller c = new Controller("salvataggio//gestione.txt");
		try {
			c.start();
		} catch (IOException e) {
			System.out.println();
		}
	}

}
