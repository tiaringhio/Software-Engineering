import java.util.Scanner;

public class Officer extends Employee {
    Scanner scanner = new Scanner(System.in);
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
     */
    public Officer(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate) {
        super(id, name, surname, fiscalCode, workplace, job, startingDate, endingDate);
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
     * Checks that the fiscal code is unique
     *
     * @param employee
     * @return
     */
    public boolean checkFiscalCode(Employee employee) {
        String checkFiscalCode;
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
    /**
     * Adds an Employee, checking first if there an employee with the same fiscal code
     */
    public void insertEmployee(Employee employee) {
        if (!checkFiscalCode(employee)) {
            Server.Employees.add(employee);
            System.out.println("Employee added!");
        }
        else {
            System.out.println("There are no employees");
        }
    }

    /**
     * Updates and Employee
     */
    public void updateEmployee(Employee employee){
        String newName;
        String newSurname;
        String newJob;
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
}
