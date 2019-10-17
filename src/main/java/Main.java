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
        Client client1 = new Client("Mattia", "Ricci", "tia", "password", false);
        Client client2 = new Client("Gaspare", "Lo Bue", "gas", "password", false);
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

        // Logging the Client in, User input required
        client1.login();
        System.out.println("----------------------------------------------------------------------------------------");

        // The client searches a Wine
        client1.search("Nebbiolo", 2005);
      //  System.out.println(client1.printList(cartList));
        System.out.println("----------------------------------------------------------------------------------------");

        // The client adds a Wine to the cart
        client1.addToCart("Nebbiolo", 2005);
        System.out.println(client1.printList(cartList));
        System.out.println("----------------------------------------------------------------------------------------");

        /**
         * The client buys a wine, the quantity is decided via user input
         * if the user requests more bottles than available e message will be displayed
         */
        client1.buyWine(wine1);

    }
}
