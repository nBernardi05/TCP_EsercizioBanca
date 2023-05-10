package banca;

import java.io.Serializable;

/**
 *
 * @author bernardi.nicola
 */
public class Richiesta implements Serializable {
    private byte tipoOperazione;
    private int nCC;
    private double importo;

    public Richiesta(byte tipoOperazione, int nCC, double importo) {
        this.tipoOperazione = tipoOperazione;
        this.nCC = nCC;
        this.importo = importo;
    }

    public byte getTipoOperazione() {
        return tipoOperazione;
    }

    public int getnCC() {
        return nCC;
    }

    public double getImporto() {
        return importo;
    }
    
    
}