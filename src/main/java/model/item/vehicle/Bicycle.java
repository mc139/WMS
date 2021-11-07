package model.item.vehicle;

import java.math.BigDecimal;

public class Bicycle  extends Vehicle{

    private int numberOfGears;

    public Bicycle(String name, BigDecimal volume, int numberOfGears) {
        super(name, volume);
        this.numberOfGears = numberOfGears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bicycle)) return false;
        if (!super.equals(o)) return false;

        Bicycle bicycle = (Bicycle) o;

        return numberOfGears == bicycle.numberOfGears;
    }

    @Override
    public void changeVolume() {
        // todo
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numberOfGears;
        return result;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "numberOfGears=" + numberOfGears +
                "} " + super.toString();
    }
}
