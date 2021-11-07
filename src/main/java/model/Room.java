package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Room {

    private String name;
    private Person person;
    private boolean isOutOfService = false; // jesli jest true to nie mozna ustawic osoby
    private int id;
    private BigDecimal usageArea;
    private double width;
    private double height;
    private double length;
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private LocalDate dateOfRenting;

    public Room(boolean isOutOfService, double width, double height, double length) {
        this.isOutOfService = isOutOfService;
        this.width = width;
        this.height = height;
        this.length = length;
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

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (isOutOfService() != room.isOutOfService()) return false;
        if (getId() != room.getId()) return false;
        if (Double.compare(room.width, width) != 0) return false;
        if (Double.compare(room.height, height) != 0) return false;
        if (Double.compare(room.length, length) != 0) return false;
        if (getName() != null ? !getName().equals(room.getName()) : room.getName() != null) return false;
        if (getPerson() != null ? !getPerson().equals(room.getPerson()) : room.getPerson() != null) return false;
        return getUsageArea() != null ? getUsageArea().equals(room.getUsageArea()) : room.getUsageArea() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        result = 31 * result + (isOutOfService() ? 1 : 0);
        result = 31 * result + getId();
        result = 31 * result + (getUsageArea() != null ? getUsageArea().hashCode() : 0);
        temp = Double.doubleToLongBits(width);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(length);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
                ", width=" + width +
                ", height=" + height +
                ", length=" + length +
                '}';
    }

}
