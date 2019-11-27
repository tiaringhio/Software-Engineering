import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class Administrator extends Leader {

    public Administrator(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate, String username, String password, Socket socket) throws IOException {
        super(id, name, surname, fiscalCode, workplace, job, startingDate, endingDate, username, password, socket);
    }

    public void searchEmployeeAdmin(){

    }
}
