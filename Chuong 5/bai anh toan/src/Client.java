import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            //Xác định RMI máy chủ.
            IAccount iAccount = (IAccount) Naming.lookup("rmi://127.0.0.1:6789/SeptemberRMI");
            System.out.println("Name: " + iAccount.getUser().getName());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
