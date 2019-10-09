package esercitazione_1.esercizio_attivita;
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
    public String getNome() {
        return nome;
    }
    public String getCognome() {
    	return cognome;
    }
    public String getMail() {
    	return mail;
    }
    public String getPassword() {
    	return password;
    }
    public String getRuolo() {
    	return ruolo;
    }
/*
           System.out.println("Quanti utenti vuoi inserire? ");
        int numero = input.nextInt();
        Persona iscritti[] = new Persona[numero];
        for (int i = 0; i < numero; i++) {
            System.out.println("Nome: ");
            String nome = input.next();
            System.out.println("Cognome: ");
            String cognome = input.next();
            System.out.println("Mail: ");
            String mail = input.next();
            System.out.println("Password: ");
            String password = input.next();
            System.out.println("Ruolo: ");
            String ruolo = input.next();

            iscritti[i] = new Persona(nome,cognome,mail,password,ruolo);
        }
            for (Persona p : iscritti) {
                System.out.println(
                		"nome:" + p.getNome() +
                        "\ncognome:" + p.getCognome( ) +
                        "\nmail:" + p.getMail( )+
                        "\npassword:" + p.getPassword( ) +
                        "\nruolo:" + p.getRuolo( ) );             
        	}
*/
    }