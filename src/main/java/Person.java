/**
 * Person has name, surname, mail and password. It's the main Object
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Riccardo Lo Bue riccardo.lobue@studenti.unipr.it
 */
public class Person {
    String name;
    String surname;
    String mail;
    String password;

    /**
     * Empty constructor for the objectsEmpty
     */
    public Person() {
    }
    /**
     * This is the constructor
     *
     * @param name  the name of the Person
     * @param surname  the surname of the Person
     * @param mail the mail of the Person
     * @param password the password of the Person
     *
     * @return void
     */
    public Person(String name, String surname, String mail, String password) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }

    /**
     * Gets the Person's name
     *
     * @return String
     */
    public String getName() { return name; }

    /**
     * Sets the Person's name
     *
     * @param name the Person's name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the Person's surname
     *
     * @return void
     */
    public String getSurname() { return surname; }

    /**
     * Sets the Person's surname
     *
     * @param surname the Person's surname
     */
    public void setSurname(String surname) { this.surname = surname; }

    /**
     * Gets the Person's mail
     *
     * @return void
     */
    public String getMail() { return mail; }

    /**
     * Sets the Person's mail
     *
     * @param mail the Person's mail
     */
    public void setMail(String mail) { this.mail = mail; }

    /**
     * Gets the Person's password
     * @return void
     */
    public String getPassword() { return password; }

    /**
     * eSts the Person's password
     *
     * @param password the Person's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method prints the Person's data in a more clean way,
     * it overrides the toString method
     *
     * @return String the Person's data
     */
    @Override
    public String toString() {
        return "(" +
            "Name='" + getName() + "'" +
            ", Surname='" + getSurname() + "'" +
            ", Mail='" + getMail() + "'" +
            ", Password='" + getPassword() + "'" + ")";
    }
}