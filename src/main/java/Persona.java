import java.util.Objects;
import java.util.Scanner;

public class Persona {
    String nome;
    String cognome;
    String mail;
    String password;
    String ruolo;

    public Persona(String nome, String cognome, String mail, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
        this.ruolo = ruolo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public static void main(String args[]) {
        Persona iscritti[] = new Persona[5];
        Scanner input = new Scanner(System.in);
        System.out.println("Quanti utenti vuoi inserire? ");
        int numero = input.nextInt();
        for (int i = 1; i <= numero; i++) {
            System.out.println("Nome: ");
            iscritti[i].setNome(input.next());
            System.out.println("Cognome: ");
            iscritti[i].setCognome(input.next());
            System.out.println("Mail: ");
            iscritti[i].setMail(input.next());
            System.out.println("Password: ");
            iscritti[i].setPassword(input.next());
            System.out.println("Ruolo: ");
            iscritti[i].setRuolo(input.next());
        }
    }
}