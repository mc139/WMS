package model.item.vehicle;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle{

    private boolean isApproved;

    public Motorcycle(String name, BigDecimal volume,boolean isApproved) {
        super(name, volume);
        this.isApproved = isApproved;
    }

}
