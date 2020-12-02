import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

interface IAccount extends Remote {
    public User getUser() throws RemoteException;
}
class AccountServiceImpl extends UnicastRemoteObject implements IAccount {
    private static final long serialVersionUID = 1L;

    public AccountServiceImpl() throws RemoteException {

    }

    public User getUser() {
        User u = new User();
        u.setName("Toan");
        return u;
    }
}

public class Server {

    public static void main(String[] args) {
        try {
            IAccount rAccount = new AccountServiceImpl();
            LocateRegistry.createRegistry(6789);
            // Đăng ký đối tượng này với rmiregistry
            Naming.bind("rmi://127.0.0.1:6789/SeptemberRMI", rAccount);
            System.out.println(">>>>>INFO: RMI Server started!!!!!!!!");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
