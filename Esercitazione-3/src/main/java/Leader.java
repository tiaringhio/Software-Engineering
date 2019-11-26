import java.util.Date;

public class Leader extends Employee {
    String username;
    String password;

    /**
     * The constructor
     *
     * @param name
     * @param surname
     * @param fiscalCode
     * @param workplace
     * @param job
     * @param startingDate
     * @param endingDate
     */
    public Leader(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate) {
        super(id, name, surname, fiscalCode, workplace, job, startingDate, endingDate);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void searchEmployee(){

    }
}
