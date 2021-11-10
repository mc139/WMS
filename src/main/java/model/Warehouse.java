package model;

import model.item.Item;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private String name;
    private List<Room> rooms= new ArrayList<>();
    public static List<Warehouse> warehouseExtention = new ArrayList<>();

    public void addRoom(Room room){
        rooms.add(room);
        room.setWarehouse(this);
    }

    public Warehouse(String name) {
        this.name = name;
        warehouseExtention.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public BigDecimal getVolume() {
        return rooms.stream()
                .map(Room::getUsageArea)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }


}
