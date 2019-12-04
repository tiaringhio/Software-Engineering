import java.io.Serializable;
/**
 * @author Mattia Ricci
 * @author Riicardo Lo Bue
 */
public class Send implements Serializable {
    String command;
    private Employee employee;

    /**
     * The constructor
     *
     * @param command command to send to the server
     * @param employee employee to send to the server
     */
    Send(String command, Employee employee) {
        this.command = command;
        this.employee = employee;
    }

    /**
     * command Getter
     *
     * @return The command
     */
    public String getCommand() {
        return command;
    }

    /**
     * command Setter
     *
     * @param command The command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * employee's Getter
     *
     * @return The employee
     */
    Employee getEmployee() {
        return employee;
    }

    /**
     * employee's Setter
     *
     * @param employee the employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
