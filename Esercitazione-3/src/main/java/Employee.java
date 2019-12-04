import java.io.Serializable;
/**
 * @author Mattia Ricci
 * @author Riccardo Lo Bue
 */
public class Employee implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String fiscalCode;
    private Workplace workplace;
    private String job;
    private String startingDate;
    private String endingDate;

    /**
     * The constructor
     *
     * @param id Employee's id
     * @param name Employee's name
     * @param surname Employee's surname
     * @param fiscalCode Employee's fiscal code
     * @param workplace Employee's workplace
     * @param job Employee's job
     * @param startingDate Employee's starting date
     * @param endingDate Employee's ending date
     */
    Employee(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.workplace = workplace;
        this.job = job;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    /**
     * id Getter
     * @return Employee's id
     */
    int getId() {
        return id;
    }

    /**
     * id Setter
     *
     * @param id Employee's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * name Getter
     *
     * @return String
     */
    private String getName() {
        return name;
    }

    /**
     * name Setter
     *
     * @param name Employee's name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * surname Getter
     *
     * @return String
     */
    private String getSurname() {
        return surname;
    }

    /**
     * surname's Setter
     *
     * @param surname Employee's surname
     */
    void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * fiscalCode Getter
     *
     * @return String
     */
    String getFiscalCode() {
        return fiscalCode;
    }

    /**
     * fiscalCode Setter
     *
     * @param fiscalCode Employee's fiscalCode
     */
    void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    /**
     * workplace Getter
     *
     * @return Workplace
     */
    Workplace getWorkplace() {
        return workplace;
    }

    /**
     * workplace Setter
     *
     * @param workplace Employee's workplace
     */
    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    /**
     * job Getter
     *
     * @return String
     */
    String getJob() {
        return job;
    }

    /**
     * job Setter
     *
     * @param job Employee's job
     */
    void setJob(String job) {
        this.job = job;
    }

    /**
     * startingDate Getter
     *
     * @return String
     */
    public String getStartingDate() {
        return startingDate;
    }

    /**
     * startingDate Setter
     *
     * @param startingDate Employee's startingDate
     */
    public void setStartingDate(String startingDate) {

        this.startingDate = startingDate;
    }

    /**
     * endingDate Getter
     *
     * @return String
     */
    private String getEndingDate() {
        return endingDate;
    }

    /**
     * endingDate Setter
     *
     * @param endingDate Employee's endingDate
     */
    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    /**
     * Prints Employee's data in a clean way
     *
     * @return String
     */
    @Override
    public String toString() {
        return "(" +
                "Name ='" + getName() + "'" +
                ", Surname ='" + getSurname() + "'" +
                ", Fiscal Code ='" + getFiscalCode() + "'" +
                ", Workplace ='" + workplace.getName() + " - " + workplace.getAddress() +"'" +
                ", Job ='" + getJob() + "'" +
                ", Starting Date ='" + getStartingDate() + "'" +
                ", Ending Date ='" + getEndingDate() + "'" + ")\n";
    }
}
