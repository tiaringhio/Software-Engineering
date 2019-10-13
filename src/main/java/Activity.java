/**
 * Activity has a name and an array of Person registered to that Activity
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Riccardo Lo Bue riccardo.lobue@studenti.unipr.it
 */
class Activity {
    private String name;
    private Person[] subscribers;

    /**
     * Empty constructor for the objectsEmpty
     */
    Activity(){
    }
    /**
     * This is the constructor
     *
     * @param name the name of the Activity
     */
    Activity(String name) {
        this.name = name;
        this.subscribers = new Person[]{};
    }

    /**
     * Gets the Activity's name
     *
     * @return the Activity's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the subscribers
     *
     * @return The subscribers array
     */
    public Person[] getSubscribers() {
        return subscribers;
    }

    /**
     * Sets the subscribers array
     * @param subscribers the subscribers array
     */
    public void setSubscribers(Person[] subscribers) {
        this.subscribers = subscribers;
    }

    /**
     * This method adds a Person to an activity
     *
     * First i check that the Member i want to add is not already present,
     * if he is i stop, else i go on.
     * I then create a new array newMember and i add the Member.
     * I create an array called update that will be the array containing the initial subscribers plus
     * the new one.
     * Array copy:
     *   - I add to the update array the Members already subscribed to the initial array (subscribers)
     *   - Then i add the new Member to update
     *   - Finally i replace the old subscribers array with update
     *
     * @param person the Member i want to add to subscribers
     */
    public void addPerson(Person person) {
        boolean check = false;
        if (getSubscribers().length != 0) {
            for (Person p : getSubscribers()) {
                if (p == person) {
                    check = true;
                    break;
                }
            }
        }
        if (!check){
            Person[] newMember = new Person[]{person};
            Person[] update = new Person[getSubscribers().length + newMember.length];
            System.arraycopy(getSubscribers(), 0, update, 0, getSubscribers().length);
            System.arraycopy(newMember, 0, update, getSubscribers().length, newMember.length);
            this.subscribers = update;
        }
    }

    /**
     * This method does the same thing as the onw above but instead of adding a Member it deletes one.
     *
     * First i have to check if the person i want to delete exists, if it doesn't i stop, else i go on.
     * I create an array called toDelete which will be the same as subscribers but with one less 'spot'
     * I use a counter (added) to keep track of every Member i have to re-add
     * Array copy:
     *   - I have to add every Member i don't want to be deleted to an array so then i can replace the
     *     old array with the new one.
     *
     * @param person the member i want to remove from subscribers
     */
    public void deletePerson(Person person){
        boolean exists = false;
        for (Person p: getSubscribers()) {
            if (p == person) {
                exists = true;
                break;
            }
        }
        if (exists) {
            Person[] toDelete = new Person[getSubscribers().length - 1];
            int added = 0;
            for (int i = 0; i < getSubscribers().length; i++) {
                if (getSubscribers()[i]!= person) {
                    System.arraycopy(getSubscribers(), i, toDelete, added, 1);
                    added ++;
                }
            }
            this.subscribers = toDelete;
        }
    }

    /**
     * This method prints the Activity's data in a more clean way,
     * it overrides the toString method
     *
     * @return
     */
    @Override
    public String toString() {
        return "(" +
                "nome='" + getName() + "'" +
                ", cognome='" + getSubscribers() + ")";
    }
}
