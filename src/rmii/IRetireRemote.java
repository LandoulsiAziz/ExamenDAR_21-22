package rmii;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRetireRemote extends Remote {

    void retireCanette() throws RemoteException, InterruptedException;

}
