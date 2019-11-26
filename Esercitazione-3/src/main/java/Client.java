import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private static final int SocketPort = 4444;
    private static  final String ClientHost = "localhost";

    public void send(){
        try {
            Socket client = new Socket(ClientHost, SocketPort);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            dataOutputStream.writeBytes("Hello\n");

            System.out.println("Client recevied: " + bufferedReader.readLine());
            client.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static void main(final String[] v){
        new Client().send();
    }
}
