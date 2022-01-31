package rmii;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Distributeur {

    public static void main(String[] args) {
        try{
            System.out.println("Serveur: Construction de l'implementation");

            java.rmi.registry.LocateRegistry.createRegistry(2004);
            ObjetDepose dep= new ObjetDepose();
            System.out.println("ObjetDepose lié dans RMIregistry");
            Naming.rebind("rmi://services.isi.tn:2004/Depot", dep);

            ObjetRetire ret= new ObjetRetire();
            System.out.println("ObjetDepose lié dans RMIregistry");
            Naming.rebind("rmi://services.isi.tn:2004/Retirer", ret);

            System.out.println("Attente des invocations des clients");
        }catch (MalformedURLException | RemoteException e) {
            System.out.println("Erreur d'accès a un objet distant");
            System.out.println(e.toString());
        }
    }
}
