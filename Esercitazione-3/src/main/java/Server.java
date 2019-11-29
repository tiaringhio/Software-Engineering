import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    // Server port
    private static final int SPORT = 4444;
    // List of existent Employees
    private ArrayList<Employee> Employees = new ArrayList<>();

    public void reply(){
        try {
            ServerSocket serverSocket = new ServerSocket(SPORT);
            Socket clientSocket = serverSocket.accept();

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            boolean exit = false;
            Send send;
            while (!exit) {
                send = (Send) objectInputStream.readObject();
                switch (send.command) {
                    case "checkFiscalCode":
                        String fiscalCode = send.getEmployee().getFiscalCode();
                        boolean fiscalResult = checkFiscalCode(fiscalCode);
                        objectOutputStream.writeUTF(String.valueOf(fiscalResult));
                        objectOutputStream.flush();
                        break;
                    case "insertEmployee":
                        Employee employee = send.getEmployee();
                        insertEmployee(employee);
                        break;
                    case "printEmployees":
                        objectOutputStream.writeObject(Employees);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                        break;
                    case "updateEmployee":
                        employee = send.getEmployee();
                        String newName = objectInputStream.readUTF();
                        String newSurname = objectInputStream.readUTF();
                        String newJob = objectInputStream.readUTF();
                        updateEmployee(employee, newName, newSurname, newJob);
                        break;
                    case "exit":
                        exit = true;
                        break;
                }
            }

            serverSocket.close();
            clientSocket.close();

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks that the fiscal code is unique, if it isn't it prints an error
     *
     * @param fiscalCode The fiscal code to check
     * @return a boolean with the result, true if an equal fiscal code is found, false otherwise
     */
    private boolean checkFiscalCode(String fiscalCode) {
        String checkFiscalCode;
        if (!Employees.isEmpty()) {
            for (Employee emp : Employees) {
                checkFiscalCode = emp.getFiscalCode();
                if (checkFiscalCode.equals(fiscalCode)) {
                    System.out.println("Fiscal code must be unique");
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Insert the Employee into the Employees list
     * @param employee The Employee to add
     */
    private void insertEmployee(Employee employee) {
        Employees.add(employee);
    }

    /**
     * Updates an Employee's data, getting the values from user input
     * @param employee Employee to modify
     * @param newName Employee's name
     * @param newSurname Employee's surname
     * @param newJob Employee's job
     */
    private void updateEmployee(Employee employee, String newName, String newSurname, String newJob) {
        if (!Employees.isEmpty()) {
            boolean found = false;
            for (Employee value : Employees) {
                if (value.getId() == employee.getId()) {
                    found = true;
                    value.setName(newName);
                    value.setSurname(newSurname);
                    value.setJob(newJob);
                    System.out.println("Employee modified!");
                    break;
                }
            }
            if (!found) System.out.println("I can't find the employee you're looking for");
        }
        else {
            System.out.println("There are no employees");
        }
    }

    public static void main(final String[] v){
        new Server().reply();
    }
}
