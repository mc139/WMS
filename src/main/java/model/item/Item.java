package model.item;

import java.math.BigDecimal;

public class Item {

    private String name;
    private BigDecimal volume;

    public Item(String name, BigDecimal volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
