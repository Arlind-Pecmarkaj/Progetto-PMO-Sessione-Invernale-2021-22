# Progetto PMO - Sessione Invernale 2021-22
Repository del progetto di PMO della sessione invernale 2021/22
<br>
# *Studenti*
| MATRICOLA | NOME     | COGNOME   | NickName GitHub  |
|-----------|----------|-----------| -----------------|
| 305991    | Arlind   | Pecmarkaj | Arlind-Pecmarkaj |
| 307059    | Leonardo | Bigelli   | LeonardoBigelli  |
| 307283    | Martin   | Berardi   | martin-berardi   |
| 308193    | Bogdan   | Dragne    | DragneBogdan     |
| 305558    | Tommaso  | Petrelli  | petrello         |

# Specifica del progetto
Il progetto consiste nell'implementazione di un gestionale di parcheggi.
 - Ogni parcheggio dispone di posti per auto e per moto, con la possibilità di effettuare il noleggio di monopattini elettrici.
 - All'ingresso di ogni parcheggio è presente un sensore che rileva l'altezza di ogni auto, in modo tale da permettere
   l'ingresso al parcheggio solamente alle auto che non superano il limite massimo di altezza.
   In ogni posto è presente un sensore che, in base all'emissione dei gas di scarico, rileva il tipo di carburante utilizzato dall'auto.
   Nei parcheggi sotterranei non sarà possibile parcheggiare con macchine a GPL o a metano.

 - Ogni proprietario ha la possibilità di fare un abbonamento per il parcheggio.
   L'abbonamento è strettamente associato ad un solo veicolo.
 - Un utente, se dispone di abbonamento per il parcheggio, ha la possibilità di noleggiare un monopattino con una certa
   tariffa oraria. Si permette l'acquisto di un abbonamento premium che elimina la tariffa oraria di utilizzo del monopattino.
 - Se all'uscita dal parcheggio il veicolo non risulta associato a nessun abbonamento, verrà calcolato il costo di
   utilizzo del parcheggio secondo la tariffa oraria imposta.

 - I dati riguardanti il parcheggio vengono salvati in un file, che viene caricato durante l'apertura dell'applicazione
   e che viene aggiornato alla chiusura di quest'ultimo.
 - E' previsto l'utilizzo di un'interfaccia grafica (GUI).
