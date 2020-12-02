package cau1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote {

    public int add(int x,int y) throws RemoteException;
    public int sub(int x,int y) throws RemoteException;
    public int mul(int x,int y) throws RemoteException;
    public double div(int x, int y) throws RemoteException;
    public String Convert(String x) throws RemoteException;
    public String menu() throws  RemoteException;
}