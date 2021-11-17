package model.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Room;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Item implements Serializable {

    private static final long serialVersionUID = 2801183849553406104L;

    private String name;
    private BigDecimal volume;
    private Room room;
    public static List<Item> itemExtention = new ArrayList<>();

    public Item(String name, BigDecimal volume) {
        this.name = name;
        this.volume = volume;
        itemExtention.add(this);
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void changeVolume() {
        this.volume.divide(new BigDecimal(2));
    }
}
