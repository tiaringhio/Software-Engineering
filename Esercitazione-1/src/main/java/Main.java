/**
 * Every method defined in the classes is shown here
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
		/**
		 * This scanner will be used when modifying a Person's data
		 */
		Scanner scan = new Scanner(System.in);

        Person person1 = new Person("Mattia", "Ricci", "mattiaricci@email.it", "password");
        Person person2 = new Person("Riccardo", "Lo Bue", "riccardolb@email.it", "password");
		System.out.println("Created two new users:\n" + person1.toString() + "\n" +  person2.toString());
		System.out.println("----------------------------------------------------------------------------------------");

		Member member1 = new Member("Giovanni", "Bianchi", "jbianchi@email.it", "password");
		Admin admin1 = new Admin("Peter", "Logan", "plogan@email.it",  "admin");
		System.out.println("Created a member and an admin\n" + member1.toString() + "\n" +  admin1.toString());
		System.out.println("----------------------------------------------------------------------------------------");

        Activity activity1 = new Activity("Sport");
        Race race1 = new Race("Formula 1");
        Course course1 = new Course("Italian GP");
        System.out.println("Created an activity, a race and a course\n" + activity1.toString() + "\n" +race1.toString() + "\n" + course1.toString());
		System.out.println("----------------------------------------------------------------------------------------");

        admin1.subscribeMember(member1, activity1);
        admin1.subscribeMember(person1, activity1);
		System.out.println("Added users to the activity\n" +  activity1.activityMembers());
		System.out.println("----------------------------------------------------------------------------------------");

        admin1.unsubscribeMember(person1, activity1);
		System.out.println("Removed a user from the activity\n" +  activity1.activityMembers());
		System.out.println("----------------------------------------------------------------------------------------");

		admin1.subscribeMember(person1, race1);
		admin1.subscribeMember(person2, race1);
		System.out.println("Added users to the race\n" +  race1.activityMembers());
		System.out.println("----------------------------------------------------------------------------------------");

        admin1.unsubscribeMember(person1, race1);
		System.out.println("Removed a user from the race\n" +  race1.activityMembers());
		System.out.println("----------------------------------------------------------------------------------------");

		admin1.subscribeMember(person1, course1);
		admin1.subscribeMember(person2, course1);
		System.out.println("Added users to the course\n" +  course1.activityMembers());
		System.out.println("----------------------------------------------------------------------------------------");

		admin1.unsubscribeMember(person1, course1);
		System.out.println("Removed a user from the course\n" +  course1.activityMembers());
		System.out.println("----------------------------------------------------------------------------------------");

		String name;
		String surname;
		String mail;
		String password;
		
		System.out.println("Let's modify a member's data \nInsert Name,Surname,Mail and Password");
    	
    	name = scan.next();
    	surname = scan.next();
    	mail = scan.next();
    	password = scan.next();
		member1.modifyMemberData(member1, name, surname, mail, password);

		System.out.println("Here's the updated member\n" + member1.toString());
		System.out.println("----------------------------------------------------------------------------------------");


		System.out.println("Now let's modify an admin's data \nInsert Name,Surname,Mail and Password");

		name = scan.next();
		surname = scan.next();
		mail = scan.next();
		password = scan.next();
		admin1.modifyData(admin1, name, surname, mail, password);

		System.out.println("Here's the updated admin\n" + admin1.toString());
    }
}
