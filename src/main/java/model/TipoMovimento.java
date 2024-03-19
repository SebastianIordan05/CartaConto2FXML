/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author seb
 */
public class TipoMovimento {
    private int codice;
    private String descrizione;
    private String segnoOperazione;
    private Date giorniValuta;
    private double costo;
    
    public void setVersamento() {
        versamento();
    }
    
    private void versamento() {
        this.codice = 1;
        this.descrizione = "versamento";
        this.segnoOperazione = "+";
        this.giorniValuta = new Date();
        this.costo = 0;
    }
    
    public void setPrelievo() {
        prelievo();
    }
    
    private void prelievo() {
        this.codice = 2;
        this.descrizione = "prelievo";
        this.segnoOperazione = "-";
        this.giorniValuta = new Date();
        this.costo = 0;
    }
    
    public void setBonificoOrdinario() {
        bonificoOrdinario();
    }
    
    private void bonificoOrdinario() {
        this.codice = 2;
        this.descrizione = "bonifico ordinario";
        this.segnoOperazione = "+";
        this.giorniValuta = new Date();
        this.costo = 0;
    }
    
    public void setBonificoIstantaneo() {
        bonificoIstantaneo();
    }
    
    private void bonificoIstantaneo() {
        this.codice = 3;
        this.descrizione = "bonifico istantaneo";
        this.segnoOperazione = "+";
        this.giorniValuta = new Date();
        this.costo = 2.5;
    }

    public int getCodice() {
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
}
