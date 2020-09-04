package com.company;

import java.io.IOException;
public class NgattuyenDoan extends Thread {
    public void run(){
        System.out.println("Tôi đang cảm thấy buồn  ngủ.Đánh thức tôi sau tám giờ");
        try {
            Thread.sleep( 1000*60 );
            System.out.println("Đó là một giấc ngủ ngắn");
        }
        catch (InterruptedException ie){
            System.out.println("Chỉ năm phút nữa thôi ...");
        }
    }

    public static void main(String[] args) throws IOException {
        Thread sleepy = new NgattuyenDoan();
        sleepy.start();
        System.out.println("Nhấn enter để ngắt luồng");
        System.in.read();
        sleepy.interrupt();
    }
}
