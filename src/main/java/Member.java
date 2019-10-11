public class Member extends Person {

    public Member(String name, String surname, String mail, String password) {
        super(name, surname, mail, password);
    }

    /*
    * Iscrivo un socio ad un'attività
    *
    * @param attivita
    *
    * */
    void subscribeActivity(Activity activity) {
        activity.addPerson(Member.this);
    }
    /*
     * Disiscrivo un socio ad un'attività
     *
     * @param attivita
     *
     * */
    void unsubscribeActiviy(Activity activity) {
        activity.deletePerson(Member.this);
    }
    /*
    * Modifico i dati di un socio usando i metodi set di Persona
    *
    * @param socio il mio socio
    * @param nome, il nome che andrà a sostituire quello vecchio
    * @param cognome, il cognome che andrà a sostituire quello vecchio
    * @param mail, la mail che andrà a sostituire quella vecchia
    * @param password, la password che andrà a sostituire quella vecchia
    * */
    void modifyData(Member member, String nome, String cognome, String mail, String password) {
        member.setName(nome);
        member.setSurname(cognome);
        member.setMail(mail);
        member.setPassword(password);
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
