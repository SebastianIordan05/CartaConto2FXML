/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author seb
 */
public class Conto implements Serializable {

    private final String codiceIBAN;
    private final Date dataApertura;
    private final Intestatario intestatario;
    private final List<Movimento> movimenti;
    public int saldo = 0;
    private LocalDate date;

    public Conto(String codiceIBAN, Intestatario intestatario) {
        this.codiceIBAN = codiceIBAN;
        this.dataApertura = new Date();
        this.intestatario = intestatario;
        this.movimenti = new ArrayList<>();
    }

    public void registraOperazione(TipoMovimento tipoMovimento, double importo, String descrizione, LocalDate date) {
        int numeroProgressivo = movimenti.size() + 1;
        Movimento movimento = new Movimento(numeroProgressivo, date, codiceIBAN, tipoMovimento, importo,
                descrizione, tipoMovimento.getCosto());
        movimenti.add(movimento);
    }

    public double calcolaSaldo() throws Exception {
        double saldo = 0;
        for (Movimento movimento : movimenti) {
            switch (movimento.getTipoOperazione().getSegnoOperazione()) {
                case "+" -> saldo += movimento.getImporto();
                case "-" -> saldo -= movimento.getImporto();
                default -> throw new Exception("Type of operation not supported, supported operations are: +, -.");
            }
            saldo -= movimento.getCostoOperazione();
        }
        return saldo;
    }

    public List<Movimento> elencoOperazioni() {
        return movimenti;
    }

    public String getCodiceIBAN() {
        return codiceIBAN;
    }

    public Date getDataApertura() {
        return dataApertura;
    }

    @Override
    public String toString() {
        return "Conto{ " + "codiceIBAN = " + codiceIBAN + ", dataApertura = " 
                + dataApertura + ", intestatario = " + intestatario + ", movimenti = " 
                + movimenti + " }";
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }
    
}
