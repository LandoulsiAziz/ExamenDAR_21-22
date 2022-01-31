package rmii;
import java.rmi.RemoteException;

public class Employe extends Thread {
    IRetireRemote distributeur;

    public Employe(IRetireRemote distributeur)
    {this.distributeur = distributeur;}

    @Override
    public void run() {
        try {
            distributeur.retireCanette();
        } catch (InterruptedException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
