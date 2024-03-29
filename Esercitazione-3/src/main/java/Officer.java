import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * @author Mattia Ricci
 * @author Riccardo Lo Bue
 */
public class Officer extends Employee implements Serializable {

    private static Socket socket;
    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;
    static Scanner scanner = new Scanner(System.in);
    private boolean logged;
    private String username;
    private String password;

    /**
     * The constructor
     *
     * @param id Officer's id
     * @param name Officer's name
     * @param surname Officer's surname
     * @param fiscalCode Officer's fiscal code
     * @param workplace Officer's workplace
     * @param job Officer's job
     * @param startingDate Officer's starting date
     * @param endingDate  Officer's ending date
     * @param username Officer's username
     * @param password Officer's password
     */
    Officer(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate, String username, String password, Socket socket) throws IOException {
        super(id, name, surname, fiscalCode, workplace, job, startingDate, endingDate);
        this.username = username;
        this.password = password;
        Officer.socket = socket;
    }

    /**
     * logged Getter
     *
     * @return boolean
     */
    boolean isLogged() {
        return logged;
    }

    /**
     * logged Setter
     *
     * @param logged
     */
    private void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * username Getter
     *
     * @return String
     */
    String getUsername() {
        return username;
    }

    /**
     * username Setter
     *
     * @param username Officer's username
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * password Getter
     *
     * @return String
     */
    String getPassword() {
        return password;
    }

    /**
     * password Setter
     *
     * @param password Officer's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Logs the Officer in, if the data input is wrong it gives the Officer another chance
     */
    void login() throws IOException {
        Send sendLogin = new Send("login", this);
        String logUser;
        String logPassword;
        String loginResult = "false";
        int counter = 0;

        if (objectOutputStream == null){
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        if (objectInputStream == null) {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }

        do {
            if(counter > 0)
                System.out.println("Woops, wrong data! Try again!\n");

            objectOutputStream.writeObject(sendLogin);
            objectOutputStream.flush();
            System.out.println("Insert user:");
            logUser = scanner.nextLine();
            objectOutputStream.writeUTF(logUser);
            objectOutputStream.flush();
            System.out.println("Insert password:");
            logPassword = scanner.nextLine();
            objectOutputStream.writeUTF(logPassword);
            objectOutputStream.flush();
            loginResult = objectInputStream.readUTF();

            counter++;
        }
        while (loginResult.equals("false"));

        System.out.println("Welcome back!");
        setLogged(true);
    }

    /**
     * Adds an Employee, checking first if there an employee with the same fiscal code
     *
     * @param employee The Employee to add
     * @throws IOException
     */
    void insertEmployee(Employee employee) throws IOException {
        Send sendFiscal = new Send("checkFiscalCode", employee);
        Send sendInsert = new Send("insertEmployee", employee);
        String serverResult;
        if (objectOutputStream == null){
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        // I check that the Officer is logged
        if (isLogged()) {
            objectOutputStream.writeObject(sendFiscal);
            objectOutputStream.flush();
            if(objectInputStream == null){
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            }
            System.out.println("Adding employee to the list...\n");
            serverResult = objectInputStream.readUTF();
            if (serverResult.equals("false")) {
                objectOutputStream.writeObject(sendInsert);
                objectOutputStream.flush();
                System.out.println("Employee added!\n" + employee.toString());
            }
            else {
                System.out.println("Fiscal code must be unique");
            }
        }
        else {
            System.out.println("You have to login first!");
        }
    }

    /**
     * Updates and Employee, gets the new data from user input
     *
     * @param employee The Employee to modify
     * @throws IOException
     */
    void updateEmployee(Employee employee) throws IOException {
        String newName;
        String newSurname;
        String newJob;
        Send sendUpdate = new Send("updateEmployee", employee);
        if (objectOutputStream == null) {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        // I check that the Officer is logged
        if (isLogged()) {
            objectOutputStream.writeObject(sendUpdate);
            /*
             * I get the name via user input the i send it to the Server
             */
            System.out.println("New name: ");
            newName = scanner.nextLine();
            objectOutputStream.writeUTF(newName);
            /*
             * I get the surname via user input the i send it to the Server
             */
            System.out.println("New surname: ");
            newSurname = scanner.nextLine();
            objectOutputStream.writeUTF(newSurname);
            /*
             * I get the job via user input the i send it to the Server
             */
            System.out.println("New job: ");
            newJob = scanner.nextLine();
            objectOutputStream.writeUTF(newJob);
            System.out.println("Updating employee...\n");
        }
        else {
            System.out.println("You have to login first!");
        }
    }

    /**
     * This method prints the Employees list defined in Server
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void printEmployees() throws IOException, ClassNotFoundException {
        Send sendPrint = new Send("printEmployees", null);
        objectOutputStream.writeObject(sendPrint);
        System.out.println("Getting the existent employees...\n");
        System.out.println("Here are the existent employees:\n" + objectInputStream.readObject().toString());
    }
}
