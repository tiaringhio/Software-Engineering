/**
 * Every method defined in the classes is shown here
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Riccardo Lo Bue riccardo.lobue@studenti.unipr.it
 */
public class Main {
    public static void main(String[] args) {

        Person person1 = new Person("Mattia", "Ricci", "mattiaricci@email.it", "password");
        Person person2 = new Person("Riccardo", "Lo Bue", "riccardolb@email.it", "password");

        Activity activity1 = new Activity("Football");
        Race race1 = new Race("Champions League");
        Course course1 = new Course(" Serie A");


        Member member1 = new Member("Giovanni", "Bianchi", "jbianchi@email.it", "password");
        Admin admin1 = new Admin("Peter", "Logan", "plogan@email.it",  "admin");

        System.out.println("Every person existent: " + person1.toString() + person2.toString() + "\n");
        activity1.addPerson(person1);
        activity1.addPerson(person2);

        activity1.deletePerson(person1);

        for (Person p:activity1.getSubscribers()) {
            System.out.println("Ecco gli iscritti all'attivita scelta: " + p.toString() + "\n");
        }

        member1.subscribeActivity(activity1);
        member1.subscribeActivity(race1);

        admin1.subscribeActivity(activity1);
        admin1.subscribeActivity(race1);

        System.out.println("Every member existent: " + member1.toString() + "\n");
        System.out.println("Every member existent: " + admin1.toString() + "\n");

        for (Person p:race1.getSubscribers()) {
            System.out.println("Every subscribers to the race: " + p.toString() + "\n");
        }

        for(Person p:course1.getSubscribers()) {
            System.out.println("Every subscribers to the course: " + p.toString() + "\n");
        }
    }
}
