package cau1;

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
            RmiInterface c= (RmiInterface) Naming.lookup("//localhost/cau1");
            int x,y;
            String a;
            boolean runn = true;
            while (runn) {
                System.out.println( c.menu());
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Nhap vao x, y");
                        System.out.print("Nhap x: ");
                        x = sc.nextInt();
                        System.out.print("Nhap y: ");
                        y = sc.nextInt();
                        System.out.println("Tong: " + c.add(x, y));
                        break;
                    case 2:
                        System.out.println("Nhap vao x, y");
                        System.out.print("Nhap x: ");
                        x = sc.nextInt();
                        System.out.print("Nhap y: ");
                        y = sc.nextInt();
                        System.out.println("Hieu: " + c.sub(x, y));
                        break;

                    case 3:
                        System.out.println("Nhap vao x, y");
                        System.out.print("Nhap x: ");
                        x = sc.nextInt();
                        System.out.print("Nhap y: ");
                        y = sc.nextInt();
                        System.out.println("Tich: " + c.mul(x, y));
                        break;
                    case 4:
                        System.out.println("Nhap vao x, y");
                        System.out.print("Nhap x: ");
                        x = sc.nextInt();
                        System.out.print("Nhap y: ");
                        y = sc.nextInt();
                        System.out.println("Thuong: " + c.div(x, y));
                        break;
                    case 5:
                        System.out.print("Nhap vao chuoi: ");
                        Scanner scn = new Scanner(System.in);
                        String s = scn.nextLine();
                        System.out.println("Chuan hoa: " + c.Convert(s));
                        break;
                    default:
                        runn = false;
                }
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
