package model.item.vehicle;

import model.item.Item;

import java.math.BigDecimal;

public abstract class Vehicle extends Item {

    public Vehicle(String name, BigDecimal volume) {
        super(name, volume);
    }

    @Override
    public void changeVolume() {

    }
}
