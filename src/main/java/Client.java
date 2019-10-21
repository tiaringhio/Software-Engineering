/**
 * Every method defined in the classes is shown here
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
import java.util.Scanner;

public class Client extends Person {

    private boolean logged;
    private Scanner scan = new Scanner(System.in);


    /**
     * The constructor
     *
     * @param name Client's name
     * @param surname Client's surname
     * @param user Client's username
     * @param password Client's password
     * @param logged boolean to check if the Client is logged
     */
    public Client(String name, String surname, String user, String password, boolean logged) {
        super(name, surname, user, password);
        this.logged = logged;
    }

    /**
     *
     * @return boolean logged
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * Set logged value for Client
     *
     * @param logged boolean to check if the Client is logged
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * Logs the users in to allow them to buy wine.
     * Check the user's input against what's benn created in the main,
     * if they match the user is logged in,
     * otherwise the function re-prompts the input
     * until the user (hopefully) gets it right
     *
     */
    public void login(){
        String logUser;
        String logPass;
        System.out.println("Insert user and password\n");
        logUser = scan.next();
        logPass = scan.next();
        while(!isLogged()){
            if(this.user.equals(logUser) && this.password.equals(logPass)) {
                System.out.println("Welcome back!");
                setLogged(true);
            }
            else {
                System.out.println("Woops, Wrong data!");
                System.out.println("Insert user and password\n");
                logUser = scan.next();
                logPass = scan.next();
            }
        }
    }

    /**
     * This method searches for the wine, if found
     * a simple console  message is displayed.
     * I decided to user a counter so that item not found is only printed if the
     *
     * @param name Wine's name
     * @param year Wine's year
     */
    public void search(String name, int year){
        int counter = 0;
        for (Wine w : Main.wineList) {
            counter++;
            if (w.getName().equals(name) && w.getYear() == year){
                System.out.println("Item found!");
                break;
            }
            else if(counter == Main.wineList.size()) {
                System.out.println("Item not found");
            }
        }
    }

    /**
     * This method searches te wines and if it founds one
     * it adds that wine to a list defined in Main, cartList
     *
     * @param name Wines's main
     * @param year Wine's year
     */
    public void addToCart(String name, int year){
        for (Wine w : Main.wineList) {
            if(w.getName().equals(name) && w.getYear() == year){
                Main.cartList.add(w);
                System.out.println("Item found and added to cart!");
            }
        }
        if(Main.cartList.size()==0){
            System.out.println("Item not found");
        }
    }

    /**
     * This method allows the Client to buy Wine.
     * The method will ask for a quantity,
     * if the quantity requested is more than the one available
     * it will print a message and ask if the Client wants to request for more bottles.
     * If the clients buys every bottle it will request if the Client wants to ask for more
     * bottles.
     *
     * @param wine Wine to buy
     */
    public void buyWine(Wine wine){
        int toBuy;
        boolean askForRequest;
        int howMany;
        System.out.println("How many bottles would you like?: ");
        toBuy = scan.nextInt();
        if (isLogged()){
            if (wine.getProduced() < toBuy){
                System.out.println("We're sorry, there are only " + wine.getProduced() + " bottles available!");
                System.out.println("Do you want to request more bottles? true / false\n");
                askForRequest = scan.nextBoolean();
                if (askForRequest) {
                    System.out.println("How many bottles would you like?\n");
                    howMany = scan.nextInt();
                    Main.requestList.add(new Wine(wine.getName(), wine.getYear(), wine.getTechnical_notes(), wine.getVine(),howMany));
                    System.out.println("Ordered " + howMany + " bottles");
                }
                else {
                    System.out.println("Alright! Maybe next time!");
                }
            }
            else {
                wine.setProduced(wine.getProduced() - toBuy);
                Main.toBeProcessedList.add(new Wine(wine.getName(), wine.getYear(), wine.getTechnical_notes(), wine.getVine(), toBuy));
                System.out.println("Added order to be processed!\n" + Main.toBeProcessedList.toString() + "\n");
                System.out.println("Here's how many bottles remain:\n" + wine.toString());
                if (wine.getProduced() == 0){
                    System.out.println("Do you want to request more bottles? true / false\n");
                    askForRequest = scan.nextBoolean();
                    if (askForRequest) {
                        System.out.println("How many bottles would you like?\n");
                        //new Wine(wine.getName(), wine.getYear(), wine.getTechnical_notes(), wine.getVine(),wine.getProduced())
                        howMany = scan.nextInt();
                        Main.requestList.add(new Wine(wine.getName(), wine.getYear(), wine.getTechnical_notes(), wine.getVine(),howMany));
                        System.out.println("Ordered " + howMany + " bottles");
                    }
                    else {
                        System.out.println("Alright! Maybe next time!");
                    }

                }
            }
        }
        else {
            System.out.println("You're not logged!");
        }
    }
}
