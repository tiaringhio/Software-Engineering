package esercitazione_1.esercizio_attivita;

import java.util.Objects;
import java.util.Scanner;

public class Attivita {

	    String nome_a;
	    String tipo_a;


	    public Attivita(String nome_a, String tipo_a) {
	        this.nome_a = nome_a;
	        this.tipo_a = tipo_a;
	    }

	    public void setNome(String nome_a) {
	        this.nome_a = nome_a;
	    }
	    public void setCognome(String tipo_a) {
	        this.tipo_a = tipo_a;
	    }
	    public String getNome() {
	        return nome_a;
	    }
	    public String getTipo_a() {
	    	return tipo_a;
	    }
	  
	/*
            System.out.println("Quanti utenti vuoi inserire? ");
            int numero_a = input.nextInt();
            Attivita lista_a[] = new Attivita[numero_a];
            for (int i = 0; i < numero; i++) {
                System.out.println("Nome: ");
                String nome_a = input.next();
                System.out.println("Cognome: ");
                String tipo_a = input.next();


                lista_a[i] = new Attivita(nome_a,tipo_a);
            }
                for (Attivita p : lista_a) {
                    System.out.println(
                    		"nome:" + p.getNome() +
                            "\ncognome:" + p.getTipo_a( ) );
            	}
    */
	    }

