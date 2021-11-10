package application;

import dto.WarehouseToFileDto;
import model.Address;
import model.Person;
import model.Room;
import model.Warehouse;
import model.item.Item;
import model.item.vehicle.bike.Bicycle;
import model.item.vehicle.car.Car;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {


        Warehouse warehouse = new Warehouse("warehouse 1");
        Warehouse warehouse1 = new Warehouse("warehouse 2");
        Person person1 = new Person("Test name1","Test surname2","41300501665",new Address("Test Street","London","13"),LocalDate.of(1990,11,11));
        Person person2 = new Person("Test name","Test surname","62710170436",new Address("Test Street","London","13"),LocalDate.of(1990,11,11));
        Room room1 = new Room(false,5,5,5);
        Room room2 = new Room(false,5,5,5);
        Room room3 = new Room(false,5,5,5);
        Room room4 = new Room(false,5,5,5);
        Room room5 = new Room(false,5,5,5);
        Room room6 = new Room(false,5,5,5);

        room1.rentToPerson(person2, LocalDate.of(2010,11,1));
        room2.rentToPerson(person1, LocalDate.of(1994,11,10));
        room3.rentToPerson(person1, LocalDate.of(1994,11,10));
        room3.rentToPerson(person1, LocalDate.of(1994,11,10));
        room4.rentToPerson(person1, LocalDate.of(1994,11,10));
        room5.rentToPerson(person1, LocalDate.of(1994,11,10));
        room6.rentToPerson(person1, LocalDate.of(1994,11,10));

        Item item1 = new Item("test item1", BigDecimal.valueOf(12));
        Item item2 = new Item("test item2", BigDecimal.valueOf(12));
        Item item3 = new Item("test item3", BigDecimal.valueOf(88));
        Item item4 = new Item("test item4", BigDecimal.valueOf(124));
        Item item5 = new Item("test item5", BigDecimal.valueOf(125));
        Item item6 = new Item("test item6", BigDecimal.valueOf(128));

//        System.out.println(room1);
//        System.out.println(room2);
//        System.out.println(room3);
//        System.out.println(room4);
//        System.out.println(room5);
//        System.out.println(room6);


        System.out.println("==========================================================");
        System.out.println(room1.getItems());
        room1.addItem(item1);
        System.out.println(room1.getAreaLeftInTheRoom());
        room1.addItem(item3);
        System.out.println(room1.getAreaLeftInTheRoom());

        System.out.println("==========================================================");

        Bicycle bicycle1 = new Bicycle("bicycle1",BigDecimal.valueOf(10),12);
        System.out.println(bicycle1.getVolume());
        bicycle1.changeVolume();
        System.out.println(bicycle1.getVolume());
        room3.addItem(item4);
        System.out.println(room3.getItems());
        room3.removeItem(item4);
        System.out.println(room3.getItems());

        warehouse.addRoom(room1);
        warehouse.addRoom(room2);
        warehouse.addRoom(room3);
        warehouse1.addRoom(room4);
        warehouse1.addRoom(room5);
        warehouse1.addRoom(room6);
        room2.addItem(bicycle1);
        WarehouseToFileDto.process(Warehouse.warehouseExtention);
        System.out.println(warehouse.getRooms());

    }
}
