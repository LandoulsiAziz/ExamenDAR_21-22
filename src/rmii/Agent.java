package rmii;

import java.rmi.RemoteException;

public class Agent extends Thread{

    public static boolean dispo;
    private IDeposeRemote distributeur;

    public Agent(IDeposeRemote distributeur) {
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
}
