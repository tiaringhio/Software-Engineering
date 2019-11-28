import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    /**
     * This class generates the Fiscal Code
     */
    public static class generateFiscalCode {
        private final static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        public static String generateRandom(int length) {
            Random random = new SecureRandom();
            if (length <= 0) {
                throw new IllegalArgumentException("String length must be a positive integer");
            }

            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            return sb.toString();
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String serverIP = "127.0.0.1";
        int port = 4444;
        Socket socket = new Socket(serverIP, port);

        //ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        Workplace workplace1 = new Workplace("Parma", "Via Mazzini, 25, 43121, Parma");
        Workplace workplace2 = new Workplace("Milano", "Via Nobili, 14, 20019, Milano");

        Employee employee1 = new Employee(0,"Mattia", "Ricci", generateFiscalCode.generateRandom(16), workplace1, "Employee", "25/05/19", "25/05/20");
        Officer officer1 = new Officer(1,"Marco", "Rossi", generateFiscalCode.generateRandom(16), workplace1, "Officer", "22/04/18", "22/04/20", "mark", "pass", socket);
        Leader leader1 = new Leader(2, "Giorgio", "Vanni", generateFiscalCode.generateRandom(16), workplace2, "Leader", "22/01/18", "22/01/20", "giorgino", "pass", socket);
        Leader leader2 = new Leader(3, "Luca", "Vanni", generateFiscalCode.generateRandom(16), workplace2, "Leader", "22/01/18", "22/01/20", "giorgino", "pass", socket);
        //System.out.println("Employee:\n" + employee1.printEmployee());
        //System.out.println("Officer:\n" + officer1.toString());

        officer1.login();
        System.out.println("\n----------------------------------------------------------------------------------------\n");
        officer1.insertEmployee(leader1);
        System.out.println("\n----------------------------------------------------------------------------------------\n");
        officer1.insertEmployee(leader2);
        System.out.println("\n----------------------------------------------------------------------------------------\n");
        //System.out.println("Here's the existent employees:\n" + Server.Employees.toString());
        officer1.printEmployees();
        System.out.println("\n----------------------------------------------------------------------------------------");
        //officer1.updateEmployee(employee1);
        System.out.println("\n----------------------------------------------------------------------------------------");
        //System.out.println("Here's the existent employees:\n" + Server.Employees.toString());
        System.out.println("\n----------------------------------------------------------------------------------------");
        leader1.login();
        System.out.println("\n----------------------------------------------------------------------------------------");
        //leader1.searchEmployee();
        socket.close();
    }
}
