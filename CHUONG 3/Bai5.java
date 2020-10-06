import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

class DiaChi {
    private String name;
    private String ip;

    public DiaChi(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public String print() {
        return this.name + "  " + this.ip;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}

class TrinhDuyet {
    private final String denyFilename = "deny.txt";
    private ArrayList<DiaChi> denyList = new ArrayList<>();

    public TrinhDuyet() {
        this.read();
    }

    private void read() {
        try {
            Scanner scanner = new Scanner(new File(denyFilename));
            while (scanner.hasNextLine()) {
                String l = scanner.nextLine();
                if (!l.isEmpty()) {
                    String[] s = l.split(" ");
                    DiaChi d = new DiaChi(s[0], s[1]);
                    this.denyList.add(d);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("khong doc duoc file history");
        }
    }

//     public void write() {
//         try {
//             java.io.File output = new java.io.File(denyFilename);
//             FileWriter writer = new FileWriter(output);
//             if (this.denyList != null) {
//                 for (DiaChi d : this.denyList) {
//                     writer.append(d.print());
//                     writer.append("\n");
//                 }
//             }
//             writer.flush();
//             writer.close();
//         } catch (Exception e) {
//             System.out.println("Khong the write history vao file");
//             System.out.println(e.getLocalizedMessage());
//         }

//     }

    private String formatAddress(String s) {
        // vnexpress.net => http://vnexpress.net
        if (!s.startsWith("http://") && !s.startsWith("https://")) {
            return "http://" + s;
        }
        return s;
    }

    boolean isBlockedAddress(String hostname, String ip) {
        for (DiaChi d : denyList) {
            if (d.getIp().equals(ip) || d.getName().equals(hostname)) {
                return true;
            }
        }
        return false;
    }

    public void go(String address) {
        // kiem tra xem thu trong danh sach deny list neu ton tai thi thong bao
        try {
            URL url = new URL(this.formatAddress(address));
            String hostname = url.getHost();
            // lấy dia chi ip
            InetAddress add = InetAddress.getByName(hostname);
            String ip = add.getHostAddress();
//             System.out.println(ip);
            if (this.isBlockedAddress(hostname, ip)) {
                System.out.println("Địa chỉ trang web của bạn đã bị cấm!");
                return;
            }
            Scanner sc = new Scanner(url.openStream());
            StringBuffer sb = new StringBuffer();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            // hiển thị nội dung trang web
            System.out.println(sb.toString());

        } catch (Exception e) {
            System.out.println("Không thể truy cập được vào trang web này");
        }

    }
}

public class Bai5 {

    public static void main(String[] args) {
        System.out.print("Nhap dia chi: ");
        Scanner scanner = new Scanner(System.in);
        String nhap = scanner.nextLine();
        TrinhDuyet t = new TrinhDuyet();
        t.go(nhap);
    }
}
