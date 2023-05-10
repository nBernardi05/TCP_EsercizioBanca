package banca;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author bernardi.nicola
 */
public class ClientB {
    public static void main(String[] args) throws IOException, UnknownHostException, ClassNotFoundException {
        ClientB client = new ClientB();
        client.esegui();
    }
    
    public void esegui() throws UnknownHostException, IOException, ClassNotFoundException{
        Socket cSocket = new Socket(InetAddress.getLocalHost(), 50000);
        Scanner sc = new Scanner(System.in);
        ObjectOutputStream oos = new ObjectOutputStream(cSocket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(cSocket.getInputStream());
        
        System.out.println("Inserisci il numero del CC");
        int nCC = sc.nextInt();
        byte tO = 0;
        while(tO<1||tO>3){
            System.out.println("TIPO OPERAZIONE:");
            System.out.println("1 - Deposito sul conto corrente;");
            System.out.println("2 - Prelievo sul conto corrente;");
            System.out.println("3 - Visualizza saldo conto corrente;");
            tO = sc.nextByte();
        }
        double importo = 0;
        if(tO==1||tO==2){
            System.out.println("Inserire l'importo da prelevare/depositare:");
            importo = sc.nextDouble();
        }
        oos.writeObject(new Richiesta(tO, nCC, importo));
        oos.flush();
        
        Utente u = (Utente)ois.readObject();
        if(u.getNumeroCC()==-1)
            System.out.println("Non è stato trovato alcun conto corrente con il seguente codice: " + nCC);
        else System.out.println(u);
    }
}
