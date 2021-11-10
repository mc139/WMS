package model;

import exception.TooManyThingsException;
import model.item.Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Room {

    private Warehouse warehouse;
    private String name;
    private Person person;
    private boolean isOutOfService;
    private int id;
    private BigDecimal usageArea;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private List<Item> items = new ArrayList<>();
    private LocalDate dateOfRenting;

    public Room(boolean isOutOfService, double width, double height, double length) {
        this.isOutOfService = isOutOfService;
        this.usageArea = BigDecimal.valueOf(width * height * length);
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = "Room: " + ID_GENERATOR.get();
    }

    public void rentToPerson(Person person,LocalDate dateOfRenting) {
        if (!this.isOutOfService) {
            if(person.getRooms().size() == 0 || person.getRooms().isEmpty()){
                person.setFirstRentDate(dateOfRenting);
            }
            this.setPerson(person);
            person.getRooms().add(this);
            this.dateOfRenting = dateOfRenting;
        } else {
            throw new IllegalStateException("This room is OutOfService " + this.getName());
        }
    }

    public BigDecimal getAreaLeftInTheRoom(){
      return usageArea.subtract(Optional.of(items).orElse(Collections.emptyList()).stream()
                .map(Item::getVolume)
              .reduce(BigDecimal.ZERO,BigDecimal::add));
    }

    public void addItem(Item item){
            if(this.getAreaLeftInTheRoom().compareTo(item.getVolume()) >= 0){
                items.add(item);
                item.setRoom(this);
            } else {
                throw new TooManyThingsException("Too many things in: " + this.getName());
            }

    }
    public void removeItem(Item item){
        items.remove(item);

    }

    public List<Item> getItems() {
        return items;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDate getDateOfRenting() {
        return dateOfRenting;
    }

    public String getName() {
        return name;
    }

    public Person getPerson() {
        return person;
    }

    public boolean isOutOfService() {
        return isOutOfService;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getUsageArea() {
        return usageArea;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setOutOfService(boolean outOfService) {
        isOutOfService = outOfService;
    }

    public void setUsageArea(BigDecimal usageArea) {
        this.usageArea = usageArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (isOutOfService() != room.isOutOfService()) return false;
        if (getId() != room.getId()) return false;
        if (getWarehouse() != null ? !getWarehouse().equals(room.getWarehouse()) : room.getWarehouse() != null)
            return false;
        if (getName() != null ? !getName().equals(room.getName()) : room.getName() != null) return false;
        if (getPerson() != null ? !getPerson().equals(room.getPerson()) : room.getPerson() != null) return false;
        if (getUsageArea() != null ? !getUsageArea().equals(room.getUsageArea()) : room.getUsageArea() != null)
            return false;
        if (getItems() != null ? !getItems().equals(room.getItems()) : room.getItems() != null) return false;
        return getDateOfRenting() != null ? getDateOfRenting().equals(room.getDateOfRenting()) : room.getDateOfRenting() == null;
    }

    @Override
    public int hashCode() {
        int result = getWarehouse() != null ? getWarehouse().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        result = 31 * result + (isOutOfService() ? 1 : 0);
        result = 31 * result + getId();
        result = 31 * result + (getUsageArea() != null ? getUsageArea().hashCode() : 0);
        result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
        result = 31 * result + (getDateOfRenting() != null ? getDateOfRenting().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", person=" + person +
                ", isOutOfService=" + isOutOfService +
                ", id=" + id +
                ", usageArea=" + usageArea +" m3" +
                '}';
    }


}
