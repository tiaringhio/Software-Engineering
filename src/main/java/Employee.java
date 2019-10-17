import java.util.Scanner;

public class Employee extends Person {

    Scanner scan = new Scanner(System.in);
    /**
     * The constructor
     *
     * @param name Employee's name
     * @param surname Employee's surname
     * @param user Employee's username
     * @param password Employee's password
     */
    public Employee(String name, String surname, String user, String password) {
        super(name, surname, user, password);
    }

    /**
     * this method adds more Wine bottles if there's none
     * and notifies the Clients.
     *
     * @param wine Wine's chosen
     */
    public void addBottles(Wine wine) {
        int newQuantity = 0;
        if (!Main.requestList.isEmpty()){
            newQuantity = Main.requestList.get(0).getProduced();
            wine.setProduced(newQuantity);
            System.out.println("Hey! We've updated the wines list!:\n" + wine.toString());
            Main.requestList.clear();
        }
        else {
            System.out.println("There's no requests pending!");
        }
    }

    /**
     * This method confirms the order thus shipping it.
     * It the clears the list of the order to be processed.
     */
    public void shipOrder() {
        if(!Main.toBeProcessedList.isEmpty()) {
            System.out.println("Order processed, your items are on their way!");
            Main.toBeProcessedList.clear();
        }
        else {
            System.out.println("Nothing to see here, everything's good!\n");
        }
    }
}
