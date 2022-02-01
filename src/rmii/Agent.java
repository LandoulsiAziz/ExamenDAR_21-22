package rmii;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Agent extends Thread{

    public static boolean dispo;
    private ICanette distributeur;

    public Agent(ICanette distributeur) {
        this.distributeur = distributeur;
    }

    @Override
    public void run() {
        try {
            distributeur.deposeCanette();
        } catch (InterruptedException | RemoteException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException, RemoteException, MalformedURLException, NotBoundException {

        ICanette distributeur = (ICanette) Naming.lookup("rmi://services.isi.tn:2004/Depot");

        Agent agent1 = new Agent(distributeur);
        Agent agent2 = new Agent(distributeur);

        agent1.start();
        agent2.start();

        agent1.join();
        agent2.join();
    }
}
