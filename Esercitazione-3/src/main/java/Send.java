import java.io.Serializable;

public class Send implements Serializable {
    String command;
    Employee employee;

    public Send(String command, Employee employee) {
        this.command = command;
        this.employee = employee;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
