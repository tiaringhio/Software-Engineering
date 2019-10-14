/**
 * Member is a subclass of Person, it can subscribe/unsubscribe to/from an Activity
 * and change the Members data
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
public class Member extends Person {
    /**
     * The constructor
     *
     * @param name the Member's name
     * @param surname the Member's surname
     * @param mail the Member's mail
     * @param password the Member's password
     */
    public Member(String name, String surname, String mail, String password) {
        super(name, surname, mail, password);
    }

    /**
     * This method subscribes a member to a specific Activity,
     * It uses the addPerson method from Person, since Member extends Person
     *
     * @param activity the activity
     */
    void subscribeActivity(Activity activity) {
        activity.addPerson(Member.this);
    }

    /**
     * This method unsubscribes a member from a specific Activity,
     * It uses the deletePerson method from Person, since Member extends Person
     *
     * @param activity the activity
     */
    void unsubscribeActivity(Activity activity) {
        activity.deletePerson(Member.this);
    }

    /**
     * This method modifies a Member's information using the setters methods defined in Person
     *
     * @param member the Member
     * @param name the Member's name
     * @param surname the Member's surname
     * @param mail the Member's mail
     * @param password the Member's password
     */
    void modifyData(Member member, String name, String surname, String mail, String password) {
        member.setName(name);
        member.setSurname(surname);
        member.setMail(mail);
        member.setPassword(password);
    }

    /**
     * This method prints the Member's data in a more clean way,
     * it overrides the toString method
     *
     * @return the Member's data
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
