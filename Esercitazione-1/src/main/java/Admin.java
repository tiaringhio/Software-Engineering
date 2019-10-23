/**
 * Admin is a subclass of Member, it has every aspect defined there but
 * has more methods so that it can have more privileges
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
public class Admin extends Member {
    /**
     * The constructor
     *
     * @param name Admin's name
     * @param surname Admin's surname
     * @param mail Admin's mail
     * @param password Admin's password
     */
    public Admin(String name, String surname, String mail, String password) {
        super(name, surname, mail, password);
    }

    /**
     * This method subscribes a Member to an Activity
     *
     * @param person the Member to subscribe
     * @param activity the Activity to subscribe the Member to
     */
    public void subscribeMember(Person person, Activity activity) {
        activity.addPerson(person);
    }

    /**
     * This method unsubscribes a Member from an Activity
     *
     * @param person the Member to unsubscribe
     * @param activity  the Activity to unsubscribe the Member from
     */
    public void unsubscribeMember(Person person, Activity activity) {
        activity.deletePerson(person);
    }

    /**
     * This methods modifies a Person's data using the setters methods defined in Person.
     * This means that an admin can modify every Person's data, while a member can't.
     *
     * @param person
     * @param name
     * @param surname
     * @param mail
     * @param password
     */
    void modifyData(Person person, String name, String surname, String mail, String password) {
        person.setName(name);
        person.setSurname(surname);
        person.setMail(mail);
        person.setPassword(password);
    }
}
