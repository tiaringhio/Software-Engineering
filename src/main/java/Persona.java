public class Persona {
    private String nome;
    private String cognome;
    private String mail;
    private String password;
    private String ruolo;
    private char condizione = 'y';


    static int id = 1;
    private static Persona[] iscritti = new Persona[50];

    public Persona(String nome, String cognome, String mail, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
        this.ruolo = ruolo;
    }

    public void Registrazione() {
        while (condizione == 'y') {
            System.out.println("Nome: " + nome);
            System.out.println("Cognome: " + cognome);
            System.out.println("Mail: " + mail);
            System.out.println("Password: " + password);
            System.out.println("Ruolo: " + ruolo);
            System.out.println("Vuoi inserire un altro utente? (y/n)" + condizione);
        }
    }
}
