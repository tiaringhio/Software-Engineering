/**
 * Every method defined in the classes is shown here
 *
 * @author Mattia Ricci mattia.ricci1@studenti.unipr.it
 * @author Gaspare Riccardo Lo Bue gasparericcardo.lobue@studenti.unipr.it
 */
public class Wine {
    String name;
    int year;
    String technical_notes;
    String vine;
    int produced;

    /**
     * The constructor
     *
     * @param name Wine's name
     * @param year Wine's year
     * @param technical_notes Wine's notes
     * @param vine Wine's vine
     * @param produced Wine's quantity produced
     */
    public Wine(String name, int year, String technical_notes, String vine, int produced) {
        this.name = name;
        this.year = year;
        this.technical_notes = technical_notes;
        this.vine = vine;
        this.produced = produced;
    }

    /**
     * Gets the the Wine's name
     *
     * @return String Wine's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the the Wine's name
     *
     * @param name Wine's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the the Wine's year
     *
     * @return int Wine's year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the the Wine's year
     *
     * @param year Wine's year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the the Wine's technical notes
     * @return String Wine's technical notes
     */
    public String getTechnical_notes() {
        return technical_notes;
    }

    /**
     * Sets the the Wine's technical notes
     *
     * @param technical_notes Wine's technical notes
     */
    public void setTechnical_notes(String technical_notes) {
        this.technical_notes = technical_notes;
    }

    /**
     * Gets the the Wine's vine
     *
     * @return String Wine's vine
     */
    public String getVine() {
        return vine;
    }

    /**
     * Sets the the Wine's vine
     *
     * @param vine Wine's vine
     */
    public void setVine(String vine) {
        this.vine = vine;
    }

    /**
     * Gets the the Wine's production quantity
     *
     * @return Wine's production quantity
     */
    public int getProduced() {
        return produced;
    }

    /**
     * Sets the the Wine's production quantity
     * @param produced Wine's production quantity
     */
    public void setProduced(int produced) {
        this.produced = produced;
    }

    /**
     * Prints the Wines's data in a clean way.
     * It overrides the toString method
     *
     * @return String Wine's data
     */
    @Override
    public String toString() {
        return "(" +
                "Name = '" + getName() + "'" +
                ", Year = '" + getYear() + "'" +
                ", Notes = '" + getTechnical_notes() + "'" +
                ", Vine = '" + getVine() + "'" +
                ", Quantity = '" + getProduced() + "'" + ")";
    }

}
