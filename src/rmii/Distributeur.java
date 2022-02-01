package rmii;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Distributeur extends UnicastRemoteObject implements ICanette {

    private List<Canette> canettes = new ArrayList<>();
    private int capacité = 50;

    public Distributeur() throws RemoteException {
    }

    @Override
    public void deposeCanette() throws RemoteException,InterruptedException {
        while (true){
            synchronized (this) {
                while (canettes.size()==capacité)
                    wait();

                if(Agent.dispo) {
                    System.out.println(" Agent ajoute " + canettes.size() + " canettes ");
                    canettes.add(new Canette());
                }
                notify();
            }
        }
    }

    @Override
    public void retireCanette() throws RemoteException,InterruptedException {
        while (true){
            synchronized (this) {
                while (canettes.size()==0)
                    wait();

                System.out.println(" Employe retire une canette " + canettes.size());
                canettes.remove(0);

                notify();
            }
        }
    }

    public static void main(String[] args) {
        try{
            System.out.println("Serveur: Construction de l'implementation");

            Distributeur distributeur= new Distributeur();
            java.rmi.registry.LocateRegistry.createRegistry(2004);

            System.out.println("Objets liés dans RMIregistry");
            Naming.rebind("rmi://services.isi.tn:2004/Depot", distributeur);

            System.out.println("Attente des invocations des clients");



        }catch (MalformedURLException | RemoteException e) {
            System.out.println("Erreur d'accès a un objet distant");
            System.out.println(e.toString());
        }
    }
}
