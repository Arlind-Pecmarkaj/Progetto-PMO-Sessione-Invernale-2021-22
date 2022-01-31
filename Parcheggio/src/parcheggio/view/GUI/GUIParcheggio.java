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
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import static javax.swing.JOptionPane.showMessageDialog;

//import parcheggio.enumerations.Alimentazione;
import parcheggio.model.veicolo.*;
import parcheggio.exceptions.AltezzaMassimaConsentitaException;
import parcheggio.exceptions.MonopattiniEsauritiException;
import parcheggio.exceptions.PersonaSenzaAbbonamentoException;
import parcheggio.exceptions.PostiFinitiException;
import parcheggio.model.Parcheggio;
import parcheggio.model.ParcheggioImpl;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.persona.Persona;
import parcheggio.model.posto.Posto;
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
	private ArrayList<Posto> posti;
	private HashSet<Abbonamento> abbonamenti;
	private LinkedList<JButton> bottoniMonopattini = new LinkedList<JButton>();
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
		
		this.abbonamenti = ((ParcheggioImpl) p).getAbbonamenti();
		
		int numeroPosti = p.getNPostiSpecifici(po -> po instanceof PostoAuto) +
						  p.getNPostiSpecifici(po -> po instanceof PostoMoto) +
						  p.getNPostiSpecifici(po -> po instanceof PostoElettrico);
		
		this.container.setLayout(new GridLayout(2, 1));
		this.topA.setLayout(new FlowLayout());
		this.topB.setLayout(new FlowLayout());
		this.top.setLayout(new GridLayout(1, 2));
		this.bottom.setLayout(new FlowLayout());
		
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
			
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!GUIParcheggio.this.posti.get(GUIParcheggio.this.bottoniVeicoli.lastIndexOf(jb)).isLibero()) {
						double prezzoDaPagare = p.liberaPosto(GUIParcheggio.this.posti.get(GUIParcheggio.this.bottoniVeicoli.lastIndexOf(jb)));
						jb.setBackground(Color.green);
						showMessageDialog(null,"Posto liberato con successo!\n Costo: " + 
										  prezzoDaPagare + " euro");
					} else {
						showMessageDialog(null,"Il posto auto è gia' libero!");
					}
				}
			});
			
			this.topB.add(jb);
		}
		
		for(int i = 0; i < ((ParcheggioImpl)p).getPostiMonopattino().size(); i++){
			this.bottoniMonopattini.add(new JButton("M" + i));
		}
		for(JButton jb: this.bottoniMonopattini) {
			jb.setBackground(Color.green);
			this.topA.add(jb);
		}
		
		this.posti = ((ParcheggioImpl)p).getPostiDisponibili();
		
		JTextField nome = new JTextField("Nome");
		JTextField cognome = new JTextField("Cognome");
		JComboBox listaAlimentazioni = new JComboBox(Alimentazione.values());
		JCheckBox noleggiaMonopattino = new JCheckBox("Noleggia monopattino");
		
		/*
		 * implementazione dell'inserimento di un veicolo all'interno del parcheggio 
		 */
		this.in.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				if(GUIParcheggio.this.listaVeicoli.getSelectedItem().equals("Auto")) {
					Veicolo v = new Auto(targa.getText(),
			   				   	r.nextInt(2022 - 1980) + 1980,
			   				   	(Alimentazione) listaAlimentazioni.getSelectedItem(),
			   				   	"a",
			   				   	"b",
			   				   	nome.getText(),
			   				   	cognome.getText(),
			   				   	100 + (500 - 100) * r.nextDouble(),
			   				   	200 + (300 - 200) * r.nextDouble(),
			   				   	0 + (300 - 0) * r.nextDouble());
					
					GUIParcheggio.this.aggiornamento(v, p);
					
				} else if(GUIParcheggio.this.listaVeicoli.getSelectedItem().equals("Moto")) {
					Veicolo v = new Moto(targa.getText(),
								r.nextInt(2022 - 1980) + 1980,
								(Alimentazione) listaAlimentazioni.getSelectedItem(),
								"a",
								"b",
								nome.getText(),
								cognome.getText(),
								200 + (300 - 200) * r.nextDouble(),
								0 + (300 - 0) * r.nextDouble());
					
					GUIParcheggio.this.aggiornamento(v, p);
					
				}
				if(noleggiaMonopattino.isSelected()) {
					// generazione di una data casuale
					long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
					long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
					long tmpDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
					LocalDate tmpDate = LocalDate.ofEpochDay(tmpDay);
					try {
						p.noleggiaMonopattino(new Persona(codiceFiscale.getText(),
														  nome.getText(),
														  cognome.getText(),
														  tmpDate,
														  "Italia"
														  ));
						GUIParcheggio.this.bottoniMonopattini.getLast().setBackground(Color.red);
					}catch(MonopattiniEsauritiException m) {
						showMessageDialog(null, "Attenzione! Monopattini esauriti");
					}catch(PersonaSenzaAbbonamentoException m) {
						showMessageDialog(null, "Attenzione! L'utente è sprovvisto di abbonamento. " +
									      "Monopattino non noleggiato");
					}
				}
			}
		});
		
		/**/
		
		this.bottom.add(this.listaVeicoli);
		this.targa.setText("Targa");
		this.bottom.add(this.targa);
		this.codiceFiscale.setText("Codice fiscale utente");
		this.bottom.add(this.codiceFiscale);
		this.bottom.add(nome);
		this.bottom.add(cognome);
		this.bottom.add(listaAlimentazioni);
		this.bottom.add(this.in);
		this.bottom.add(noleggiaMonopattino);
		
		this.top.add(this.topA);
		this.top.add(this.topB);
		
		this.container.add(this.top);
		this.container.add(this.bottom);
		
		this.add(this.container);
		
		this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	private void aggiornamento(Veicolo v, Parcheggio p) {
		try {
			Optional<Posto> postoOccupato = Optional.empty();
			postoOccupato = Optional.ofNullable(p.aggiungiVeicolo(v));
			
			if(postoOccupato.isPresent()) {
				bottoniVeicoli.get(posti.lastIndexOf(postoOccupato.get())).setBackground(Color.red);
				showMessageDialog(null,"Il veicolo e' ora parcheggiato!");
			}
		}catch(PostiFinitiException e) {
			showMessageDialog(null,"Attenzione! non è possibile parcheggiare, posti insufficienti");
		}catch(AltezzaMassimaConsentitaException e) {
			showMessageDialog(null, "Attenzione! Altezza non consentita, veicolo non parcheggiato");
		}
	}
}
