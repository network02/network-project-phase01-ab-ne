import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        MainPage();
    }

    public static void MainPage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------------");
        System.out.println("                  Welcome To Nmap                  ");
        System.out.println("---------------------------------------------------");
        System.out.println("    1 - Check Host Status                          ");
        System.out.println("    2 - Check Port Status of a Host                ");
        System.out.println("    3 - Get                                  ");
        System.out.println("    4 - Post                                 ");
        System.out.println("---------------------------------------------------");
        System.out.print("Choose a number : ");
        int inputNumMainPage = scanner.nextInt();

        switch (inputNumMainPage) {
            case 1:
                isOnline();
                break;
            case 2:
                checkinhPorys();
                break;
            case 3:
                get();
                break;
            case 4:
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


    private static boolean isOnline(String host ,int port) throws IOException {
        Socket s = new Socket(host,port);
        return s.isConnected();

        /*
        try(Socket socket = new Socket()) {
            s.connect(new InetSocketAddress(host,port),2000);
            System.out.println("online");
        }
        catch (Exception e)
        {
            System.out.println("offline");
        }

         */






    }

    private static void isOnline() throws IOException {
        System.out.println("enter the host/IP address");

        Scanner scanner = new Scanner(System.in);
        String host = scanner.next();

        System.out.println("enter the post number");
        int port = scanner.nextInt();

        if (isOnline(host,port))
            System.out.println("HOST IS ONLINE");
        else
            System.out.println("HOST IS OFFLINE");
    }

    private static void get() throws IOException {
        String host = "localhost";
        int port = 8080;
        try {
            // Create a socket and connect to the server
            Socket socket = new Socket(host, port);

            // Send a GET request for a user with ID 1
            String request = "GET /users/1\r\n";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(request);
            writer.flush();

            // Read the server's response
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                System.out.println(responseLine);
            }

            // Close the connections
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


}


