package model.item;

import model.Room;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Room getRoom() {
        return room;
    }

    public void changeVolume() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        if (getName() != null ? !getName().equals(item.getName()) : item.getName() != null) return false;
        return volume != null ? volume.equals(item.volume) : item.volume == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", volume=" + volume  + " m3" +
                '}';
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
