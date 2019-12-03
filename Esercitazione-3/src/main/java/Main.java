import java.io.IOException;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Mattia Ricci
 * @author Riccardo Lo Bue
 */
public class Main {

    public static class generateFiscalCode {
        private final static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        /**
         * generates the fiscal code
         *
         * @return a 16 character alphanumeric string
         */
        static String generateRandom() {
            Random random = new SecureRandom();
            StringBuilder sb = new StringBuilder(16);
            for (int i = 0; i < 16; i++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            return sb.toString();
        }
    }

    private void send() {
        try {

            String serverIP = "127.0.0.1";
            int port = 4444;
            Socket socket = new Socket(serverIP, port);

            Workplace workplace1 = new Workplace("Parma", "Via Mazzini, 25, 43121, Parma");
            Workplace workplace2 = new Workplace("Milano", "Via Nobili, 14, 20019, Milano");

            Employee employee1 = new Employee(0,"Mattia", "Ricci", generateFiscalCode.generateRandom(), workplace1, "Employee", "25/05/19", "25/05/20");
            Officer officer1 = new Officer(1,"Marco", "Rossi", generateFiscalCode.generateRandom(), workplace1, "Officer", "22/04/18", "22/04/20", "mark", "pass", socket);
            Officer officer2 = new Officer(2,"Lucia", "Grandi", generateFiscalCode.generateRandom(), workplace2, "Officer", "22/04/18", "22/04/20", "lucia", "pass", socket);
            Leader leader1 = new Leader(3, "Giorgio", "Vanni", generateFiscalCode.generateRandom(), workplace1, "Leader", "22/01/18", "22/01/20", "giorgio", "pass", socket);
            Leader leader2 = new Leader(4, "Luca", "Vanni", generateFiscalCode.generateRandom(), workplace2, "Leader", "22/01/18", "22/01/20", "luca", "pass", socket);
            Administrator admin1 = new Administrator(5, "Fabio", "Bianchi", generateFiscalCode.generateRandom(), workplace2,"Administrator", "22/01/18", "22/01/21", "fabio", "pass", socket);

            officer1.login(officer1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer1.insertEmployee(employee1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer1.insertEmployee(officer1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer1.insertEmployee(leader1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer1.insertEmployee(leader2);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer1.insertEmployee(admin1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer1.printEmployees();
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer2.login();
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer2.updateEmployee(employee1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            officer1.printEmployees();
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            leader1.login(leader1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            leader1.searchEmployeeLeader();
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            admin1.login(admin1);
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            admin1.searchEmployeeAdmin();
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            socket.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().send();
    }
}
