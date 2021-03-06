package parcheggio.model.veicolo;
import parcheggio.enumerations.Alimentazione;

public abstract class Veicolo implements VeicoloInt{
    /*--- Dichiarazione attributi ---*/
    private String targa;               //stringa alfanumerica identificativa del veicolo
    private int annoImm;                //anno di immatricolazione del veicolo
    private Alimentazione carburante;   //tipo di carburante (enum)
    private String marca;               //marca del veicolo
    private String modello;             //modello del veicolo
    private String nomeProprietario;    //nome del proprietario del veicolo
    private String cognomeProprietario; //cognome del proprietario del veicolo
    private double capienzaMassima;		//capienza massima del serbatoio
    private double carburanteAttuale;	//quantita' di carburante al momento
    
    /*--- Costruttore ---*/
    public Veicolo(String targa, int annoImm, Alimentazione carburante,
                    String marca, String modello, String nomeProprietario,
                    String cognomeProprietario, double capienzaMassima,
                    double carburanteAttuale){
        super();
        this.targa = targa;
        this.annoImm = annoImm;
        this.carburante = carburante;
        this.marca = marca;
        this.modello = modello;
        this.nomeProprietario = nomeProprietario;
        this.cognomeProprietario = cognomeProprietario;
        this.capienzaMassima = capienzaMassima;
        this.carburanteAttuale = carburanteAttuale;
    }
    
    /*--- Metodi get ---*/
    public String getTarga(){
        return this.targa;
    }
    
    public int getAnnoImm(){
        return this.annoImm;
    }
    
    public Alimentazione getCarburante(){
        return this.carburante;
    }
    
    public String getMarca(){
        return this.marca;
    }
    
    public String getModello(){
        return this.modello;
    }
    
    public String getNomeProprietario(){
        return this.nomeProprietario;
    }
    
    public String getCognomeProprietario(){
        return this.cognomeProprietario;
    }
    
    public double getCapienzaMassima() {
    	return this.capienzaMassima;
    }
    
    public double getCarburanteAttuale() {
    	return this.carburanteAttuale;
    }
    
    
    /*--- Metodi set ---*/
    public void setTarga(String t){
        this.targa = t;
    }
    
    public void setAnnoImm(int anno){
        this.annoImm = anno;
    }
    
    public void setCarburante(Alimentazione c){
        this.carburante = c;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public void setModello(String modello){
        this.modello = modello;
    }
    
    public void setNomeProprietario(String nome){
        this.nomeProprietario = nome;
    }
    
    public void setCognomeProprietario(String cognome){
        this.cognomeProprietario = cognome;
    }
    
    public void setCapienzaMassima(double capienzaMassima) {
    	this.capienzaMassima = capienzaMassima;
    }
    
    public void setCarburanteAttuale(double carburanteAttuale) {
    	this.carburanteAttuale = carburanteAttuale;
    }
    

	/**
     *
     * @return
     */
    @Override
    public String toString(){
        return "Targa: " + this.targa
        		+ "\nAnno di Immatricolazione: " + this.annoImm
                + "\nCarburante: " + this.carburante
                + "\nMarca: " + this.marca
                + "\nModello: " + this.modello
                + "\nNome proprietario: " + this.nomeProprietario
                + "\nCognome proprietario: " + this.cognomeProprietario
                + "\nCapienza massima serbatoio: " + this.capienzaMassima
                + "\nQuantita' di carburante presente al momento: " + this.carburanteAttuale;
    }
}

