/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author seb
 */
public class Intestatario {

    private final String codiceFiscale;
    private final String cognome;
    private final String nome;
    private final String luogoNascita;
    private Date dataNascita;
    private String dataDiNascita2;
    private String indirizzo;
    private String cap;
    private String citta;
    private String provincia;
    private final String cellulare;
    private final String email;
    
    //test
    private static Map<String, Intestatario> users;
    
    public Intestatario(String codiceFiscale, String cognome, String nome, String luogoNascita, Date dataNascita,
            String indirizzo, String cap, String citta, String provincia, String cellulare, String email) {
        this.codiceFiscale = codiceFiscale;
        this.cognome = cognome;
        this.nome = nome;
        this.luogoNascita = luogoNascita;
        this.dataNascita = dataNascita;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.citta = citta;
        this.provincia = provincia;
        this.cellulare = cellulare;
        this.email = email;
    }
    
//    public Intestatario() {
//        this.codiceFiscale = "RDNSST05H24L736E";
//        this.cognome = "iordan";
//        this.nome = "sebastian";
//        this.luogoNascita = "venezia";
//        this.dataDiNascita2 = "24/06/2005";
//        this.indirizzo = "via alcide de gasperi 23/3";
//        this.cap = "30030";
//        this.citta = "salzano";
//        this.provincia = "venezia";
//        this.cellulare = "3338782703";
//        this.email = "sebastian.iordan@edu.iisleviponti.it";
//    }

    public void modificaDatiResidenza(String nuovoIndirizzo, String nuovoCap, String nuovaCitta, String nuovaProvincia) {
        this.indirizzo = nuovoIndirizzo;
        this.cap = nuovoCap;
        this.citta = nuovaCitta;
        this.provincia = nuovaProvincia;
    }
    
    @Override
    public String toString() {
        return "Intestatario{ " + "codiceFiscale = " + codiceFiscale + ", cognome = "
                + cognome + ", nome = " + nome + ", luogoNascita = "
                + luogoNascita + ", dataNascita = " + dataDiNascita2 + ", indirizzo = "
                + indirizzo + ", cap = " + cap + ", citta = " + citta + ", provincia = "
                + provincia + ", cellulare = " + cellulare + ", email = " + email + " }";
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCellulare() {
        return cellulare;
    }

    public String getEmail() {
        return email;
    }  
}
