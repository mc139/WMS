package model;

import model.item.Item;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Warehouse implements Serializable {


    private static final long serialVersionUID = -8100311072752220759L;

    private String name;
    private List<Room> rooms= new ArrayList<>();
    public static List<Warehouse> warehouseExtention = new ArrayList<>();

    public static List<Room> getListOfEmptyRooms(Warehouse warehouse) {
        return Optional.ofNullable(warehouse.getRooms()).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .filter(room -> room.getPerson() == null)
                .collect(Collectors.toList());
    }

    public List<Person> getPeople() {
        return Optional.ofNullable(getRooms()).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .map(Room::getPerson)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;

        Warehouse warehouse = (Warehouse) o;

        if (getName() != null ? !getName().equals(warehouse.getName()) : warehouse.getName() != null) return false;
        return getRooms() != null ? getRooms().equals(warehouse.getRooms()) : warehouse.getRooms() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getRooms() != null ? getRooms().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
