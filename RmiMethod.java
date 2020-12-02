package Cau1Registry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiMethod extends UnicastRemoteObject implements  RmiInterface{

    public String menu()
    {
//        System.out.println("Menu");
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");

        return "Menu\n" +
                "1 -> Tong x , y\n" +
                "2 -> Hieu x , y\n" +
                "3 -> Tich x , y\n"+
                "4 -> Thuong x , y\n"+
                "5 ->Chuan Hoa Chuoi\n"
                ;

    }
    public  String Convert(String a)
    {
        String [] b=a.split("");
        String text="";
        text+=b[0].toUpperCase();
        for(int i=1;i<b.length;i++)
        {
            if(b[i].equals(" ")&&!b[i+1].equals(" "))
            {

                text += " "+b[i+1].toUpperCase();
                i++;
            }
            else
            {
                text+=b[i];
            }
        }
        return text;
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
    public int div(int a,int b) throws  RemoteException
    {
        return a/b;
    }


}
