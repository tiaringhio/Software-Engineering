public class Client extends Person {
    /**
     *
     * @param name
     * @param surname
     * @param user
     * @param password
     */
    public Client(String name, String surname, String user, String password) {
        super(name, surname, user, password);
    }

    /**
     *
     * @param client
     * @param name
     * @param surname
     * @param user
     * @param password
     */
    public void modify (Client client, String name, String surname, String user, String password) {
        client.setName(name);
        client.setSurname(surname);
        client.setUser(user);
        client.setPassword(password);
    }

    public void search (Client client, Wine wine) {

    }
}
