package Cau1Registry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RminClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try
        {
             RmiInterface c= (RmiInterface) Naming.lookup("//localhost/Cau1Registry");
             System.out.println( c.menu());
             int choice = sc.nextInt();
             int x,y;
             String a;
             switch (choice)
             {
                 case 1:
                     System.out.println("Nhap vao X , y");
                     System.out.println("Enter X");
                     x= sc.nextInt();
                     System.out.println("Enter Y");
                     y=sc.nextInt();
                     System.out.println(c.add(x,y));
                     break;
                 case 2:
                     System.out.println("Nhap vao X , y");
                     System.out.println("Enter X");
                     x= sc.nextInt();
                     System.out.println("Enter Y");
                     y=sc.nextInt();
                     System.out.println(c.sub(x,y));
                     break;

                 case 3:
                     System.out.println("Nhap vao X , y");
                     System.out.println("Enter X");
                     x= sc.nextInt();
                     System.out.println("Enter Y");
                     y=sc.nextInt();
                     System.out.println(c.mul(x,y));
                     break;
                 case 4:
                     System.out.println("Nhap vao X , y");
                     System.out.println("Enter X");
                     x= sc.nextInt();
                     System.out.println("Enter Y");
                     y=sc.nextInt();
                     System.out.println(c.div(x,y));
                     break;
                 case 5:
                     System.out.println("Nhap vao chuoi");
                     System.out.println("Enter X");
                     a=sc.nextLine();
                     System.out.println(c.Convert(a));
                     break;
             }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
