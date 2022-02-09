package parcheggio.view.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import static javax.swing.JOptionPane.showMessageDialog;

//import parcheggio.enumerations.Alimentazione;
import parcheggio.model.veicolo.*;
import parcheggio.enumerations.Alimentazione;
import parcheggio.exceptions.AltezzaMassimaConsentitaException;
import parcheggio.exceptions.AutoMetanoNonAmmessaException;
import parcheggio.exceptions.MonopattiniEsauritiException;
import parcheggio.exceptions.PersonaSenzaAbbonamentoException;
import parcheggio.exceptions.PostiFinitiException;
import parcheggio.exceptions.TargaNonPresenteException;
import parcheggio.exceptions.TargheUgualiException;
import parcheggio.model.Parcheggio;
import parcheggio.model.ParcheggioImpl;
import parcheggio.model.monopattino.Monopattino;
import parcheggio.model.persona.Persona;
import parcheggio.model.posto.Posto;
import parcheggio.model.posto.PostoAuto;
import parcheggio.model.posto.PostoElettrico;
import parcheggio.model.posto.PostoMoto;

@SuppressWarnings("serial")
public class GUIParcheggio extends JFrame{

	private JPanel contentPane;      // Panel principale del frame
	private JPanel panelParcheggi;   // Panel in cui son visibili i parchegi
	private JPanel panelMonopattini; // Panel in cui son visibili i monopattini
	private JPanel panelInserimento; // Panel in cui � visibile il form di inserimento di un veicolo
	
	/* Label associate al gruppo di Field/Box qui sotto e aiutano nell'identificazione. */
	private JLabel lblPanelInserimento;
	private JLabel lblTarga;
	private JLabel lblCodiceFiscale;
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblTipoVeicolo;
	private JLabel lblCarburante;
	
	private JTextField   targa;
	private JTextField   codiceFiscale;
	private JTextField   nome;
	private JTextField   cognome;
	private JButton      in;	
	private String[]     veicoliDisponibili = { "Auto", "Moto" };
	private JComboBox<?> listaVeicoli = new JComboBox<Object>(veicoliDisponibili);
	private JComboBox<?> listaAlimentazioni;
	private JCheckBox    noleggiaMonopattino;
	private JButton		 pulisci;
	private JButton		 infoPosto;
	
	private ArrayList<Posto>   posti;
	private ArrayList<JButton> bottoniVeicoli = new ArrayList<JButton>();
	private ArrayList<JButton> bottoniVeicoliElettrici = new ArrayList<JButton>();
	private ArrayList<JButton> bottoniMonopattini = new ArrayList<JButton>();

	/*
	 * costruzione del frame
	 */
	public GUIParcheggio(Parcheggio p) {
		super("Parcheggio: " + ((ParcheggioImpl)p).getName());
		
		((ParcheggioImpl) p).getAbbonamenti();
		
		int numeroPosti = p.getNPostiSpecifici(po -> po instanceof PostoAuto) +
						  p.getNPostiSpecifici(po -> po instanceof PostoMoto) +
						  p.getNPostiSpecifici(po -> po instanceof PostoElettrico);
		
		
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/*private JPanel panelInfo;		 // Panel per vedere le info dei posti  
		 * ------------------------------------ POSTI -------------------------- */
		
		posti = ((ParcheggioImpl) p).getPostiDisponibili();
		
		this.panelParcheggi = new JPanel();	
		this.panelParcheggi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (int i = 0; i < numeroPosti; i++) {
			JButton jb = new JButton();		
			if (posti.get(i) instanceof PostoAuto) {
				jb.setText("Posto Auto");
			} else if (posti.get(i) instanceof PostoElettrico) {
				jb.setText("Posto Elettrica");
			} else {
				jb.setText("Posto moto");
			}
			
			if (posti.get(i).isLibero())
				jb.setBackground(Color.green);
			else 
				jb.setBackground(Color.red);
			
			this.bottoniVeicoli.add(jb);
			
			/*********************************************************************/
		
			/* ottengo i bottoni per i posti dei veicoli elettrici */
			this.bottoniVeicoliElettrici = (ArrayList<JButton>) this.bottoniVeicoli.stream().filter(b -> b.getText().equals("Posto Elettrica")).collect(Collectors.toList());
			
		}

		for (JButton jb: this.bottoniVeicoli) {	
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!posti.get(bottoniVeicoli.lastIndexOf(jb)).isLibero()) {
						int risposta = -1;
						if(bottoniVeicoliElettrici.contains(jb)) {
							/* se il posto � occupato posso ricaricare l'auto */
							String[] opzioni = {"Ricarica auto", "Lascia parcheggio"};
							risposta = JOptionPane.showOptionDialog(null, 
																	"Che operazione ti interessa effettuare?",
																	"Operazioni disponibili", 
																	JOptionPane.DEFAULT_OPTION, 
																	JOptionPane.QUESTION_MESSAGE, 
																	null, 
																	opzioni, 
																	null);
							if(risposta == 0) {
								/* sceglo di ricaricare auto */
								new GUIRicaricaAuto((PostoElettrico) posti.get(bottoniVeicoli.lastIndexOf(jb)));
//								JFrame ricaricaAutoForm = new JFrame("Ricarica Auto Elettrica");
//								ricaricaAutoForm.setVisible(true);
//								ricaricaAutoForm.add(new JLabel("Ricarica auto"));
								
							}
						}
						
						if(!(risposta == 0 && bottoniVeicoliElettrici.contains(jb))) {
							double prezzoDaPagare = p.liberaPosto(posti.get(bottoniVeicoli.lastIndexOf(jb)));
							jb.setBackground(Color.green);
							showMessageDialog(null,"Posto liberato con successo!\n Costo: " + 
											  prezzoDaPagare + " euro");	
						}
					}
					else {
						showMessageDialog(null,"Il posto auto e' gia' libero!");
					}
				}
			});
			
		    this.panelParcheggi.add(jb);
		}
		
		/* ---------------------- MONOPATTINI -------------------------- */
		
		this.panelMonopattini = new JPanel();
		this.panelMonopattini.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel labelMonopattini = new JLabel("Monopattini disponibili");
		this.panelMonopattini.add(labelMonopattini);
		
		for (int i = 0; i < ((ParcheggioImpl)p).getPostiMonopattino().size(); i++) {
			JButton jb = new JButton("M" + i);
			
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!((ParcheggioImpl) p).getPostiMonopattino().get(bottoniMonopattini.lastIndexOf(jb)).getDisponibile()) {
						double costo = p.restituisciMonopattino(((ParcheggioImpl) p).getPostiMonopattino().get(bottoniMonopattini.lastIndexOf(jb)).getPersona(),
									   ((ParcheggioImpl) p).getPostiMonopattino().get(bottoniMonopattini.lastIndexOf(jb)));
						jb.setBackground(Color.green);
						
						showMessageDialog(null, "Monopattino restituito. \n" + 
										 "Prezzo da pagare: " + costo);
					} else {
						showMessageDialog(null, "Il monopattino e' gia' disponibile per il noleggio.");
					}
				}
				
			});
			this.panelMonopattini.add(jb);
			this.bottoniMonopattini.add(jb);

			if(((ParcheggioImpl) p).getPostiMonopattino().get(bottoniMonopattini.lastIndexOf(jb)).getDisponibile()) {
				jb.setBackground(Color.green);
			} else {
				jb.setBackground(Color.red);
			}
		}
	
		/* ------------------------------------------ INSERIMENTO ----------------------------- */
		panelInserimento = new JPanel();
		GridBagLayout gblPanelInserimento = new GridBagLayout();
		gblPanelInserimento.columnWidths  = new int[]{0, 0, 0};
		gblPanelInserimento.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gblPanelInserimento.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gblPanelInserimento.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInserimento.setLayout(gblPanelInserimento);
		
		
		lblPanelInserimento = new JLabel("Inserimento veicolo");
		GridBagConstraints gbcPanelInserimento = new GridBagConstraints();
		gbcPanelInserimento.insets             = new Insets(0, 0, 5, 0);
		gbcPanelInserimento.gridx              = 1;
		gbcPanelInserimento.gridy              = 0;
		panelInserimento.add(lblPanelInserimento, gbcPanelInserimento);
		
		lblTarga = new JLabel("Targa");
		GridBagConstraints gbcLblTarga = new GridBagConstraints();
		gbcLblTarga.anchor             = GridBagConstraints.EAST;
		gbcLblTarga.insets             = new Insets(0, 0, 5, 5);
		gbcLblTarga.gridx              = 0;
		gbcLblTarga.gridy              = 1;
		panelInserimento.add(lblTarga, gbcLblTarga);
		
		targa = new JTextField();
		GridBagConstraints gbcTarga = new GridBagConstraints();
		gbcTarga.insets             = new Insets(0, 0, 5, 0);
		gbcTarga.fill               = GridBagConstraints.HORIZONTAL;
		gbcTarga.gridx              = 1;
		gbcTarga.gridy              = 1;
		panelInserimento.add(targa, gbcTarga);
		targa.setColumns(10);
		
		lblCodiceFiscale = new JLabel("Codice Fiscale");
		GridBagConstraints gbcLblCodiceFiscale = new GridBagConstraints();
		gbcLblCodiceFiscale.anchor             = GridBagConstraints.EAST;
		gbcLblCodiceFiscale.insets             = new Insets(0, 0, 5, 5);
		gbcLblCodiceFiscale.gridx              = 0;
		gbcLblCodiceFiscale.gridy              = 2;
		panelInserimento.add(lblCodiceFiscale, gbcLblCodiceFiscale);
		
		codiceFiscale = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets             = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill               = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx              = 1;
		gbc_textField_2.gridy              = 2;
		panelInserimento.add(codiceFiscale, gbc_textField_2);
		codiceFiscale.setColumns(10);
		
		lblNome = new JLabel("Nome");
		GridBagConstraints gbcLblNome = new GridBagConstraints();
		gbcLblNome.insets             = new Insets(0, 0, 5, 5);
		gbcLblNome.anchor             = GridBagConstraints.EAST;
		gbcLblNome.gridx              = 0;
		gbcLblNome.gridy              = 3;
		panelInserimento.add(lblNome, gbcLblNome);
		
		nome = new JTextField();
		GridBagConstraints gbcNome = new GridBagConstraints();
		gbcNome.insets             = new Insets(0, 0, 5, 0);
		gbcNome.fill               = GridBagConstraints.HORIZONTAL;
		gbcNome.gridx              = 1;
		gbcNome.gridy              = 3;
		panelInserimento.add(nome, gbcNome);
		nome.setColumns(10);
		
		
		lblCognome = new JLabel("Cognome");
		GridBagConstraints gbcLblCognome = new GridBagConstraints();
		gbcLblCognome.anchor             = GridBagConstraints.EAST;
		gbcLblCognome.insets             = new Insets(0, 0, 5, 5);
		gbcLblCognome.gridx              = 0;
		gbcLblCognome.gridy              = 4;
		panelInserimento.add(lblCognome, gbcLblCognome);
		
		cognome = new JTextField();
		GridBagConstraints gbcCognome = new GridBagConstraints();
		gbcCognome.insets             = new Insets(0, 0, 5, 0);
		gbcCognome.fill               = GridBagConstraints.HORIZONTAL;
		gbcCognome.gridx              = 1;
		gbcCognome.gridy              = 4;
		panelInserimento.add(cognome, gbcCognome);
		cognome.setColumns(10);
		
		lblTipoVeicolo = new JLabel("Tipo veicolo");
		GridBagConstraints gbcLblTipoVeicolo = new GridBagConstraints();
		gbcLblTipoVeicolo.anchor             = GridBagConstraints.NORTHEAST;
		gbcLblTipoVeicolo.insets             = new Insets(0, 0, 5, 5);
		gbcLblTipoVeicolo.gridx              = 0;
		gbcLblTipoVeicolo.gridy              = 5;
		panelInserimento.add(lblTipoVeicolo, gbcLblTipoVeicolo);
		
		listaVeicoli = new JComboBox<>(this.veicoliDisponibili);
		GridBagConstraints gbcListaVeicoli = new GridBagConstraints();
		gbcListaVeicoli.insets             = new Insets(0, 0, 5, 0);
		gbcListaVeicoli.fill               = GridBagConstraints.HORIZONTAL;
		gbcListaVeicoli.gridx              = 1;
		gbcListaVeicoli.gridy              = 5;
		panelInserimento.add(listaVeicoli, gbcListaVeicoli);
		
		lblCarburante = new JLabel("Carburante");
		GridBagConstraints gbcLabelCarburante = new GridBagConstraints();
		gbcLabelCarburante.anchor             = GridBagConstraints.NORTHEAST;
		gbcLabelCarburante.insets             = new Insets(0, 0, 5, 5);
		gbcLabelCarburante.gridx              = 0;
		gbcLabelCarburante.gridy              = 6;
		panelInserimento.add(lblCarburante, gbcLabelCarburante);
		
		listaAlimentazioni = new JComboBox<>(Alimentazione.values());
		GridBagConstraints gbcListaAlimentazioni = new GridBagConstraints();
		gbcListaAlimentazioni.insets             = new Insets(0, 0, 5, 0);
		gbcListaAlimentazioni.fill               = GridBagConstraints.HORIZONTAL;
		gbcListaAlimentazioni.gridx              = 1;
		gbcListaAlimentazioni.gridy              = 6;
		panelInserimento.add(listaAlimentazioni, gbcListaAlimentazioni);
		
		
		noleggiaMonopattino = new JCheckBox("Noleggia monopattino");
		GridBagConstraints gbcNoleggiaMonopattino = new GridBagConstraints();
		gbcNoleggiaMonopattino.insets = new Insets(0, 0, 5, 0);
		gbcNoleggiaMonopattino.gridx = 1;
		gbcNoleggiaMonopattino.gridy = 7;
		panelInserimento.add(noleggiaMonopattino, gbcNoleggiaMonopattino);
		
		in = new JButton("Inserisci");
		GridBagConstraints gbcIn = new GridBagConstraints();
		gbcIn.gridx              = 1;
		gbcIn.gridy              = 8;
		panelInserimento.add(in, gbcIn);
		
		/*
		 * implementazione dell'inserimento di un veicolo all'interno del parcheggio 
		 */
		this.in.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				boolean esito = false;
				double capacitaSerbatoio = Math.round((200 + (300 - 200) * r.nextDouble())*100.0)/100.0;
				if(GUIParcheggio.this.listaVeicoli.getSelectedItem().equals("Auto")) {
					Veicolo v = new Auto(targa.getText(),
			   				   	r.nextInt(2022 - 1980) + 1980,
			   				   	(Alimentazione) listaAlimentazioni.getSelectedItem(),
			   				   	"a",
			   				   	"b",
			   				   	nome.getText(),
			   				   	cognome.getText(),
			   				   	100 + (500 - 100) * r.nextDouble(),
			   				   	capacitaSerbatoio,
			   				   	Math.round((0 + (capacitaSerbatoio - 0) * r.nextDouble())*100.0)/100.0);
					
					esito = GUIParcheggio.this.aggiornamento(v, p);
					
				} else if(GUIParcheggio.this.listaVeicoli.getSelectedItem().equals("Moto")) {
					Veicolo v = new Moto(targa.getText(),
								r.nextInt(2022 - 1980) + 1980,
								(Alimentazione) listaAlimentazioni.getSelectedItem(),
								"a",
								"b",
								nome.getText(),
								cognome.getText(),
								capacitaSerbatoio,
								Math.round((0 + (capacitaSerbatoio - 0) * r.nextDouble())*100.0)/100.0);
					
					esito = GUIParcheggio.this.aggiornamento(v, p);
					
				}
				if(noleggiaMonopattino.isSelected() && esito) {
					// generazione di una data casuale
					long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
					long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
					long tmpDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
					LocalDate tmpDate = LocalDate.ofEpochDay(tmpDay);
					try {
						Monopattino m = p.noleggiaMonopattino(new Persona(codiceFiscale.getText(),
														  			      nome.getText(),
														  			      cognome.getText(),
														  			      tmpDate,
														  			      "Italia"
														  	  ));
						GUIParcheggio.this.bottoniMonopattini.get(((ParcheggioImpl) p).getPostiMonopattino()
															 .indexOf(m))
															 .setBackground(Color.red);
						
					} catch (MonopattiniEsauritiException m) {
						showMessageDialog(null, "Attenzione! Monopattini esauriti");
					} catch (PersonaSenzaAbbonamentoException m) {
						showMessageDialog(null, "Attenzione! L'utente e' sprovvisto di abbonamento. " +
									      "Monopattino non noleggiato");
					} catch(IllegalArgumentException m) {
						showMessageDialog(null, "Attenzione! I campi per l'utente non sono completi.");
					}
				}
			}
		});
		
		
		pulisci = new JButton("Pulisci campi");
		GridBagConstraints gbcPulisci = new GridBagConstraints();
		gbcPulisci.gridx			  = 2;
		gbcPulisci.gridy			  = 8;
		panelInserimento.add(pulisci, gbcPulisci);
		
		infoPosto = new JButton("Info posti");
		GridBagConstraints gbcInfo = new GridBagConstraints();
		gbcInfo.gridx			  = 0;
		gbcInfo.gridy			  = 8;
		panelInserimento.add(infoPosto, gbcInfo);
		
		pulisci.addActionListener(new ActionListener(){  
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int sicuro = -1;
				String[] opzioni = {"No", "Sì"};
				sicuro = JOptionPane.showOptionDialog(null, 
										     "Sei sicuro di volere pulire i campi?",
											 " ", 
											  JOptionPane.DEFAULT_OPTION, 
											  JOptionPane.QUESTION_MESSAGE, 
											  null, 
											  opzioni, 
											  null);
				
				if(sicuro != 0) {
					targa.setText("");
					codiceFiscale.setText("");
					nome.setText("");
					cognome.setText("");
				}
					
			}  
		});
		
		// infoPosto.addActionListener(new ActionListener() {
			
			
		// });
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelParcheggi, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelInserimento, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelMonopattini, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelParcheggi, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(panelMonopattini, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelInserimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	private boolean aggiornamento(Veicolo v, Parcheggio p) {
		boolean esito = false;
		try {
			Optional<Posto> postoOccupato = Optional.of(p.aggiungiVeicolo(v));
			if(postoOccupato.isPresent()) {
				bottoniVeicoli.get(posti.lastIndexOf(postoOccupato.get())).setBackground(Color.red);
				showMessageDialog(null,"Il veicolo e' ora parcheggiato!");
				esito = true;
			}
		}catch(PostiFinitiException e) {
			showMessageDialog(null,"Attenzione! non e' possibile parcheggiare, posti insufficienti");
		}catch(AltezzaMassimaConsentitaException e) {
			showMessageDialog(null, "Attenzione! Altezza non consentita, veicolo non parcheggiato");
		}catch(AutoMetanoNonAmmessaException e) {
			showMessageDialog(null, "Attenzione! Le auto a metano non sono ammesse in un parcheggio sotterranneo");
		}catch(TargheUgualiException e) {
			showMessageDialog(null, "Attenzione! Un veicolo con la stessa targa e' gia' parcheggio");
		}catch(TargaNonPresenteException e) {
			showMessageDialog(null, "Attenzione! Non e' stata inserita la targa");
		}
		return esito;
		
	}
}
