package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(exclude = "rooms")
@FieldNameConstants
@ToString
public class Warehouse implements Serializable {

    private static final long serialVersionUID = -8100311072752220759L;

    private String name;
    private List<Room> rooms = new ArrayList<>();
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

    public void addRoom(Room room) {
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
