import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;


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
        System.out.println("    5 - Exit                                 ");
        System.out.println("---------------------------------------------------");
        System.out.print("Choose a number : ");
        int inputNumMainPage = scanner.nextInt();

        switch (inputNumMainPage) {
            case 1:
                isOnline();
                MainPage();
                break;
            case 2:
                checkPorts();
                MainPage();
                break;
            case 3:
                get();
                MainPage();
                break;
            case 4:
                post();
                MainPage();
                break;
            case 5:
                break;
            default:
                MainPage();
        }
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

    private static boolean isOnline(String host ,int port) throws IOException {

        Socket s = new Socket(host,port);
        return s.isConnected();

        /*
        try(Socket socket = new Socket()) {
            s.connect(new InetSocketAddress(host,port),2000);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

         */

    }



    private static void checkPorts(){

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
        //scanner.close();
    }

    private static String getServiceName(int port) {// تابع برای گرفتن نام سرویس بر اساس شماره پورت

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
            case 29:
                return "MSG-ICP";
            case 41:
                return "Graphics";
            case 53:
                return "DNS";
            case 80:
                return "HTTP";
            case 110:
                return "POP3";
            case 123:
                return "NTP";
            case 143:
                return "IMAP";
            case 443:
                return "HTTPS";
            default:
                return "Unknown Service";
        }
    }



    private static void get() throws IOException {

        String host = "localhost";
        int port = 8080;
        String userID=getID();

        try {
            // Create a socket and connect to the server
            Socket socket = new Socket(host, port);

            // Send a GET request for a user with ID 1
            String request = "GET " + userID + "\r\n";
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

    private static String getID(){

        Scanner sc = new Scanner(System.in);
        System.out.println("enter the user ID you want to see the information");
        String userId = sc.next();
        return userId;

    }


    private static void post(){

        Scanner sc = new Scanner(System.in);
        System.out.println("enter the name of user you want to add");
        String name = sc.next();
        System.out.println("enter the age of user");
        int age = sc.nextInt();
        post(name,age);
    }

    private static void post(String name,int age) {
            String host = "localhost";
            int port = 8080;


            try {
                // Create a socket and connect to the server
                Socket socket = new Socket(host, port);

                // Send a POST request to add a user with name "John" and age "25"
                String request = "POST " + name + " "+ age + "\r\n";
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




