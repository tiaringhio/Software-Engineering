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
     * This method modifies a Member's information using the setters methods defined in Person, not a person
     * So a simple member cannot modify a Person's data, while an Admin can
     *
     * @param member the Member
     * @param name the Member's name
     * @param surname the Member's surname
     * @param mail the Member's mail
     * @param password the Member's password
     */
    void modifyMemberData(Member member, String name, String surname, String mail, String password) {
        member.setName(name);
        member.setSurname(surname);
        member.setMail(mail);
        member.setPassword(password);
    }
}
