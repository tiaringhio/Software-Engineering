/**
 * Every method defined in the classes is shown here
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
import java.util.ArrayList;

public abstract class Person {
    String name;
    String surname;
    String user;
    String password;

    /**
     * The constructor
     *
     * @param name Person's name
     * @param surname Person's surname
     * @param user Person's username
     * @param password Person's password
     */
    public Person(String name, String surname, String user, String password) {
        this.name = name;
        this.surname = surname;
        this.user = user;
        this.password = password;
    }

    /**
     * Gets the Person's name
     *
     * @return String name, the Person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Person's name
     * @param name the Person's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Person's
     *
     * @return String surname the Person's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the Person's  surname
     *
     * @param surname the Person's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the Person's username
     *
     * @return String the Person's username
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the Person's username
     *
     * @param user the Person's username
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the Person's password
     *
     * @return String the Person's  password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the Person's password
     *
     * @param password the Person's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Prints the Person's data in a clean way.
     * It overrides the toString method
     *
     * @return String the Person's data
     */
    @Override
    public String toString() {
        return "(" +
                "Name='" + getName() + "'" +
                ", Surname='" + getSurname() + "'" +
                ", User='" + getUser() + "'" +
                ", Password='" + getPassword() + "'" + ")";
    }

    /**
     * Prints every element in a list, used to print ArrayLists
     *
     * @param list the list to print
     * @return String
     */
    public String printList(ArrayList list){
        return list.toString();
    }
}
