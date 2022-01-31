package rmii;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) throws InterruptedException, RemoteException, MalformedURLException, NotBoundException {

        IDeposeRemote dep = (IDeposeRemote) Naming.lookup("rmi://services.isi.tn:2004/Depot");
        IRetireRemote ret = (IRetireRemote) Naming.lookup("rmi://services.isi.tn:2004/Retirer");

        Agent agent1 = new Agent(dep);
        Agent agent2 = new Agent(dep);

        for (int i = 0; i < 20; i++)
            new Employe(ret).start();

        agent1.start();
        agent2.start();

        agent1.join();
        agent2.join();
    }
}
