import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

class Leader extends Officer implements Serializable {

    private static Socket socket;

    Leader(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate, String username, String password, Socket socket) throws IOException {
        super(id, name, surname, fiscalCode, workplace, job, startingDate, endingDate, username, password, socket);
        Leader.socket = socket;
    }

    /**
     * Searches employee in a selected workplace, Administrators are not included in the search results
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void searchEmployeeLeader() throws IOException, ClassNotFoundException {
        String searchResult;
        String searchWorkplace;
        Send searchEmployee = new Send("searchEmployeeLeader", null);
        if (objectOutputStream == null){
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        // I check that the Leader is logged
        if (isLogged()) {
            objectOutputStream.writeObject(searchEmployee);
            objectOutputStream.flush();
            /*
            * I get the workplace name via user input then send the value to the server
            * */
            System.out.println("Workplace to search: ");
            searchWorkplace = scanner.next();
            objectOutputStream.writeUTF(searchWorkplace);
            objectOutputStream.flush();
            /*
            * I receive the result of the function executed in the server
            * then i print the list of employees if the result is true ( the server found some employees)
            * or i give an error if the result is false (no employees found)
            * */
            searchResult = objectInputStream.readUTF();
            if (searchResult.equals("true")) {
                if(objectInputStream == null){
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                }
                System.out.println("There are " + objectInputStream.readInt()+ " employees in the selected workplace, here they are:\n" + objectInputStream.readObject().toString());
            }
            else {
                System.out.println("There are no employees in the selected workspace!");
            }
        }
    }
}
