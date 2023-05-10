package banca;

import java.io.Serializable;

/**
 *
 * @author bernardi.nicola
 */
public class Utente implements Serializable {
    private int numeroCC;
    private String nome;
    private double saldo;

    public Utente(int numeroCC, String nome, double saldo) {
        this.numeroCC = numeroCC;
        this.nome = nome;
        this.saldo = saldo;
    }
    
    /**
     * Prelievo di denaro
     * @param importo
     * @return -1: saldo insufficiente
     * @return -2: importo minore di 0
     * @return 0: operazione eseguita
     */
    public byte prelievo(double importo) {
        if(importo>saldo)
            return -1;
        if(importo <=0)
            return -2;
        saldo -= importo;
        return 0;
    }
    
    public byte deposito(double importo) {
        if(importo<=0)
            return -1;
        saldo += importo;
        return 0;
    }
    
    public double visualizzaSaldo() {
        return saldo;
    }

    public int getNumeroCC() {
        return numeroCC;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome + ", numero CC: " + numeroCC + ", saldo: " + saldo;
    }
}
