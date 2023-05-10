package banca;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author bernardi.nicola
 */
public class ServerB {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket sSocket = new ServerSocket(50000);
        ArrayList<Utente> utenti = new ArrayList<>();
        utenti.add(new Utente(1126, "Rossi Mario", 120.5));
        utenti.add(new Utente(7473, "Gialli Rebecca", 71.1));
        utenti.add(new Utente(3413, "Fuscsi Luigi", 84));
        utenti.add(new Utente(7382, "Verdi Antonella", 8));

        while(true){
            Socket clientSocket = sSocket.accept();
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            
            
            Richiesta r = (Richiesta)ois.readObject();
            int nUtente = -1;
            
            for(int i=0; i<utenti.size(); i++){
                if(utenti.get(i).getNumeroCC()==r.getnCC())
                    nUtente = i;
            }
            if(nUtente==-1){
                oos.writeObject(new Utente((byte)-1, "", 0));
                oos.flush();
                continue;
            }
            switch (r.getTipoOperazione()) {
                case 1:
                    utenti.get(nUtente).deposito(r.getImporto());
                    break;
                case 2:
                    utenti.get(nUtente).prelievo(r.getImporto());
                    break;
                case 3:
                    break;
            }
            oos.writeObject(utenti.get(nUtente));
            oos.flush();
        }
    }
}
