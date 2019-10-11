public class Admin extends Member {

    public Admin(String name, String surname, String mail, String password) {
        super(name, surname, mail, password);
    }

    public void Registration(Activity activity) {
        activity.addPerson(Admin.this);
    }

    public void SubscribeMember(Member member, Activity activity) {
        member.subscribeActivity(activity);
    }

    public void UnsubscribeMember(Member member, Activity activity) {
        member.unsubscribeActiviy(activity);
    }

    public void ModifyMember(Member member) {
        member.modifyData(member, name, surname, mail, password);
    }

    public void ModifyAdmin(Admin admin){
        admin.modifyData(admin, name, surname, mail, password);
    }

    @Override
    public String toString() {
        return "(" +
                "nome='" + getName() + "'" +
                ", cognome='" + getSurname() + "'" +
                ", mail='" + getMail() + "'" +
                ", password='" + getPassword() + "'" + ")";
    }
}
