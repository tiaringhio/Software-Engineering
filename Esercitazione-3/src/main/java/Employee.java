import java.util.Date;

/**
 * Employee class
 */
public class Employee {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String name;
    String surname;
    String fiscalCode;
    Workplace workplace;
    String job;
    String startingDate;
    String endingDate;

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
    public Employee(int id, String name, String surname, String fiscalCode, Workplace workplace, String job, String startingDate, String endingDate) {
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
     * name Getter
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * name Setter
     *
     * @param name Employee's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * surname Getter
     *
     * @return String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * surname's Setter
     *
     * @param surname Employee's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * fiscalCode Getter
     *
     * @return String
     */
    public String getFiscalCode() {
        return fiscalCode;
    }

    /**
     * fiscalCode Setter
     *
     * @param fiscalCode Employee's fiscalCode
     */
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    /**
     * workplace Getter
     *
     * @return Workplace
     */
    public Workplace getWorkplace() {
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
    public String getJob() {
        return job;
    }

    /**
     * job Setter
     *
     * @param job Employee's job
     */
    public void setJob(String job) {
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
    public String getEndingDate() {
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
                ", Ending Date ='" + getEndingDate() + "'" + ")";
    }
}
