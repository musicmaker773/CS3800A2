import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Client {
    
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;
    
    public static void main(String args[]) throws Exception
    {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        
        String name = "unknown";
        
        ServerConnection serverConn = new ServerConnection(socket);
        
        (new Thread(serverConn)).start();
        
        BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (  true )
        {
            System.out.print("(Me): ");
            String command = keyboard.readLine();
            if ( command.equalsIgnoreCase("BYE") || command.equalsIgnoreCase("exit") )
            {
                System.out.println("Connection ended by client");
                out.println(command);
                break;
            }
            if (command.startsWith("login")) {
                int firstSpace = command.indexOf(" ");
                if (firstSpace != -1) {
                    name = command.substring(firstSpace+1);
                }
            }
            String sentString = "";
            StringTokenizer st = new StringTokenizer(command);
            if (st.nextToken().equals("say")) {
                sentString = "say " + name + " :";
                while(st.hasMoreTokens()) {
                    sentString = sentString + " " + st.nextToken();
                }
            }
            else {
                sentString = command;
            }
            
            out.println(sentString);
            
        }
        socket.close();
        System.exit(0);
    }
    
}
