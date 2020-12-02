package cau1;

import java.rmi.Remote;
import java.rmi.registry.Registry;

public class RmiServer {
    public static void main(String[] args) {
        try
        {
            Registry r= java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("cau1",new RmiMethod()  );

            System.out.println("server are connected");


        }catch (Exception e)
        {
            System.out.println("server not Connected "+ e);
        }

    }
}