import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    // Server port
    private static final int SPORT = 4444;

    ArrayList<Employee> Employees = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void reply(){
        try {
            // Socket creation
            ServerSocket serverSocket = new ServerSocket(SPORT);
            Socket clientSocket = serverSocket.accept();

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            //PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
            //Scanner scanner = new Scanner(clientSocket.getInputStream());
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

    public boolean checkFiscalCode(String fiscalCode) {
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

    public void insertEmployee(Employee employee) {
        Employees.add(employee);
    }

 /*   public static ArrayList<Employee> getEmployees(){
        return Employees;
    }*/
    public static void main(final String[] v){
        new Server().reply();
    }
}
