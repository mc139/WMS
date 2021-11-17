package model.item.vehicle.car;

import model.item.vehicle.Vehicle;

import java.math.BigDecimal;

public class Car extends Vehicle {

    private CarType carType;

    public Car(String name, BigDecimal volume, CarType carType) {
        super(name, volume);
        this.carType = carType;
        itemExtention.add(this);
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
