package chuong_5_cau_2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RmiClient {

    public static void menu() {
        System.out.println("Chon server de ket noi");
        System.out.println("Chon a de ket noi vao server thu nhat");
        System.out.println("Chon b de ket noi vao server thu 2");

    }

    public static void main(String[] args) {
        String location;
        Scanner sc = new Scanner(System.in);
        // show menu
        while (true) {
            menu();
            String input = sc.nextLine();
            if (input.equals("a")) {
                location = "//localhost:1099/server-a";
                System.out.println("dang ket noi toi server thu nhat...");
                break;
            }
            if (input.equals("b")) {
                location = "//localhost:1098/server-b";
                System.out.println("dang ket noi toi server thu 2...");
                break;
            }
        }
        try {
            RmiInterface c = (RmiInterface) Naming.lookup(location);
            int x, y;
            String a;
            boolean runn = true;
            while (runn) {
                System.out.println(c.menu());
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
                    case 6:
                        System.out.println("Thoat!");
                        System.exit(0);
                        break;
                    default:
                        runn = false;
                }
            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println("Loi ket noi: " + e.getMessage());
        }
    }
}
