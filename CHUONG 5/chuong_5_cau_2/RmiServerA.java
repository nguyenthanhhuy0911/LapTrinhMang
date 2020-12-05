package chuong_5_cau_2;

import java.rmi.registry.Registry;

public class RmiServerA {
    public static void main(String[] args) {
        try
        {
            Registry r= java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("server-a",new RmiMethod()  );
            System.out.println("Server A running //localhost:1099/server-a");


        }catch (Exception e)
        {
            System.out.println("server not Connected "+ e);
        }

    }
}