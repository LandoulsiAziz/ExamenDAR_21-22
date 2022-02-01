package rmii;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
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
    public static void main(String[] args) throws InterruptedException, RemoteException, MalformedURLException, NotBoundException {

        IRetireRemote distributeur = (IRetireRemote) Naming.lookup("rmi://services.isi.tn:2004/Retirer");

        for (int i = 0; i < 20; i++)
            new Employe(distributeur).start();

    }
}
