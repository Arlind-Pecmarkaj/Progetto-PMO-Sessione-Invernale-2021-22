package parcheggio;

import parcheggio.model.*;
import java.time.LocalDateTime;

/**
 *
 * @author leonardo bigelli
 */
public abstract class Posto {
    private String id;
    private Veicolo veicoloOccupante;
    private LocalDateTime oraUscita;
    private LocalDateTime oraArrivo;
}
