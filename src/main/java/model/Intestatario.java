/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author seb
 */
public class Intestatario implements Serializable {

    private final String codiceFiscale;
    private final String cognome;
    private final String nome;
    private final String luogoNascita;
    private final LocalDate dataNascita;
    private final String indirizzo;
    private final String cap;
    private final String citta;
    private final String provincia;
    private final String cellulare;
    private final String email;
    private String password;
    
    public Intestatario(String codiceFiscale, String cognome, String nome, String luogoNascita, LocalDate dataNascita,
            String indirizzo, String cap, String citta, String provincia, String cellulare, String email, String password) {
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
        this.password = password;
    }

    @Override
    public String toString() {
        return "Intestatario{" + "codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", nome=" + nome +
                ", luogoNascita=" + luogoNascita + ", dataNascita=" + dataNascita + ", indirizzo=" + indirizzo +
                ", cap=" + cap + ", citta=" + citta + ", provincia=" + provincia + ", cellulare=" + cellulare +
                ", email=" + email + ", password=" + password + '}';
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

    public LocalDate getDataNascita() {
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

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
