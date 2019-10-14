/**
 * Every method defined in the classes is shown here
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    	
        String name = null;
        String surname = null;
        String mail = null;
        String password = null;
        String name_race = null;
        String name_course = null;            

        Person person1 = new Person("Mattia", "Ricci", "mattiaricci@email.it", "password");
        Person person2 = new Person("Riccardo", "Lo Bue", "riccardolb@email.it", "password");
    	
       Person person3 = new Person( name , surname , mail , password );
    	Person person4 = new Person( name , surname , mail , password );

        Activity activity1 = new Activity("Sport");
        Race race1 = new Race("Formula 1");
        Course course1 = new Course(" Serie A");
        
        Race race2 = new Race(name_race);
        Course course2 = new Course(name_course);
        
        Member member1 = new Member("Giovanni", "Bianchi", "jbianchi@email.it", "password");
        Admin admin1 = new Admin("Peter", "Logan", "plogan@email.it",  "admin");

        Member member2 = new Member("Giovanni", "Bianchi", "jbianchi@email.it", "password");
        Admin admin2 = new Admin("Peter", "Logan", "plogan@email.it",  "admin");

        System.out.println("Every person existent:\n" + person1.toString() + "\n" +  person2.toString() + "\n");
        
        activity1.addPerson(person1);
        
        activity1.addPerson(person2);
        
//////////////////////////////////////////////////////////////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        
        int response;
        
        int conditions = 1;
        
        while (conditions==1)
        	
        {

        
        System.out.println("what do you want to do now? :\n"
        		+ "1. Add Admin\n"
        		+ "2. Add Member\n"
        		+ "3. Remove Admin\n"
        		+ "4. Remove Member\n"
        		+ "5. Add Race\n"
        		+ "6. Add Course\n"
        		+ "7. Remove Roce\n"
        		+ "8. Remove Course\n"
        		+ "--------------------------\n");
        
        response= scan.nextInt();
        
        switch (response) {
        
        case 1:
        	
            System.out.println("INSERT Name,Surname,Mail and Password");
        	
        	name= scan.next();
        	
        	surname= scan.next();
        	
        	mail= scan.next();
        	
        	password= scan.next();
        	
        	activity1.addPerson(person3);
        	
            break;
            
            
            
        case 2:
        	
        	System.out.println("INSERT Name,Surname,Mail and Password");
        
    		name= scan.next();
    	
    		surname= scan.next();
    	
    		mail= scan.next();
    	
    		password= scan.next();

    		activity1.addPerson(person4);
    	
    		break;
        
        case 3:
        	
            System.out.println("Every person existent: " + person1.toString() + person2.toString() + "\n");
        
            System.out.println("who do you want to delete? 1,2,3 or 4? ");

            int removing_person = scan.nextInt();
            
            switch (removing_person) {
            
              case 1:
            	  
            	  activity1.deletePerson(person1);  
            	  
                  System.out.println("Member delete");
                  
                break;
                
              case 2:
            	  
            	  activity1.deletePerson(person2);
            	  
            	  System.out.println("Member delete");
                  
                break;
                
              case 3:
            	  
            	  activity1.deletePerson(person3);
            	  
            	  System.out.println("Member delete");
                  
                break;
                
              case 4:
            	  
            	  activity1.deletePerson(person4);
            	  
            	  System.out.println("Member delete");
                  
                break;
              
            }
        
        break;
        
        case 4:
        
        System.out.println("Every person existent: " + person1.toString() + person2.toString() + "\n");
        
        System.out.println("who do you want to delete? 1,2,3 or 4? ");

        removing_person = scan.nextInt();
        
        switch (removing_person) {
        
          case 1:
        	  
        	  activity1.deletePerson(person1);  
        	  
              System.out.println("Member delete");
              
            break;
            
          case 2:
        	  
        	  activity1.deletePerson(person2);
        	  
        	  System.out.println("Member delete");
              
            break;
            
          case 3:
        	  
        	  activity1.deletePerson(person3);
        	  
        	  System.out.println("Member delete");
              
            break;
            
          case 4:
        	  
        	  activity1.deletePerson(person4);
        	  
        	  System.out.println("Member delete");
              
            break;
          
        }
        
        break;
        
        case 5:
        	
            System.out.println("INSERT Name Race ");
        	
        	name_race= scan.next();
        	
        	System.out.println("INSERT Members (Name,Surname,Mail and Password) ");
        	
        	name= scan.next();
        	
        	surname= scan.next();
        	
        	mail= scan.next();
        	
        	password= scan.next();
        	
        	activity1.addPerson(member2);
        	
            for (Person p:activity1.getSubscribers()) {
            	
                System.out.println("Ecco gli iscritti all'attivita scelta: " + p.toString() + "\n");
            
            }

            member1.subscribeActivity(activity1);
            
            member1.subscribeActivity(race2);

            admin1.subscribeActivity(activity1);
            
            admin1.subscribeActivity(race2);

            System.out.println("Every member existent: " + member2.toString() + "\n");
            
            System.out.println("Every member existent: " + admin2.toString() + "\n");

            for (Person p:race2.getSubscribers()) {
            	
                System.out.println("Every subscribers to the race: " + p.toString() + "\n");
            
            }
           

        break;
        
        case 6:
        	
            System.out.println("INSERT Name Course ");
        	
        	name_course= scan.next();
        	
        	System.out.println("INSERT Members (Name,Surname,Mail and Password) ");
        	
        	name= scan.next();
        	
        	surname= scan.next();
        	
        	mail= scan.next();
        	
        	password= scan.next();
        	
        	activity1.addPerson(admin2);
        	
        	for (Person p:activity1.getSubscribers()) {
        		
                System.out.println("Ecco gli iscritti all'attivita scelta: " + p.toString() + "\n");
            
        	}

            member1.subscribeActivity(activity1);
            
            member1.subscribeActivity(course2);

            admin1.subscribeActivity(activity1);
            
            admin1.subscribeActivity(course2);

            System.out.println("Every member existent: " + member2.toString() + "\n");
            
            System.out.println("Every member existent: " + admin2.toString() + "\n");
        	
            for(Person p:course1.getSubscribers()) {
            	
                System.out.println("Every subscribers to the course: " + p.toString() + "\n");
            }  

        break;
        
        case 7:
        	
        	System.out.println("Ancora non fatto ");
        	
        break;
        
        case 8:
        	
            System.out.println("Ancora non fatto ");
            
        break;
        
        default:
        	
        System.out.println("Error 404");
        
        break;
        
        }

        System.out.println("Do you wish to continue? : "
        		+ "1. Yes"
        		+ "2. No");
        
        conditions = scan.nextInt();
        
        }
        
        System.out.println("Every person existent:\n" + person1.toString() +
        		"\n" +  person2.toString() +
        		"\n" +  person3.toString() +
        		"\n" +  person4.toString());
        
        System.out.println("Grazie ed arrivederci");
        
//////////////////////////////////////////////////////////////////////////////////////////////

        //activity1.deletePerson(person1);

        /*for (Person p:activity1.getSubscribers()) {
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
        
       */  
    }
}
