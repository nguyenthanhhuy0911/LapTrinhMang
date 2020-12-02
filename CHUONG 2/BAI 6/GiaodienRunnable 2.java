class  GiaodienRunnable implements Runnable{
    String word;
    int delay;
    GiaodienRunnable(String s, int d){
        word =s;
        delay=d;
    }
    public void run(){
        try {
            for(int i=0; i<30; i++){
                System.out.println(word+" ");
                Thread.sleep(delay);
            }
        }
        catch (InterruptedException e){
            return;
        }
    }

    public static void main(String[] args) {
        GiaodienRunnable ping = new GiaodienRunnable("MOT",30);
        GiaodienRunnable pong = new GiaodienRunnable("HAI", 100);
        new Thread(ping).start();
        new Thread(pong).start();
    }
}