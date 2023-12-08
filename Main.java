import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("1-check if a host is online");
        System.out.println("2-check ports and print services");
        System.out.println("3-get method");
        System.out.println("4-post method");

        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();

        Request request = new Request();

        /*
        if (choice == 1)
        {
            System.out.println("enter IP address of host");

            String IP = sc.next();
            int port = sc.nextInt();

            if (request.isOnline(IP,port) )
                System.out.println( IP + " is online");
            else
                System.out.println(IP + " is offline");
        }

        else if (choice == 2)
            request.checkPorts();
        else if (choice == 3)
            request.get();
        else
            request.post();

        String ip = sc.next();

         */
    }
}
