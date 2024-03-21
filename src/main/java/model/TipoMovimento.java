/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author seb
 */
public class TipoMovimento implements Serializable {
    private String codice;
    private String descrizione;
    private String segnoOperazione;
    private Date giorniValuta;
    private double costo;

    public String getCodice() {
        return codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getSegnoOperazione() {
        return segnoOperazione;
    }

    public Date getGiorniValuta() {
        return giorniValuta;
    }

    public double getCosto() {
        return costo;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setSegnoOperazione(String segnoOperazione) {
        this.segnoOperazione = segnoOperazione;
    }

    public void setGiorniValuta(Date giorniValuta) {
        this.giorniValuta = giorniValuta;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "TipoMovimento{" + "codice=" + codice + ", descrizione=" + descrizione + ", segnoOperazione=" + segnoOperazione + ", giorniValuta=" + giorniValuta + ", costo=" + costo + '}';
    }
}
