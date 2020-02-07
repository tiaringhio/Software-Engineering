package wineStore;

/**
 * This class i used to model the tables used in the EmployeeController to show the admin the data from the DB
 * through the GUI
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
public class ModelTable {

    String id;
    String name;
    String quantity;

    /**
     * The constructor
     * @param id id column
     * @param name name column
     * @param quantity quantity column
     */
    public ModelTable(String id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * getter method for id
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * setter method for id
     *
     * @param id id column
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getter method for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter method for name
     *
     * @param name name column
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter method for quantity
     *
     * @return quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * setter method for quantity
     *
     * @param quantity quantity column
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
