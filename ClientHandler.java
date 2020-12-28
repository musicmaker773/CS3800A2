import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.util.*;
/**
 *
 * @author SHUBHAM
 */
public class ClientHandler implements Runnable {
    
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    private String group = "general";
    private String name = "unknown";
    
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws Exception {
        client = clientSocket;
        this.clients = clients;
        in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        out=new PrintWriter(client.getOutputStream(), true);
    }
    
    @Override
    public void run()
    {
        try {
            Server.addName(name);
            while(true) {
                String request = in.readLine();
                if (request.startsWith("say")){
                    // out.println("Type 'give me a name' to give you a random name...");
                    int firstSpace = request.indexOf(" ");
                    if (firstSpace != -1) {
                        outToAllC(request.substring(firstSpace+1), group);
                    }
                }
                else if (request.startsWith("login")) {
                    int firstSpace = request.indexOf(" ");
                    Server.removeName(name);
                    if (firstSpace != -1) {
                        name = request.substring(firstSpace+1);
                        Server.addName(request.substring(firstSpace+1));
                    }
                    outToAll("User " + request.substring(firstSpace+1) + " logged in.");
                    
                }
                else if (request.startsWith("join")) {
                    int firstSpace = request.indexOf(" ");
                    if (firstSpace != -1) {
                        group = request.substring(firstSpace+1);
                    }
                    outToAllC(name + " joined into " + group + " group...", group);
                }
                else if (request.startsWith("list")) {
                    String output = Server.getList();
                    out.println(output);
                }
                else if (request.equalsIgnoreCase("group")) {
                    out.println(getGroupMembers());
                }
                else if( request.equalsIgnoreCase("BYE") || request.equalsIgnoreCase("exit") ) {
                    outToAll("User " + name + " has logged out.");
                    Server.removeName(name);
                }
                else {
                    out.println("Type 'login' along with your name. Ex: login Bryce...");
                }
            }
        } catch (Exception e) {
            System.err.println("Exception in client handler!");
            e.printStackTrace();
        }finally {
            out.close();
            try {
                in.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
      
    }
    private void outToAllC(String msg, String g) {
        for(ClientHandler aClient : clients) {
            if (aClient.getGroup().equals(g)) {
                aClient.out.println(msg);
            }
            
        }
    }
    private void outToAll(String msg) {
        for(ClientHandler aClient : clients) {
            aClient.out.println(msg);
        }
    }
    public String getGroup() {
        return group;
    }
    public String getName() {
        return name;
    }
    private String getGroupMembers() {
        String mes = "List of people currently in the group: ";
        for(ClientHandler aClient : clients) {
            if (aClient.getGroup().equals(getGroup())) {
                mes = mes + aClient.getName() + " ";
            }
        }
        return mes;
    }
    
}
