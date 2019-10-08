class Attivita extends Persona {

    private static String nome_attivita;
    static char condizione = 'y';

    Attivita(String nome, String cognome, String mail, String password, String ruolo) {
        super(nome, cognome, mail, password, ruolo);
    }

    static void Aggiungi() {
        while (condizione == 'y') {
            System.out.println("Nome dell'attività? " + nome_attivita);
            System.out.println("Vuoi inserire un'altra attività? (y/n)" + condizione);
        }
    }
}
