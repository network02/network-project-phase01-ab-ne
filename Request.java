import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Request {

    private Socket socket;







        public void SendReq() throws Exception
        {

            //Instantiate a new socket
            Socket s = new Socket("localhost", 8080);

            //Instantiates a new PrintWriter passing in the sockets output stream
            PrintWriter wtr = new PrintWriter(s.getOutputStream());

            //Prints the request string to the output stream
            wtr.println("GET / HTTP/1.1");
            wtr.println("Host: www.badunetworks.com");
            wtr.println("");
            wtr.flush();

            //Creates a BufferedReader that contains the server response
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String outStr;

            //Prints each line of the response
            while((outStr = bufRead.readLine()) != null){
                System.out.println(outStr);
            }


            //Closes out buffer and writer
            bufRead.close();
            wtr.close();

        }

        void get()  {

            try
            {
                Socket s = new Socket("localhost ",8080);
                //s.isConnected();
            }


             catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


}
