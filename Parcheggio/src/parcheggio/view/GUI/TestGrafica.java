package parcheggio.view.GUI;

import parcheggio.model.ParcheggioImpl;

public class TestGrafica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIParcheggio win = new GUIParcheggio(new ParcheggioImpl("prova", "parcheggio_prova", 5,3,1,5, 1000));
	}

}
