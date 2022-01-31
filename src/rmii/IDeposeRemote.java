package rmii;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDeposeRemote extends Remote {

    void deposeCanette() throws RemoteException, InterruptedException;

}
