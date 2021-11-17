package model.item.vehicle.bike;

import model.item.vehicle.Vehicle;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle {

    private boolean isApproved;

    public Motorcycle(String name, BigDecimal volume,boolean isApproved) {
        super(name, volume);
        this.isApproved = isApproved;
        itemExtention.add(this);
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Motorcycle)) return false;
        if (!super.equals(o)) return false;

        Motorcycle that = (Motorcycle) o;

        return isApproved() == that.isApproved();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isApproved() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "isApproved=" + isApproved +
                "} " + super.toString();
    }
}
