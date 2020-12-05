package chuong_5_cau_2;

import java.rmi.registry.Registry;

public class RmiServerB {
    public static void main(String[] args) {
        try {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1098);
            r.rebind("server-b", new RmiMethod());
            System.out.println("Server B running //localhost:1098/server-b");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }
}