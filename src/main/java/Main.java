public class Main {
    public static void main(String args[]) {

        /*
        *
        * */

        Person p1 = new Person("Mattia", "Ricci", "mattiaricci@email.it", "password");
        Person p2 = new Person("Riccardo", "Lo Bue", "riccardolb@email.it", "password");

        Activity a1 = new Activity("Calcio");
        Race g1 = new Race("Torneo Tre Maghi");

        /*
        * */

        Member s1 = new Member("Giovanni", "Bianchi", "jbianchi@email.it", "password");
        Admin ad1 = new Admin("Peter", "Logan", "plogan@email.it",  "admin");
        System.out.println("Ecco le persone esistenti: " + p1.toString() + p2.toString() + "\n");
        a1.addPerson(p1);
        a1.addPerson(p2);

        a1.deletePerson(p1);

        for (Person p:a1.getSubscribers()) {
            System.out.println("Ecco gli iscritti all'attivita scelta: " + p.toString() + "\n");
        }

        s1.subscribeActivity(a1);
        s1.subscribeActivity(g1);
        System.out.println("Ecco i soci esistenti: " + s1.toString() + "\n");

        for (Person p:g1.getSubscribers()) {
            System.out.println("Ecco gli iscritti alla gara: " + p.toString() + "\n");
        }
    }
}
