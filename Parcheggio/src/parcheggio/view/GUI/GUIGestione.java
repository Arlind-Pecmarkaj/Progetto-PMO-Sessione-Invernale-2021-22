package parcheggio.view.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import static javax.swing.JOptionPane.showMessageDialog;

@SuppressWarnings("serial")
public class GUIGestione extends JFrame {

	private JPanel           contentPane;
	private JTextField     codiceFiscale;
	private JTextField              nome;
	private JTextField           cognome;
	private JTextField       dataNascita;
	private JTextField             targa;

	/**
	 * Costruisco il frame
	 */
	public GUIGestione(GestioneParcheggio g, ReaderWriterFromFile r) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 470); /* la finestra sara' di 810x470 px con il vertice superiore sinistro nella posizione (100,100). */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); /* margini della finestra. */
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Seleziona parcheggio");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_parcheggi = new JPanel(); /* Panel per la visualizzazione dei parcheggi */
		GridBagConstraints gbc_panel_parcheggi = new GridBagConstraints();
		gbc_panel_parcheggi.insets = new Insets(0, 0, 5, 0);
		gbc_panel_parcheggi.fill = GridBagConstraints.BOTH;
		gbc_panel_parcheggi.gridx = 0;
		gbc_panel_parcheggi.gridy = 1;
		contentPane.add(panel_parcheggi, gbc_panel_parcheggi);
		panel_parcheggi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); //Per i parcheggi si usa un flow layout.	
		
		for (ParcheggioImpl p : g.getParcheggi()) {
			JButton bottone_parcheggio = new JButton(p.getName());
			bottone_parcheggio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showMessageDialog(null, p.toString());
				}
			});
			panel_parcheggi.add(bottone_parcheggio);
		}
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		contentPane.add(separator, gbc_separator);
		
		JLabel lblNewLabel_1 = new JLabel("Inserimento abbonamento");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel panel_abbonamenti = new JPanel(); /* Panel per gli abbonamenti */
		GridBagConstraints gbc_panel_abbonamenti = new GridBagConstraints();
		gbc_panel_abbonamenti.fill = GridBagConstraints.BOTH;
		gbc_panel_abbonamenti.gridx = 0;
		gbc_panel_abbonamenti.gridy = 5;
		contentPane.add(panel_abbonamenti, gbc_panel_abbonamenti);
		GridBagLayout gbl_panel_abbonamenti = new GridBagLayout();
		gbl_panel_abbonamenti.columnWidths = new int[]{0, 0, 0};
		gbl_panel_abbonamenti.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_abbonamenti.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_abbonamenti.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_abbonamenti.setLayout(gbl_panel_abbonamenti);
		
		JLabel label_cf = new JLabel("Codice fiscale");
		GridBagConstraints gbc_label_cf = new GridBagConstraints();
		gbc_label_cf.anchor = GridBagConstraints.EAST;
		gbc_label_cf.insets = new Insets(0, 0, 5, 5);
		gbc_label_cf.gridx = 0;
		gbc_label_cf.gridy = 0;
		panel_abbonamenti.add(label_cf, gbc_label_cf);
		
		codiceFiscale = new JTextField();
		GridBagConstraints gbc_codiceFiscale = new GridBagConstraints();
		gbc_codiceFiscale.fill = GridBagConstraints.HORIZONTAL;
		gbc_codiceFiscale.insets = new Insets(0, 0, 5, 0);
		gbc_codiceFiscale.gridx = 1;
		gbc_codiceFiscale.gridy = 0;
		panel_abbonamenti.add(codiceFiscale, gbc_codiceFiscale);
		codiceFiscale.setColumns(10);
		
		JLabel label_nome = new JLabel("Nome");
		GridBagConstraints gbc_label_nome = new GridBagConstraints();
		gbc_label_nome.anchor = GridBagConstraints.EAST;
		gbc_label_nome.insets = new Insets(0, 0, 5, 5);
		gbc_label_nome.gridx = 0;
		gbc_label_nome.gridy = 1;
		panel_abbonamenti.add(label_nome, gbc_label_nome);
		
		nome = new JTextField();
		GridBagConstraints gbc_nome = new GridBagConstraints();
		gbc_nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_nome.anchor = GridBagConstraints.NORTH;
		gbc_nome.insets = new Insets(0, 0, 5, 0);
		gbc_nome.gridx = 1;
		gbc_nome.gridy = 1;
		panel_abbonamenti.add(nome, gbc_nome);
		nome.setColumns(10);
		
		JLabel label_cognome = new JLabel("Cognome");
		GridBagConstraints gbc_label_cognome = new GridBagConstraints();
		gbc_label_cognome.anchor = GridBagConstraints.EAST;
		gbc_label_cognome.insets = new Insets(0, 0, 5, 5);
		gbc_label_cognome.gridx = 0;
		gbc_label_cognome.gridy = 2;
		panel_abbonamenti.add(label_cognome, gbc_label_cognome);
		
		cognome = new JTextField();
		GridBagConstraints gbc_cognome = new GridBagConstraints();
		gbc_cognome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cognome.insets = new Insets(0, 0, 5, 0);
		gbc_cognome.gridx = 1;
		gbc_cognome.gridy = 2;
		panel_abbonamenti.add(cognome, gbc_cognome);
		cognome.setColumns(10);
		
		JLabel label_nascita = new JLabel("Data di nascita");
		GridBagConstraints gbc_label_nascita = new GridBagConstraints();
		gbc_label_nascita.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_nascita.insets = new Insets(0, 0, 5, 5);
		gbc_label_nascita.gridx = 0;
		gbc_label_nascita.gridy = 3;
		panel_abbonamenti.add(label_nascita, gbc_label_nascita);
		
		dataNascita = new JTextField();
		GridBagConstraints gbc_dataNascita = new GridBagConstraints();
		gbc_dataNascita.fill = GridBagConstraints.HORIZONTAL;
		gbc_dataNascita.insets = new Insets(0, 0, 5, 0);
		gbc_dataNascita.gridx = 1;
		gbc_dataNascita.gridy = 3;
		panel_abbonamenti.add(dataNascita, gbc_dataNascita);
		dataNascita.setColumns(10);
		
		JLabel label_targa = new JLabel("Targa");
		GridBagConstraints gbc_label_targa = new GridBagConstraints();
		gbc_label_targa.anchor = GridBagConstraints.EAST;
		gbc_label_targa.insets = new Insets(0, 0, 5, 5);
		gbc_label_targa.gridx = 0;
		gbc_label_targa.gridy = 4;
		panel_abbonamenti.add(label_targa, gbc_label_targa);
		
		targa = new JTextField();
		GridBagConstraints gbc_targa = new GridBagConstraints();
		gbc_targa.fill = GridBagConstraints.HORIZONTAL;
		gbc_targa.insets = new Insets(0, 0, 5, 0);
		gbc_targa.gridx = 1;
		gbc_targa.gridy = 4;
		panel_abbonamenti.add(targa, gbc_targa);
		targa.setColumns(10);
		
		JLabel label_durata = new JLabel("Durata");
		GridBagConstraints gbc_label_durata = new GridBagConstraints();
		gbc_label_durata.anchor = GridBagConstraints.EAST;
		gbc_label_durata.insets = new Insets(0, 0, 5, 5);
		gbc_label_durata.gridx = 0;
		gbc_label_durata.gridy = 5;
		panel_abbonamenti.add(label_durata, gbc_label_durata);
		
		String[] opzioni = {"mensile", "trimestrale", "semestrale", "annuale"};
		JComboBox<String> durata = new JComboBox<>(opzioni);
		GridBagConstraints gbc_durata = new GridBagConstraints();
		gbc_durata.insets = new Insets(0, 0, 5, 0);
		gbc_durata.fill = GridBagConstraints.HORIZONTAL;
		gbc_durata.gridx = 1;
		gbc_durata.gridy = 5;
		panel_abbonamenti.add(durata, gbc_durata);
		
		JCheckBox premium = new JCheckBox("Premium");
		GridBagConstraints gbc_premium = new GridBagConstraints();
		gbc_premium.fill = GridBagConstraints.HORIZONTAL;
		gbc_premium.insets = new Insets(0, 0, 5, 5);
		gbc_premium.gridx = 0;
		gbc_premium.gridy = 6;
		panel_abbonamenti.add(premium, gbc_premium);
		
		JButton inserimento = new JButton("Inserisci abbonamento");
		/* Cliccando su inserisci abbonamento viene inserito l'abbonamento in Gestione e viene aggiornata la lista di tutti gli abbonamenti. */
		inserimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cf = codiceFiscale.getText();
					String nm = nome.getText();
					String cn = cognome.getText();
					String tr = targa.getText();
					String dr = durata.getSelectedItem().toString();
					LocalDate na = LocalDate.parse(dataNascita.getText());
					LocalDate end = null;
					if (dr.equals("mensile")) {
						end = LocalDate.now().plusMonths(1);
					} else if (dr.equals("trimestrale")) {
						end = LocalDate.now().plusMonths(3);
					} else if (dr.equals("semestrale")) {
						end = LocalDate.now().plusMonths(6);
					} else if (dr.equals("annuale")) {
						end = LocalDate.now().plusMonths(12);
					}
					Persona p = new Persona(cf, nm, cn, LocalDate.now(), "prova");					
					Abbonamento a = new Abbonamento(p.hashCode(), tr, p, na, end, premium.isSelected());
					g.aggiungiAbbonamento(a);
					g.aggiornaAbbonamenti();
					showMessageDialog(null, "Aggiunto abbonamento: \n" + a);
				} catch (Exception ex) {
					showMessageDialog(null, "ATTENZIONE: campi non compilati correttamente!");
				}
				
			}
		});
		
		GridBagConstraints gbc_inserimento = new GridBagConstraints();
		gbc_inserimento.gridx = 1;
		gbc_inserimento.gridy = 7;
		panel_abbonamenti.add(inserimento, gbc_inserimento);
		
		/* Alla chiusura del frame dovr� scrivere i dati */
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	r.write(g);
		    }
		});
		
		this.setVisible(true);
	}
}
