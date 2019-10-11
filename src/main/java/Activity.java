class Activity {
    private String name;
    private Person[] subscribers;

    Activity(){
    }

    Activity(String name) {
        this.name = name;
        this.subscribers = new Person[]{};
    }

    public String getName() {
        return name;
    }

    public Person[] getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Person[] subscribers) {
        this.subscribers = subscribers;
    }
    /*
    * Controllo che il membro non sia già presente, se lo è mi fermo, altrimenti:
    * Creo un nuovo array newMember e ci aggiungo il membro da aggiungere.
    * Creo un array update che sarà
    * l'array contenente gli iscritti iniziali più il nuovo membro.
    * Primo array copy: aggiungo ad update i membri attualmente iscritti in iscritti_attivita
    * Secondo array copy: aggiungo il nuovo membro ad update
    * sostituisco il mio array iniziale con update
    *
    * @param persona il membro da aggiungere agli iscritti
    *
    * */
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

    /*
    *
    * Controllo che il membro sia presente
    *
    * @param persona il membro da togliere dagli iscritti
    *
    * */

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

    @Override
    public String toString() {
        return "(" +
                "nome='" + getName() + "'" +
                ", cognome='" + getSubscribers() + ")";
    }
}
