package esercitazione_1.esercizio_attivita;

import java.util.Objects;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
	 
		Scanner input = new Scanner(System.in);
		
		int scelta;
		
        System.out.println("Cosa vuoi fare : "
        		+ "\n1. Aggiungere persone"
        		+ "\n2. Aggiunfere attività");
       scelta = input.nextInt();
        
       char condizione ='y'; 
       
       while (condizione == 'y') {
////////////////////////////////////////////////////////////////////////////////////////
		
        if (scelta == 1)
        
        {
        	
        System.out.println("-----------------------------------------");
		
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
            
            System.out.println("-----------------------------------------");

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
            
        }
            
////////////////////////////////////////////////////////////////////////////////////////
		
        if (scelta == 2)
        
        {
		
        System.out.println("-----------------------------------------");
		   
        System.out.println("Quanti utenti vuoi inserire? ");
        int numero_a = input.nextInt();
        Attivita lista_a[] = new Attivita[numero_a];
        for (int i = 0; i < numero_a; i++) {
                System.out.println("Nome: ");
                String nome_a = input.next();
                System.out.println("Tipo: ");
                String tipo_a = input.next();

       System.out.println("-----------------------------------------");
                
                lista_a[i] = new Attivita(nome_a,tipo_a);
            }
                for (Attivita p : lista_a) {
                    System.out.println(
                    		"nome:" + p.getNome() +
                            "\ncognome:" + p.getTipo_a( ) );
            	}
                
        }
////////////////////////////////////////////////////////////////////////////////////////
        
        System.out.println("-----------------------------------------");
            
        System.out.println("\nCosa vuoi fare : "
        		+ "\n1. Aggiungere persone"
        		+ "\n2. Aggiunfere attività");
       scelta = input.nextInt();
       
        }
		
	}

}