import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainPage();
    }
    public static void MainPage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------------");
        System.out.println("                  Welcome To Nmap                  ");
        System.out.println("---------------------------------------------------");
        System.out.println("    1 - Check Host Status                          ");
        System.out.println("    2 - Check Port Status of a Host                ");
        System.out.println("    3 - Get & Post                                 ");
        System.out.println("---------------------------------------------------");
        System.out.print("Choose a number : ");
        int inputNumMainPage = scanner.nextInt();

        switch (inputNumMainPage) {
            case 1:

                break;
            case 2:
                checkinhPorys();
                break;
            case 3:

                break;
            default:
                MainPage();
        }
    }
    private static String getServiceName(int port) { // تابع برای گرفتن نام سرویس بر اساس شماره پورت
        switch (port) {
            case 20:
                return "FTP Data Transfer";
            case 21:
                return "FTP Control";
            case 22:
                return "SSH";
            case 23:
                return "Telnet";
            case 25:
                return "SMTP";
            case 53:
                return "DNS";
            case 80:
                return "HTTP";
            case 443:
                return "HTTPS";
            default:
                return "Unknown Service";
        }
    }
    private static void checkinhPorys(){
        Scanner scanner = new Scanner(System.in);
        // دریافت هاست و رنج پورت‌ها از کاربر
        System.out.print("Enter the host/IP address: ");
        String host = scanner.nextLine();
        System.out.print("Enter the starting port: ");
        int startPort = scanner.nextInt();
        System.out.print("Enter the ending port: ");
        int endPort = scanner.nextInt();

        System.out.println("Scanning ports...");

        // اسکن پورت‌ها
        for (int port = startPort; port <= endPort; port++) {
            try {
                // ایجاد یک سوکت با تاخیر زمانی 100 میلی‌ثانیه برای هر پورت
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 100);

                // نمایش اطلاعات پورت باز
                if(getServiceName(port)!= "Unknown Service")
                System.out.println("Port " + port + " is open - Service: " + getServiceName(port));

                // بستن سوکت
                socket.close();
            } catch (Exception e) {
                // پورت بسته است یا خطای دیگر
            }
        }

        // بستن اسکنر
        scanner.close();
    }
}
