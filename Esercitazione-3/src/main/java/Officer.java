import java.util.Date;

public class Officer extends Employee {
    String username;
    String password;

    public Officer(String name, String surname, String fiscalCode, Workplace workplace, String job, Date startingDate, Date endingDate) {
        super(name, surname, fiscalCode, workplace, job, startingDate, endingDate);
    }

    public Officer(String name, String surname, String fiscalCode, Workplace workplace, String job, Date startingDate, Date endingDate, String username, String password) {
        super(name, surname, fiscalCode, workplace, job, startingDate, endingDate);
        this.username = username;
        this.password = password;
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

    public void insertEmployee(){

    }

    public void updateEmployee(){

    }
}
