import java.util.Date;

public class Employee {
    String name;
    String surname;
    String fiscalCode; // TODO generate fiscal code
    Workplace workplace;
    String job;
    Date startingDate;
    Date endingDate;

    public Employee(String name, String surname, String fiscalCode, Workplace workplace, String job, Date startingDate, Date endingDate) {
        this.name = name;
        this.surname = surname;
        this.fiscalCode = fiscalCode;
        this.workplace = workplace;
        this.job = job;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    // Workplace is inherited from Workplace class
    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }
}