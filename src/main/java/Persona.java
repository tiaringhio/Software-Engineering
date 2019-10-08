import java.util.Objects;
import java.util.Scanner;

import static com.sun.org.apache.bcel.internal.classfile.Utility.printArray;

public class Persona {
    static String nome;
    static String cognome;
    static String mail;
    static String password;
    static String ruolo;
    static Persona[] iscritti = new Persona[5];

    Persona(String nome, String cognome, String mail, String password, String ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
        this.ruolo = ruolo;
    }

    public void Registrazione() {
        Scanner input = new Scanner(System.in);
        System.out.println("Quanti utenti vuoi inserire? ");
        int numero = input.nextInt();
        for (int i = 1; i <= numero; i++)  {
            System.out.println("Nome: ");
            nome = input.next();
            System.out.println("Cognome: ");
            cognome = input.next();
            System.out.println("Mail: ");
            mail = input.next();
            System.out.println("Password: ");
            password = input.next();
            System.out.println("Ruolo: ");
            ruolo = input.next();
            //Persona persona = new Persona(nome, cognome, mail, password, ruolo);
        }
    }
    public static void main(String[] args) {
        Persona persona = new Persona(nome, cognome, mail, password, ruolo);
        persona.Registrazione();
    }
}
