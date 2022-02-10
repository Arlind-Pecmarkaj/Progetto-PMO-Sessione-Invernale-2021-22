package parcheggio.view.GUI;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class GUIHelp extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIHelp window = new GUIHelp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIHelp() {
		super("Guida");
		getContentPane().setBackground(SystemColor.controlHighlight);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 1051, 599);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		getContentPane().setLayout(null);
		
		JTextPane txtpnGuidaAllavvioDel = new JTextPane();
		txtpnGuidaAllavvioDel.setBounds(10, 43, 1017, 125);
		txtpnGuidaAllavvioDel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnGuidaAllavvioDel.setBackground(SystemColor.controlHighlight);
		txtpnGuidaAllavvioDel.setText("All'avvio del programma viene mostrata la finestra principale, divisa in due sezioni:\r\n      \u2022 la prima mostra i parcheggi\r\n      \u2022 la seconda contiene i campi da inserire per poter aggiungere un nuovo abbonamento al parcheggio.\r\nCliccando su un parcheggio, comparir\u00E0 una finestra mostrante i posti disponibili (colorati in verde), i posti occupati (colorati in rosso), i monopattini disponibili e una sezione per inserire i dati di un nuovo veicolo.\r\nPer occupare un posto, inserire i dati del veicolo e cliccare su \"Inserisci\".\r\nPer liberare un posto, cliccare sul posto stesso.");
		getContentPane().add(txtpnGuidaAllavvioDel);
		
		JTextPane txtpnPostiDisponibiliPosti = new JTextPane();
		txtpnPostiDisponibiliPosti.setBounds(10, 178, 106, 54);
		txtpnPostiDisponibiliPosti.setBackground(SystemColor.controlHighlight);
		txtpnPostiDisponibiliPosti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnPostiDisponibiliPosti.setText("Posti disponibili:\r\n\r\nPosti occupati:");
		getContentPane().add(txtpnPostiDisponibiliPosti);
		
		JButton btnNewButton = new JButton("Posto Auto");
		btnNewButton.setBounds(126, 178, 144, 21);
		btnNewButton.setBackground(Color.GREEN);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Posto Auto");
		btnNewButton_1.setBounds(126, 209, 144, 21);
		btnNewButton_1.setBackground(Color.RED);
		getContentPane().add(btnNewButton_1);
		
		JTextPane txtpnGuida = new JTextPane();
		txtpnGuida.setBounds(10, 10, 80, 23);
		txtpnGuida.setBackground(SystemColor.controlHighlight);
		txtpnGuida.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnGuida.setText("Guida.");
		getContentPane().add(txtpnGuida);
		
		JTextPane txtpnCliccandoSulBottone = new JTextPane();
		txtpnCliccandoSulBottone.setBounds(10, 263, 128, 23);
		txtpnCliccandoSulBottone.setBackground(SystemColor.controlHighlight);
		txtpnCliccandoSulBottone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnCliccandoSulBottone.setText("Cliccando sul bottone");
		getContentPane().add(txtpnCliccandoSulBottone);
		
		JButton btnNewButton_2 = new JButton("Pulisci campi");
		btnNewButton_2.setBounds(148, 265, 85, 21);
		getContentPane().add(btnNewButton_2);
		
		JTextPane txtpnVieneCancellatoAutomaticamente = new JTextPane();
		txtpnVieneCancellatoAutomaticamente.setBounds(243, 263, 364, 23);
		txtpnVieneCancellatoAutomaticamente.setBackground(SystemColor.controlHighlight);
		txtpnVieneCancellatoAutomaticamente.setText("viene cancellato automaticamente il contenuto del campo.");
		txtpnVieneCancellatoAutomaticamente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(txtpnVieneCancellatoAutomaticamente);
		
		JTextPane txtpnNoleggioMonopattiniUn = new JTextPane();
		txtpnNoleggioMonopattiniUn.setBounds(10, 296, 159, 23);
		txtpnNoleggioMonopattiniUn.setBackground(SystemColor.controlHighlight);
		txtpnNoleggioMonopattiniUn.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtpnNoleggioMonopattiniUn.setText("Noleggio monopattini:\r\n");
		getContentPane().add(txtpnNoleggioMonopattiniUn);
		
		JTextPane txtpnUnMonopattinoPu = new JTextPane();
		txtpnUnMonopattinoPu.setBounds(10, 320, 1017, 70);
		txtpnUnMonopattinoPu.setBackground(SystemColor.controlHighlight);
		txtpnUnMonopattinoPu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnUnMonopattinoPu.setText("Un monopattino pu\u00F2 essere noleggiato solo se l'auto che si sta parcheggiando \u00E8 provvista di abbonamento premium.\r\nPer ottenere un abbonamento premium, spuntare la casella \"Premium\" al momento dell'aggiunta di un nuovo abbonamento.\r\nPer noleggiare un monopattino, spuntare la casella \"Noleggia monopattino\".");
		getContentPane().add(txtpnUnMonopattinoPu);
	}
}
