import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Officer extends Employee implements Serializable {

    Socket socket;
    //BufferedInputStream bufferedInputStream;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Scanner scanner = new Scanner(System.in);
    private boolean logged;
    String username;
    String password;

    /**
     * The constructor
     *
     * @param id
     * @param name
     * @param surname
     * @param fiscalCode
     * @param workplace
     * @param job
     * @param startingDate
     * @param endingDate
     * @param username
     * @param password
     */
    public Officer(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate, String username, String password, Socket socket) throws IOException {
        super(id, name, surname, fiscalCode, workplace, job, startingDate, endingDate);
        this.username = username;
        this.password = password;
        this.socket = socket;

        //bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        //objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        //objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * logged Getter
     *
     * @return boolean
     */
    public boolean isLogged() {
        return logged;
    }

    /**
     * logged Setter
     *
     * @param logged
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    /**
     * username Getter
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * username Setter
     *
     * @param username Officer's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * password Getter
     *
     * @return String
     */
    public String getPassword() {
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
    public void login(){
        String logUser;
        String logPassword;
        System.out.println("Insert user and password\n");
        logUser = scanner.nextLine();
        logPassword = scanner.nextLine();
        while (!isLogged()){
            if (this.username.equals(logUser) && this.password.equals(logPassword)) {
                System.out.println("Welcome back!");
                setLogged(true);
            }
            else {
                System.out.println("Woops, wrong data!");
                System.out.println("Insert user and password");
                logUser = scanner.next();
                logPassword = scanner.next();
            }
        }
    }

    /**
     * Checks that the fiscal code is unique, the Officer must be logged
     *
     * @param employee
     * @return
     */
    public boolean checkFiscalCode(Employee employee) {
        String checkFiscalCode;
        if (isLogged()) {
            if (!Server.Employees.isEmpty()) {
                for (Employee emp : Server.Employees) {
                    checkFiscalCode = emp.getFiscalCode();
                    if (checkFiscalCode.equals(employee.getFiscalCode())) {
                        System.out.println("Fiscal code must me unique");
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
        else {
            System.out.println("You have to login first!");
        }
        return false;
    }

    /**
     * Adds an Employee, checking first if there an employee with the same fiscal code
     */
    public void insertEmployee(Employee employee) throws IOException {
        Send send = new Send("checkFiscalCode", employee);
        String serverResult;
        if (objectOutputStream == null){
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        if (isLogged()) {
            objectOutputStream.writeObject(send);
            objectOutputStream.flush();
            if(objectInputStream == null){
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            }
            serverResult = objectInputStream.readUTF();
            if (serverResult.equals("false")) {
                objectOutputStream.writeUTF("insertEmployee");
                objectOutputStream.flush();
                objectOutputStream.writeObject(employee);
                objectOutputStream.flush();
                System.out.println("Employee added!\n" + employee.toString());
                }
            }
        else {
            System.out.println("You have to login first!");
        }
    }

    /**
     * Updates and Employee, gets the new data from user input
     */
    public void updateEmployee(Employee employee){
        String newName;
        String newSurname;
        String newJob;
        if (isLogged()) {
            if (!Server.Employees.isEmpty()){
                int searchId;
                System.out.println("Employee ID: ");
                searchId = scanner.nextInt();
                boolean found = false;
                for (int i = 0; i <= Server.Employees.size(); i++){
                    if (searchId == Server.Employees.get(i).getId()) {
                        found = true;
                        System.out.println("New name: ");
                        newName = scanner.next();
                        employee.setName(newName);
                        System.out.println("New surname: ");
                        newSurname = scanner.next();
                        employee.setSurname(newSurname);
                        System.out.println("New job: ");
                        newJob = scanner.next();
                        employee.setJob(newJob);
                        break;
                    }

                }
                if (!found) System.out.println("I can't find the employee you're looking for");
            }
            else {
                System.out.println("There are no employees");
            }
        }
        else {
            System.out.println("You have to login first!");
        }
    }
}
