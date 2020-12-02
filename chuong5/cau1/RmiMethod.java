package cau1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiMethod extends UnicastRemoteObject implements  RmiInterface{

    public String menu()
    {
        return "Menu\n" +
                "1 -> Tong x, y\n" +
                "2 -> Hieu x, y\n" +
                "3 -> Tich x, y\n"+
                "4 -> Thuong x, y\n"+
                "5 -> Chuan hoa chuoi\n"
                ;

    }
    public  String Convert(String s)
    {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        String st[] = s.split(" ");
        s="";
        for (int i = 0; i < st.length; i++) {
            s += String.valueOf(st[i].charAt(0)).toUpperCase();
            for (int j = 1; j < st[i].length(); j++)
                s += String.valueOf(st[i].charAt(j)).toLowerCase();
            if (i < st.length - 1) s += " ";
        }
        return s;
    }
    public RmiMethod() throws RemoteException
    {
        int a,b;
    }
    public int add(int a,int b) throws  RemoteException
    {
        return a+b;
    }


    public int sub(int a, int b) throws  RemoteException
    {
        return a-b;
    }
    public int mul(int a,int b) throws  RemoteException
    {
        return a*b;
    }
    public double div(int a, int b) throws  RemoteException
    {
        return (double)a / (double)b;
    }


}