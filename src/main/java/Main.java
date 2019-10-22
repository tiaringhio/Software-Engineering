import java.util.ArrayList;

public class Main {
    // Every Wine is stored here
    public static ArrayList<Wine> wineList = new ArrayList<>();
    // Every Client is stored here
    public static ArrayList<Client> clientList = new ArrayList<>();
    // Every Employee is stored here
    public static ArrayList<Employee> employeeList = new ArrayList<>();
    // This is the cart
    public static ArrayList<Wine> cartList = new ArrayList<>();
    // This is the list containing Client's order, waiting to be processed by an Employee
    public static ArrayList<Wine> toBeProcessedList = new ArrayList<>();
    // This is the request list, where the Client puts the item he wants that are not available
    public static  ArrayList<Wine> requestList = new ArrayList<>();

    public static void main(String[] args){

        // Created three clients and added them to Client list
        Client client1 = new Client("Mattia", "Ricci", "tia", "pass", false);
        Client client2 = new Client("Gaspare", "Lo Bue", "gas", "pass", false);
        Client client3 = new Client("Riccardo", "Napolitano", "rick", "pass", false);
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        System.out.print("Created three clients:\n" + clientList.toString() + "\n");
        System.out.println("----------------------------------------------------------------------------------------");

        // Created Employee and added to Employee list
        Employee employee1 = new Employee("Marco", "Rossi", "mark", "password");
        employeeList.add(employee1);
        System.out.print("Created an employee:\n" + employeeList.toString() + "\n");
        System.out.println("----------------------------------------------------------------------------------------");

        // Created two Wines and added them to Wine list
        Wine wine1 = new Wine("Nebbiolo", 2005, "Fruity", "Travaglini", 20);
        Wine wine2 = new Wine("Chianti", 2007, "Tasty", "Ricci", 20);
        wineList.add(wine1);
        wineList.add(wine2);
        System.out.print("Created two wines:\n" + wineList.toString() +"\n");
        System.out.println("----------------------------------------------------------------------------------------");

        // Logging the Client in, User input required
        client1.login();
        System.out.println("----------------------------------------------------------------------------------");

        // The searches for a wine, decides if he wants to add the item to the cart,
        // buys a wine, the quantity is decided via user input
        // if the user requests more bottles than available a message will be displayed
        client1.buyWine();
        System.out.println("----------------------------------------------------------------------------------------");
        employee1.shipOrder();
        System.out.println("----------------------------------------------------------------------------------------\n\n");

        // The client will buy every bottle of wine available, the quantity is decided via user input
        client2.login();
        System.out.println("----------------------------------------------------------------------------------");
        // The client searches a Wine
        client1.search("Chianti", 2007);
        System.out.println("----------------------------------------------------------------------------------------");
        client2.buyWine();
        System.out.println("----------------------------------------------------------------------------------------");
        employee1.shipOrder();
        System.out.println("----------------------------------------------------------------------------------------\n\n");

        // The client will request more bottles of wine, since Client 2 has bought everything
        client3.login();
        System.out.println("----------------------------------------------------------------------------------------");
        client1.search("Chianti", 2007);
        System.out.println("----------------------------------------------------------------------------------------");
        client3.buyWine();
        System.out.println("----------------------------------------------------------------------------------------");
        employee1.addBottles(wine2);
        System.out.println("----------------------------------------------------------------------------------------");



    }
}
