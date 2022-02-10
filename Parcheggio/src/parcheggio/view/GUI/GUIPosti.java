package parcheggio.view.GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class GUIPosti extends JFrame {
	
	private final JLabel title;
	private final Container container;
	private final JPanel form;
	private final JTable table;
	 
	public GUIPosti() {
		setTitle("Info posti");
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		
		this.container = getContentPane();
		this.container.setLayout(new FlowLayout());
		
		this.title = new JLabel("Tabella riassuntiva");
		this.title.setFont(new Font("Arial", Font.PLAIN, 30));
        this.title.setSize(300, 20);
        this.container.add(title);
        
        
        this.form = new JPanel(new GridLayout(6, 1));
        this.container.add(form);
        
        setLayout(new FlowLayout());
        
        String[] column = {"Targa", "Nome", "Cognome", "Veicolo"};
        
        Object[][] data = {};
        
        table = new JTable(data,column);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);
        
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
        
        setVisible(true);
	}
	
	
}
