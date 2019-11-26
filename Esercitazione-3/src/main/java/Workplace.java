import java.util.Arrays;

public class Workplace {
    String name;
    String address;

    public Workplace(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Prints Workpl
     * @return
     */
    @Override
    public String toString() {
        return "(" +
                "Name = '" + getName() + "'" +
                ", Address = '" + getAddress() + "'" + ")";
    }
}