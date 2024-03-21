/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author seb
 */
public class Movimento implements Serializable {

    private final int numeroProgressivo;
    private final LocalDate dataOperazione;
    private final Date dataValuta;
    private final String codiceIBAN;
    private final TipoMovimento tipoOperazione;
    private final double importo;
    private final String descrizione;
    private final double costoOperazione;

    public Movimento(int numeroProgressivo, LocalDate dataOperazione, String codiceIBAN, TipoMovimento tipoOperazione,
            double importo, String descrizione, double costoOperazione) {
        this.numeroProgressivo = numeroProgressivo;
        this.dataOperazione = dataOperazione;
        this.dataValuta = new Date();  // default: data = data operazione
        this.codiceIBAN = codiceIBAN;
        this.tipoOperazione = tipoOperazione;
        this.importo = importo;
        this.descrizione = descrizione;
        this.costoOperazione = costoOperazione;
    }

    public int getNumeroProgressivo() {
        return numeroProgressivo;
    }

    public LocalDate getDataOperazione() {
        return dataOperazione;
    }

    public Date getDataValuta() {
        return dataValuta;
    }

    public String getCodiceIBAN() {
        return codiceIBAN;
    }

    public TipoMovimento getTipoOperazione() {
        return tipoOperazione;
    }

    public double getImporto() {
        return importo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getCostoOperazione() {
        return costoOperazione;
    }
}
