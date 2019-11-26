import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    // Server port
    private static final int SPORT = 4444;
    public void reply(){
        try {
            // Socket creation
            ServerSocket serverSocket = new ServerSocket(SPORT);
            Socket clientSocket = serverSocket.accept();
            // Gets user input
            BufferedReader is = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

            System.out.println("Server received: " + is.readLine());
            os.writeBytes("Done\n");

            clientSocket.close();
            serverSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Employee> Employees = new ArrayList<>();
    public static ArrayList<Workplace> Workplaces = new ArrayList<>();

    public static void main(final String[] v){
        new Server().reply();
    }
}
