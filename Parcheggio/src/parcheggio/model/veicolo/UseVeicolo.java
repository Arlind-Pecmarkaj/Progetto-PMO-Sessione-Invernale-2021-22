package parcheggio.model.veicolo;

/**
 *
 * @author marti
 */
public class UseVeicolo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Auto a1 = new Auto("ABC23ABC", 2021, Alimentazione.GPL, "Toyota", "Yaris", "Martin", "Berardi", 1.5, 5);
        Moto m1 = new Moto("AAA999ZZZ", 1999, Alimentazione.BENZINA, "Honda", "SuHonda", "Radja", "Nainggolan", 300.0);
        System.out.println(a1);
        System.out.println("\n");
        System.out.println(m1);
        /*Auto a2 = new Auto("ABC23ABC", 2021, Alimentazione.GPL, "Toyota", "Yaris", "Martin", "Berardi", 1.5, 5);
        if(a1.equals(a2))
        	System.out.println("Ok");*/
    }
    
}
