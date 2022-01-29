package parcheggio.view.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JComboBox;

//import parcheggio.enumerations.Alimentazione;
import parcheggio.model.veicolo.*;
import parcheggio.model.Parcheggio;
import parcheggio.model.ParcheggioImpl;
import parcheggio.model.posto.PostoAuto;
import parcheggio.model.posto.PostoElettrico;
import parcheggio.model.posto.PostoMoto;

public class GUIParcheggio extends JFrame{

	private JPanel container = new JPanel();
	private JPanel top = new JPanel();
	private JPanel topB = new JPanel();
	private JPanel topA = new JPanel();
	private JPanel bottom = new JPanel();
	private ArrayList<JButton> bottoniVeicoli = new ArrayList<JButton>();
	private ArrayList<JButton> bottoniMonopattini = new ArrayList<JButton>();
	private JButton in = new JButton("In");
	private JTextField targa = new JTextField();
	private JTextField codiceFiscale = new JTextField();
	private String[] veicoliDisponibili = { "Auto", "Moto" };
	private JComboBox listaVeicoli = new JComboBox(veicoliDisponibili);
	
	/*
	 * costruzione del frame
	 */
	public GUIParcheggio(Parcheggio p) {
		super("Parcheggio: " + ((ParcheggioImpl)p).getName());
	/*	Container a = this.getContentPane();
		Container b = this.getContentPane();*/
		int numeroPosti = p.getNPostiSpecifici(po -> po instanceof PostoAuto) +
						  p.getNPostiSpecifici(po -> po instanceof PostoMoto) +
						  p.getNPostiSpecifici(po -> po instanceof PostoElettrico);
		
		this.container.setLayout(new GridLayout(2, 1));
		this.topA.setLayout(new FlowLayout());
		this.topB.setLayout(new FlowLayout());
		this.top.setLayout(new GridLayout(1, 2));
		//top.add(new JLabel());
		this.bottom.setLayout(new FlowLayout());
		//bottom.add(new JLabel());
		
		for(int i = 0; i < p.getNPostiSpecifici(po -> po instanceof PostoAuto); i++) {
			this.bottoniVeicoli.add(new JButton("Posto auto"));
		}
		for(int i = 0; i < p.getNPostiSpecifici(po -> po instanceof PostoMoto); i++) {
			this.bottoniVeicoli.add(new JButton("Posto moto"));
		}
		for(int i = 0; i < p.getNPostiSpecifici(po -> po instanceof PostoElettrico); i++) {
			this.bottoniVeicoli.add(new JButton("Posto auto elettriche"));
		}
		for(JButton jb: this.bottoniVeicoli) {
			jb.setBackground(Color.green);
			this.topB.add(jb);
		}
		
		for(int i = 0; i < ((ParcheggioImpl)p).getPostiMonopattino().size(); i++){
			this.bottoniMonopattini.add(new JButton("M" + i));
		}
		for(JButton jb: this.bottoniMonopattini) {
			jb.setBackground(Color.green);
			this.topA.add(jb);
		}
		
		/**/
		
		this.in.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(listaVeicoli.getSelectedItem().equals("Auto")) {
					p.aggiungiVeicolo(new Auto(targa.getText(),
							   				   2001,
							   				   Alimentazione.BENZINA,
							   				   "a",
							   				   "b",
							   				   "Pietro",
							   				   "Augusto",
							   				   195.5,
							   				   205.0,
							   				   50.0));
					
				} else if(listaVeicoli.getSelectedItem().equals("Moto")) {
					p.aggiungiVeicolo(new Moto(targa.getText(),
											   2001,
											   Alimentazione.BENZINA,
											   "a",
											   "b",
											   "ciao",
											   "prova",
											   200.2,
											   20));
				}
			}
		});
		
		/**/
		
		this.bottom.add(this.listaVeicoli);
		this.targa.setText("Targa");
		this.bottom.add(this.targa);
		this.codiceFiscale.setText("Codice fiscale utente");
		this.bottom.add(this.codiceFiscale);
		this.bottom.add(this.in);
		
		this.top.add(this.topA);
		this.top.add(this.topB);
		
		this.container.add(this.top);
		this.container.add(this.bottom);
		
		this.add(this.container);
		
		this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
}
