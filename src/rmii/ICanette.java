package rmii;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICanette extends Remote {

    void deposeCanette() throws RemoteException, InterruptedException;
    void retireCanette() throws RemoteException, InterruptedException;
}
