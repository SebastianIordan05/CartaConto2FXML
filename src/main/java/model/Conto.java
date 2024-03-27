/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author seb
 */
public class Conto implements Serializable {
    
    private final String codiceIBAN;
    private final Date dataApertura;
    private final Intestatario intestatario;
    private final List<Movimento> movimenti;
    private LocalDate date;
    private double saldo;
    
//    public ArrayList<String> iCollegati = new ArrayList<>();
    
    public static final String FILE2_PATH = "./.conti";
    public static Map<String, Conto> conti = loadConti(new File(FILE2_PATH));

    public Conto(String codiceIBAN, Intestatario intestatario) {
        this.codiceIBAN = codiceIBAN;
        this.dataApertura = new Date();
        this.intestatario = intestatario;
        this.movimenti = new ArrayList<>();
    }
    
    private static Map<String, Conto> loadConti(final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
                return new HashMap<>();
            }
            
            if (!f.canRead()) {
                return new HashMap<>();
            }
            
            final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
            final Map<String, Conto> conto = (Map<String, Conto>) inputStream.readObject();
            
            return conto;

        } catch (final IOException | ClassNotFoundException ex) {
        }

        return new HashMap<>();
    }
    
    public static void saveConti(final Map<String, Conto> conti, final File f) {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            
            if (!f.canWrite()) {
                return;
            }
            
            final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(f));
            outputStream.writeObject(conti);
        } catch (final IOException ex) {
        }
    }

    public void registraOperazione(TipoMovimento tipoMovimento, double importo, String descrizione, LocalDate date) {
        int numeroProgressivo = movimenti.size() + 1;
        Movimento movimento = new Movimento(numeroProgressivo, date, codiceIBAN, tipoMovimento, importo,
                descrizione, tipoMovimento.getCosto());
        movimenti.add(movimento);
    }

    public double calcolaSaldo() {
        saldo = 0.0;
        for (Movimento movimento : movimenti) {
            switch (movimento.getTipoOperazione().getSegnoOperazione()) {
                case '+' -> {
                    saldo += movimento.getImporto();
                    saldo -= movimento.getCostoOperazione();
                }
                case '-' -> {
                    saldo -= movimento.getImporto();
                    saldo -= movimento.getCostoOperazione();
                }
                default -> throw new IllegalArgumentException("Type of operation not supported, supported operations are: +, -.");
            }
//            saldo -= movimento.getCostoOperazione();
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
}
