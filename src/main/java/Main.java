import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Every Wine is stored here
    public static ArrayList<Wine> wineList = new ArrayList<>();
    // Every Client is stored here
    public static ArrayList<Client> clientList = new ArrayList<>();
    // Every Employee is stored here
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    // This is the cart
    public static ArrayList<Wine> cartList = new ArrayList<>();

    public static void main(String[] args){
        // Scanner for searching wines with user's input
        Scanner scan = new Scanner(System.in);

        // Created two clients and added them to Client list
        Client client1 = new Client("Mattia", "Ricci", "tia", "password");
        Client client2 = new Client("Gaspare", "Lo Bue", "gas", "password");
        clientList.add(client1);
        clientList.add(client2);
        System.out.print("Created two clients:\n" + clientList.toString() + "\n");
        System.out.println("----------------------------------------------------------------------------------------");

        // Created Employee and added to Employee list
        Employee employee1 = new Employee("Marco", "Rossi", "mark", "password");
        employeeList.add(employee1);
        System.out.print("Created an employee:\n" + employeeList.toString() + "\n");
        System.out.println("----------------------------------------------------------------------------------------");

        // Created two Wines and added them to Wine list
        Wine wine1 = new Wine("Nebbiolo", 2005, "Fruity", "Travaglini", 20);
        Wine wine2 = new Wine("Chianti", 2007, "Fucking strong", "Ricci", 30);
        wineList.add(wine1);
        wineList.add(wine2);
        System.out.print("Created two wines:\n" + wineList.toString() +"\n");
        System.out.println("----------------------------------------------------------------------------------------");

        // The client searches a Wine
        client1.addToCart("Nebbiolo", 2005);
        System.out.println(client1.printList(cartList));
        System.out.println("----------------------------------------------------------------------------------------");

        //TODO finish login method
        /*String user = null;
        String password = null;
        System.out.println("Insert user and password\n");
        user = scan.next();
        password = scan.next();
        client1.login(client1, user, password);*/

    }
}
