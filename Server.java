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
import java.util.*;


public class Server {

    private static final int SERVER_PORT = 9090;
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(30);
    private static ArrayList<String> names = new ArrayList<>();
    
    
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(SERVER_PORT);
        while (true) {
            
            System.out.println("Waiting for client connection...");
            Socket client = listener.accept();
            System.out.println("Connected to client!!");
            ClientHandler clientThread = new ClientHandler(client, clients);
            clients.add(clientThread);
            
            pool.execute(clientThread);
        }
    }
    public static void addName(String name) {
        names.add(name);
    }
    public static void removeName(String name) {
        names.remove(name);
    }
    public static String getList() {
        String output = "";
        if(names.size() > 0) {
            output = "List of people logged into the server: ";
            for(int i = 0; i < names.size(); i++) {
                output = output + names.get(i) + " ";
            }
        }
        else {
            output = "There are no logged-in users.";
        }
        return output;
    }

    
    
}
