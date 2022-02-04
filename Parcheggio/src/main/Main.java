package main;

import java.io.IOException;

import parcheggio.controller.Controller;

public class Main {

	public static void main(String[] args) {
		Controller c = new Controller("C:\\Users\\arlin\\Documents\\PMO\\Progetto-PMO-Sessione-Invernale-2021-22\\Parcheggio\\salvataggio\\Veicoli.txt");
		try {
			c.start();
		} catch (IOException e) {
			System.out.println();
		}
	}

}
