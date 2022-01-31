package rmii;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ObjetDepose extends UnicastRemoteObject implements IDeposeRemote {

    private List<Canette> canettes = new ArrayList<>();
    private int capacité = 50;

    public ObjetDepose() throws RemoteException {
        super();
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
}
