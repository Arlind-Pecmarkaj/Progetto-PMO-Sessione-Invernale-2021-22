package parcheggio.view.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import parcheggio.controller.ReaderWriterFromFile;
import parcheggio.model.GestioneParcheggio;
import parcheggio.model.ParcheggioImpl;
import parcheggio.model.abbonamento.Abbonamento;
import parcheggio.model.persona.Persona;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import static javax.swing.JOptionPane.showMessageDialog;

@SuppressWarnings("serial")
public class GUIGestione extends JFrame {

	private JPanel       contentPane;
	private JPanel panel_abbonamenti;
	private JPanel   panel_parcheggi;
	private JTextField codiceFiscale;
	private JTextField          nome;
	private JTextField       cognome;
	private JTextField   dataNascita;
	private JTextField   nazionalita;
	private JTextField         targa;


	/**
	 * Costruisco il frame
	 */
	public GUIGestione(GestioneParcheggio g, ReaderWriterFromFile r) {
		super("Gestionale parcheggi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 470); /* la finestra sara' di 810x470 px con il vertice superiore sinistro nella posizione (100,100). */
		// JPanel principale che farà da contenitore per i due panel presenti sotto.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); /* margini della finestra. */
		contentPane.setBackground(Color.gray.brighter());
		setContentPane(contentPane); // Metto nel frame il nosto panel principale.
		GridBagLayout gbl_contentPane = new GridBagLayout(); // Layout del panel principale. Creato dal plugin WindowsBuilder
		gbl_contentPane.columnWidths  = new int[]{0, 0};
		gbl_contentPane.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights    = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Label per indicare visivamente la sezione della finestra. In questo caso alla selezione del parcheggio.
		JLabel lblSelezParcheggio = new JLabel("Seleziona parcheggio");
		GridBagConstraints gbcLblSelezParcheggio = new GridBagConstraints();
		gbcLblSelezParcheggio.insets             = new Insets(0, 0, 5, 0);
		gbcLblSelezParcheggio.gridx              = 0;
		gbcLblSelezParcheggio.gridy = 0;
		contentPane.add(lblSelezParcheggio, gbcLblSelezParcheggio);
		
		// Creo il panel in cui verranno inseriti i bottoni che permettono di accedere ai parcheggi.
		panel_parcheggi = new JPanel();
		panel_parcheggi.setOpaque(true);
		panel_parcheggi.setBackground(Color.gray);
		GridBagConstraints gbc_panel_parcheggi = new GridBagConstraints();
		gbc_panel_parcheggi.insets             = new Insets(0, 0, 5, 0);
		gbc_panel_parcheggi.fill               = GridBagConstraints.BOTH;
		gbc_panel_parcheggi.gridx              = 0;
		gbc_panel_parcheggi.gridy              = 1;
		contentPane.add(panel_parcheggi, gbc_panel_parcheggi);
		panel_parcheggi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); //Per i parcheggi si usa un flow layout.
		
		g.aggiornaAbbonamenti();
		for (ParcheggioImpl p : g.getParcheggi()) {
			JButton bottone_parcheggio = new JButton(p.getName());
			bottone_parcheggio.setBackground(Color.gray.darker());
			bottone_parcheggio.setForeground(Color.white);
			bottone_parcheggio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new GUIParcheggio(p); // Cliccando il bottone si crea una nuova istanza di GUIParcheggio.
				}
			});
			panel_parcheggi.add(bottone_parcheggio);
		}
		
		// Semplice separatore per dividere la sezione dei parcheggi con quella di inserimento degli abbonamenti.
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets             = new Insets(0, 0, 5, 0);
		gbc_separator.gridx              = 0;
		gbc_separator.gridy              = 2;
		contentPane.add(separator, gbc_separator);
		
		// Label per indicare visivamente la sezione degli abbonamenti.
		JLabel lblInserimentoAbbonamento = new JLabel("Inserimento abbonamento");
		GridBagConstraints gbcLblInsAbbonamento = new GridBagConstraints();
		gbcLblInsAbbonamento.insets             = new Insets(0, 0, 5, 0);
		gbcLblInsAbbonamento.gridx              = 0;
		gbcLblInsAbbonamento.gridy              = 4;
		contentPane.add(lblInserimentoAbbonamento, gbcLblInsAbbonamento);
		
		// Panel degli abbonamenti
		panel_abbonamenti = new JPanel();
		panel_abbonamenti.setBackground(Color.gray);
		GridBagConstraints gbc_panel_abbonamenti = new GridBagConstraints();
		gbc_panel_abbonamenti.fill               = GridBagConstraints.BOTH;
		gbc_panel_abbonamenti.gridx              = 0;
		gbc_panel_abbonamenti.gridy              = 5;
		contentPane.add(panel_abbonamenti, gbc_panel_abbonamenti);
		
		// Layout degli abbonamenti tramite GridBagConstraint
		GridBagLayout gbl_panel_abbonamenti = new GridBagLayout();
		gbl_panel_abbonamenti.columnWidths  = new int[]{0, 0, 0};
		gbl_panel_abbonamenti.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_abbonamenti.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_abbonamenti.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_abbonamenti.setLayout(gbl_panel_abbonamenti);
		
		// Da qui si susseguono label e nome dei vari campi necessari per creare un abbonamento.
		JLabel label_cf = new JLabel("Codice fiscale");
		label_cf.setForeground(Color.white);
		GridBagConstraints gbc_label_cf = new GridBagConstraints();
		gbc_label_cf.anchor             = GridBagConstraints.EAST;
		gbc_label_cf.insets             = new Insets(0, 0, 5, 5);
		gbc_label_cf.gridx              = 0;
		gbc_label_cf.gridy              = 0;
		panel_abbonamenti.add(label_cf, gbc_label_cf);
		
		codiceFiscale = new JTextField();
		GridBagConstraints gbc_codiceFiscale = new GridBagConstraints();
		gbc_codiceFiscale.fill               = GridBagConstraints.HORIZONTAL;
		gbc_codiceFiscale.insets             = new Insets(0, 0, 5, 0);
		gbc_codiceFiscale.gridx              = 1;
		gbc_codiceFiscale.gridy              = 0;
		panel_abbonamenti.add(codiceFiscale, gbc_codiceFiscale);
		codiceFiscale.setColumns(10);
		
		JLabel label_nome = new JLabel("Nome");
		label_nome.setForeground(Color.white);
		GridBagConstraints gbc_label_nome = new GridBagConstraints();
		gbc_label_nome.anchor             = GridBagConstraints.EAST;
		gbc_label_nome.insets             = new Insets(0, 0, 5, 5);
		gbc_label_nome.gridx              = 0;
		gbc_label_nome.gridy              = 1;
		panel_abbonamenti.add(label_nome, gbc_label_nome);
		
		nome = new JTextField();
		GridBagConstraints gbc_nome = new GridBagConstraints();
		gbc_nome.fill               = GridBagConstraints.HORIZONTAL;
		gbc_nome.anchor             = GridBagConstraints.NORTH;
		gbc_nome.insets             = new Insets(0, 0, 5, 0);
		gbc_nome.gridx              = 1;
		gbc_nome.gridy              = 1;
		panel_abbonamenti.add(nome, gbc_nome);
		nome.setColumns(10);
		
		JLabel label_cognome = new JLabel("Cognome");
		label_cognome.setForeground(Color.white);
		GridBagConstraints gbc_label_cognome = new GridBagConstraints();
		gbc_label_cognome.anchor             = GridBagConstraints.EAST;
		gbc_label_cognome.insets             = new Insets(0, 0, 5, 5);
		gbc_label_cognome.gridx              = 0;
		gbc_label_cognome.gridy              = 2;
		panel_abbonamenti.add(label_cognome, gbc_label_cognome);
		
		cognome = new JTextField();
		GridBagConstraints gbc_cognome = new GridBagConstraints();
		gbc_cognome.fill               = GridBagConstraints.HORIZONTAL;
		gbc_cognome.insets             = new Insets(0, 0, 5, 0);
		gbc_cognome.gridx              = 1;
		gbc_cognome.gridy              = 2;
		panel_abbonamenti.add(cognome, gbc_cognome);
		cognome.setColumns(10);
		
		JLabel label_nascita = new JLabel("Data di nascita");
		label_nascita.setForeground(Color.white);
		GridBagConstraints gbc_label_nascita = new GridBagConstraints();
		gbc_label_nascita.anchor             = GridBagConstraints.NORTHEAST;
		gbc_label_nascita.insets             = new Insets(0, 0, 5, 5);
		gbc_label_nascita.gridx              = 0;
		gbc_label_nascita.gridy              = 3;
		panel_abbonamenti.add(label_nascita, gbc_label_nascita);
		
		dataNascita = new JTextField();
		GridBagConstraints gbc_dataNascita = new GridBagConstraints();
		gbc_dataNascita.fill               = GridBagConstraints.HORIZONTAL;
		gbc_dataNascita.insets             = new Insets(0, 0, 5, 0);
		gbc_dataNascita.gridx              = 1;
		gbc_dataNascita.gridy              = 3;
		panel_abbonamenti.add(dataNascita, gbc_dataNascita);
		dataNascita.setColumns(10);
		
		JLabel label_nazionalita = new JLabel("Nazionalita'");
		label_nazionalita.setForeground(Color.white);
		GridBagConstraints gbc_label_nazionalita = new GridBagConstraints();
		gbc_label_nazionalita.anchor             = GridBagConstraints.EAST;
		gbc_label_nazionalita.insets             = new Insets(0, 0, 5, 5);
		gbc_label_nazionalita.gridx              = 0;
		gbc_label_nazionalita.gridy              = 4;
		panel_abbonamenti.add(label_nazionalita, gbc_label_nazionalita);
		
		nazionalita = new JTextField();
		GridBagConstraints gbc_nazionalita = new GridBagConstraints();
		gbc_nazionalita.insets             = new Insets(0, 0, 5, 0);
		gbc_nazionalita.fill               = GridBagConstraints.HORIZONTAL;
		gbc_nazionalita.gridx              = 1;
		gbc_nazionalita.gridy              = 4;
		panel_abbonamenti.add(nazionalita, gbc_nazionalita);
		nazionalita.setColumns(10);
		
		JLabel label_targa = new JLabel("Targa");
		label_targa.setForeground(Color.white);
		GridBagConstraints gbc_label_targa = new GridBagConstraints();
		gbc_label_targa.anchor             = GridBagConstraints.EAST;
		gbc_label_targa.insets             = new Insets(0, 0, 5, 5);
		gbc_label_targa.gridx              = 0;
		gbc_label_targa.gridy              = 5;
		panel_abbonamenti.add(label_targa, gbc_label_targa);
		
		targa = new JTextField();
		GridBagConstraints gbc_targa = new GridBagConstraints();
		gbc_targa.fill               = GridBagConstraints.HORIZONTAL;
		gbc_targa.insets             = new Insets(0, 0, 5, 0);
		gbc_targa.gridx              = 1;
		gbc_targa.gridy              = 5;
		panel_abbonamenti.add(targa, gbc_targa);
		targa.setColumns(10);
		
		JLabel label_durata = new JLabel("Durata");
		label_durata.setForeground(Color.white);
		GridBagConstraints gbc_label_durata = new GridBagConstraints();
		gbc_label_durata.anchor             = GridBagConstraints.EAST;
		gbc_label_durata.insets             = new Insets(0, 0, 5, 5);
		gbc_label_durata.gridx              = 0;
		gbc_label_durata.gridy              = 6;
		panel_abbonamenti.add(label_durata, gbc_label_durata);

		
		String[] opzioni = {"mensile", "trimestrale", "semestrale", "annuale"};
		JComboBox<String> durata = new JComboBox<>(opzioni);
		GridBagConstraints gbc_durata = new GridBagConstraints();
		gbc_durata.insets             = new Insets(0, 0, 5, 0);
		gbc_durata.fill               = GridBagConstraints.HORIZONTAL;
		gbc_durata.gridx              = 1;
		gbc_durata.gridy              = 6;
		panel_abbonamenti.add(durata, gbc_durata);
		
		JCheckBox premium = new JCheckBox("Premium");
		premium.setBackground(Color.gray);
		premium.setForeground(Color.white);
		GridBagConstraints gbc_premium = new GridBagConstraints();
		gbc_premium.fill               = GridBagConstraints.HORIZONTAL;
		gbc_premium.insets             = new Insets(0, 0, 5, 0);
		gbc_premium.gridx              = 1;
		gbc_premium.gridy              = 7;
		panel_abbonamenti.add(premium, gbc_premium);
		
		// Bottone di inserimento.
		JButton inserimento = new JButton("Inserisci abbonamento");
		inserimento.setBackground(Color.gray.darker());
		inserimento.setForeground(Color.white);
		/* Cliccando su inserisci abbonamento viene inserito l'abbonamento in Gestione e viene aggiornata la lista di tutti gli abbonamenti. */
		inserimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/* Recupero le informazioni dai miei field */
					String cf = codiceFiscale.getText();
					String nm = nome.getText();
					String cn = cognome.getText();
					String nz = nazionalita.getText();
					String tr = targa.getText();
					String dr = durata.getSelectedItem().toString();
					LocalDate na = LocalDate.parse(dataNascita.getText());
					LocalDate end = null; // In questo caso è ammissibile poichè ad end viene comunque assegnato un valore.
					if (dr.equals("mensile")) {
						end = LocalDate.now().plusMonths(1);
					} else if (dr.equals("trimestrale")) {
						end = LocalDate.now().plusMonths(3);
					} else if (dr.equals("semestrale")) {
						end = LocalDate.now().plusMonths(6);
					} else if (dr.equals("annuale")) {
						end = LocalDate.now().plusMonths(12);
					}
					Persona p = new Persona(cf, nm, cn, na, nz);			
					/* Per identificare un abbonamento si usa l'hashcode di una persona e della targa in modo tale da ottenere
					 * valori univoci */
					Abbonamento a = new Abbonamento(p.hashCode() + targa.hashCode(), tr, p, LocalDate.now(), end, premium.isSelected());
					g.aggiungiAbbonamento(a);
					g.aggiornaAbbonamenti(); // Aggiorno gli abbonamenti.
					showMessageDialog(null, "Aggiunto abbonamento con successo: \n" + a, "Inserimento abbonamento", JOptionPane.DEFAULT_OPTION);
				} catch (IllegalArgumentException il) { // Eccezione lanciata in caso i campi fossero vuoti.
					showMessageDialog(null, "ATTENZIONE: campi non compilati correttamente:\n" + il.getMessage(), 
									  "Errore", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) { // L'unica eccezione base viene lanciata dal ParseDate
					showMessageDialog(null, "ATTENZIONE: campo data di nascita non compilato correttamente!\n Il formato è AAAA-MM-GG!", 
							          "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		GridBagConstraints gbc_inserimento = new GridBagConstraints();
		gbc_inserimento.gridx              = 2;
		gbc_inserimento.gridy              = 7;
		panel_abbonamenti.add(inserimento, gbc_inserimento);
		
		/* Alla chiusura del frame dovrò scrivere i dati */
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	r.write(g);
		    }
		});
		
		this.setVisible(true);
	}
}
