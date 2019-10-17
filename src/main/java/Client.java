import java.util.Scanner;

public class Client extends Person {

    private boolean logged;
    private Scanner scan = new Scanner(System.in);


    /**
     * The constructor
     *
     * @param name
     * @param surname
     * @param user
     * @param password
     * @param logged
     */
    public Client(String name, String surname, String user, String password, boolean logged) {
        super(name, surname, user, password);
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
    /**
     * Modifies the Client's data
     *
     * @param client
     * @param name
     * @param surname
     * @param user
     * @param password
     */
    public void modifyData (Client client, String name, String surname, String user, String password) {
        client.setName(name);
        client.setSurname(surname);
        client.setUser(user);
        client.setPassword(password);
    }

    /**
     * Logs the users in to allow them to buy wine.
     * Check the user's input against what's benn created in the main,
     * if they match the user is logged in,
     * otherwise the function reprompts the input
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
     * a simple console  message is displayed
     *
     * @param name Wine's name
     * @param year Wine's year
     */
    public void search(String name, int year){
        System.out.println("What are you looking for?\n");
        for (Wine w : Main.wineList) {
            if (w.getName().equals(name) && w.getYear() == year){
                System.out.println("Item found!");
            }
            else {
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
     *
     * @param wine Wine to buy
     */
    public void buyWine(Wine wine){
        int toBuy = 0;
        System.out.println("How many bottles would you like?: ");
        toBuy = scan.nextInt();
        if (isLogged()){
            if (wine.getProduced() < toBuy){
                System.out.println("We're sorry, there are only " + wine.getProduced() + " bottles available!");
            }
            else {
                wine.setProduced(wine.getProduced() - toBuy);
                System.out.println("Purchase completed! here's how many bottles remain:\n" + wine.toString());
            }
        }
        else {
            System.out.println("You're not logged!");
        }
    }
}
