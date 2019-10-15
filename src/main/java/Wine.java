public class Wine {
    String name;
    int year;
    String technical_notes;
    String vine;
    int produced;

    /**
     *
     * @param name
     * @param year
     * @param technical_notes
     * @param vine
     * @param produced
     */
    public Wine(String name, int year, String technical_notes, String vine, int produced) {
        this.name = name;
        this.year = year;
        this.technical_notes = technical_notes;
        this.vine = vine;
        this.produced = produced;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTechnical_notes() {
        return technical_notes;
    }

    public void setTechnical_notes(String technical_notes) {
        this.technical_notes = technical_notes;
    }

    public String getVine() {
        return vine;
    }

    public void setVine(String vine) {
        this.vine = vine;
    }

    public int getProduced() {
        return produced;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }
}
