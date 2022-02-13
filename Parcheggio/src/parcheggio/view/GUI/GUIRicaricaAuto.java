package parcheggio.view.GUI;


import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import parcheggio.exceptions.IllegalChargerException;
import parcheggio.model.posto.PostoElettrico;

@SuppressWarnings("serial")
public class GUIRicaricaAuto extends JFrame {
	
	private final Container container;
	private final JLabel title;
	private final JPanel form;
	private final JLabel lbl_percDaRicaricare;
    private final JTextField tx_percDaRicaricare;
    private final JButton btn_ricarica;
    private final JLabel lbl_tempoRicarica;
    private final JLabel lbl_caricaAttuale;
    private final JLabel lbl_caricaCompleta;
    
    private final Font bodyFont = new Font("Arial", Font.PLAIN, 20);
	

	public GUIRicaricaAuto(PostoElettrico pElettrico) {
		
		setTitle("Supercharger - " + pElettrico.getColonnaSupercharger().getkWh() + "kWh");
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		this.container = getContentPane();
		this.container.setLayout(new FlowLayout());
		this.container.setBackground(Color.gray.brighter());
		
		this.title = new JLabel("Ricarica auto elettrica");
		this.title.setFont(new Font("Arial", Font.PLAIN, 30));
		this.title.setForeground(Color.white);
        this.title.setSize(300, 30);
        this.container.add(title);
        
        
        this.form = new JPanel(new GridLayout(6, 1));
        this.form.setBackground(Color.gray);
        this.container.add(form);
        
        
        this.lbl_tempoRicarica = new JLabel("Tempo di ricarica: " + pElettrico.getColonnaSupercharger().getTempoRicaricaHR());
        this.lbl_tempoRicarica.setFont(bodyFont);
        this.lbl_tempoRicarica.setForeground(Color.white);
        this.form.add(lbl_tempoRicarica);
        
        this.lbl_caricaAttuale = new JLabel("Carica attuale:" + (int) Math.ceil(pElettrico.getColonnaSupercharger().getPercentualeAttuale(pElettrico.getVeicolo().get())) + "%");
        this.lbl_caricaAttuale.setFont(bodyFont);
        this.lbl_caricaAttuale.setForeground(Color.white);
        this.form.add(lbl_caricaAttuale);
		
        this.lbl_percDaRicaricare = new JLabel("Inserisci percentuale da raggiungere: ");
        this.lbl_percDaRicaricare.setFont(bodyFont);
        this.lbl_percDaRicaricare.setForeground(Color.white);
        this.form.add(this.lbl_percDaRicaricare);
        
        this.tx_percDaRicaricare = new JTextField();
        this.tx_percDaRicaricare.setFont(bodyFont);
        this.form.add(this.tx_percDaRicaricare);
        
        
        this.btn_ricarica = new JButton("Avvia ricarica!");
        this.btn_ricarica.setFont(bodyFont);
        this.btn_ricarica.setBackground(Color.gray.darker());
        this.btn_ricarica.setForeground(Color.white);
        this.btn_ricarica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					pElettrico.getColonnaSupercharger().ricaricaVeicolo(Integer.parseInt(tx_percDaRicaricare.getText()), pElettrico.getVeicolo().get());
					
					lbl_tempoRicarica.setText("Tempo di ricarica: " + pElettrico.getColonnaSupercharger().getTempoRicaricaHR());
					
					lbl_caricaCompleta.setText("Carica completa");
					
					lbl_caricaAttuale.setText("Carica attuale:" + (int) Math.ceil(pElettrico.getColonnaSupercharger().getPercentualeAttuale(pElettrico.getVeicolo().get())) + "%");
					
				} catch(IllegalChargerException ex) {
					
					showMessageDialog(null, ex.getMessage() + " (" + tx_percDaRicaricare.getText() + "%)");
					
				}
				
			}
        	
        });
        this.form.add(this.btn_ricarica);
        
        
        this.lbl_caricaCompleta = new JLabel();
        this.lbl_caricaCompleta.setFont(bodyFont);
        this.lbl_caricaCompleta.setForeground(Color.white);
        this.form.add(lbl_caricaCompleta);
        
        
        setVisible(true);
        
	}
	
}
