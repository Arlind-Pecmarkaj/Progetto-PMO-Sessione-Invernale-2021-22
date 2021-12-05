/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcheggio;
import veicolo.*;

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
