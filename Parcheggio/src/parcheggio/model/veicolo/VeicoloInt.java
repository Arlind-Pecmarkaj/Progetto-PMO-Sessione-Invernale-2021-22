package parcheggio.model.veicolo;
import parcheggio.enumerations.Alimentazione;

public interface VeicoloInt {
	String getTarga();
	int getAnnoImm();
	Alimentazione getCarburante();
	String getMarca();
	String getModello();
	String getNomeProprietario();
	String getCognomeProprietario();
	double getCapienzaMassima();
	double getCarburanteAttuale();
	
	void setTarga(String t);
	void setAnnoImm(int anno);
	void setCarburante(Alimentazione c);
	void setMarca(String marca);
	void setModello(String modello);
	void setNomeProprietario(String nome);
	void setCognomeProprietario(String cognome);
	void setCapienzaMassima(double capienzaMassima);
	void setCarburanteAttuale(double carburanteAttuale);
}
