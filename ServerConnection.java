import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHUBHAM
 */
public class ServerConnection implements Runnable {
    
    private Socket server;
    private BufferedReader in;

    
    public ServerConnection(Socket s) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));

    }
    
    
    @Override
    public void run() {
            try {
                while(true) {
                    String serverResponse = in.readLine();
                    
                    if (serverResponse == null) {
                        break;
                    }
                    System.out.println(serverResponse);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            
        
            
            
    }
}

