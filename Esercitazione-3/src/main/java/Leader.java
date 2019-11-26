import java.util.Date;

public class Leader extends Officer {

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
    public Leader(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate, String username, String password) {
        super(id, name, surname, fiscalCode, workplace, job, startingDate, endingDate, username, password);
    }

    /**
     * Searches employee in a selected workplace
     */
    public void searchEmployee() {
        int counter = 0;
        String searchWorkplace;
        System.out.println("Workplace to search: ");
        searchWorkplace = scanner.next();
        if (isLogged()) {
            for (Employee employee : Server.Employees) {
                if (employee.getWorkplace().getName().equals(searchWorkplace)) {
                    System.out.println("Here's every employee in that workplace:" + Server.Employees.toString());
                }
            }
        }
    }
}
