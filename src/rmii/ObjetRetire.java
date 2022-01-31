package rmii;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ObjetRetire extends UnicastRemoteObject implements IRetireRemote {

    private List<Canette> canettes = new ArrayList<>();
    private int capacité = 50;

    public ObjetRetire() throws RemoteException {
        super();
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
}
