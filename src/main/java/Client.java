public class Client extends Person {
    /**
     * The constructor
     *
     * @param name Client's name
     * @param surname Client's surname
     * @param user Client's username
     * @param password Client's password
     */
    public Client(String name, String surname, String user, String password) {
        super(name, surname, user, password);
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
     * Logs the user in to allow them to buy wine
     * TODO finish this method
     *
     * @param client Client to log
     * @param user Client's username
     * @param password Client's password
     */
    public void login(Client client, String user, String password){
        boolean logged = false;
        while(!logged){
            if(client.getUser().equals(user) && client.getPassword().equals(password)) {
                logged = true;
            }
        }
    }

    /**
     * This method searches for te fine, if found
     * a simple console  message is displayed
     *
     * @param name Wine's name
     * @param year Wine's year
     */
    public void search(String name, int year){
        for (Wine w : Main.wineList) {
            if (w.getName().equals(name) && w.getYear() == year){
                System.out.println("Item found!");
            }
            System.out.println("Item not found");
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

    // TODO method to buy wine
    public void buyWine(Wine wine){

    }


}
