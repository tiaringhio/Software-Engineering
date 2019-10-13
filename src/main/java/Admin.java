/**
 * Admin is a subclass of Member, it has every aspect defined there but
 * has more methods so that it can have more privileges
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Riccardo Lo Bue riccardo.lobue@studenti.unipr.it
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
     * This method registers a Person to an Activity
     *
     * @param activity the activity to which i register the person
     */
    public void Registration(Activity activity) {
        activity.addPerson(Admin.this);
    }

    /**
     * This method subscribes a Member to an Activity
     *
     * @param member the Member to subscribe
     * @param activity the Activity to subscribe the Member to
     */
    public void SubscribeMember(Member member, Activity activity) {
        member.subscribeActivity(activity);
    }

    /**
     * This method unsubscribes a Member from an Activity
     *
     * @param member the Member to unsubscribe
     * @param activity  the Activity to unsubscribe the Member from
     */
    public void UnsubscribeMember(Member member, Activity activity) {
        member.unsubscribeActivity(activity);
    }

    /**
     * This method modifies a Member's data
     *
     * @param member the Member to modify
     */
    public void ModifyMember(Member member) {
        member.modifyData(member, name, surname, mail, password);
    }

    /**
     * This method modifies and Admin's data
     *
     * @param admin the Admin to modify
     */
    public void ModifyAdmin(Admin admin){
        admin.modifyData(admin, name, surname, mail, password);
    }

    /**
     * This method prints the Admin's data in a more clean way,
     * it overrides the toString method
     *
     * @return The Admin's data
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
