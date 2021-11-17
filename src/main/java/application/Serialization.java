package application;

import dto.WarehouseToTextFileDto;
import model.Address;
import model.Person;
import model.Room;
import model.Warehouse;
import model.item.Item;
import model.item.vehicle.bike.Bicycle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static model.Warehouse.warehouseExtention;
import static model.item.Item.itemExtention;

public class Serialization {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse("warehouse 1");
        Person person1 = new Person("John","Smith","41300501665",new Address("Test Street","London","13"), LocalDate.of(1990,11,11));
        Person person2 = new Person("Ian","Blown","62710170436",new Address("Test Street","London","13"),LocalDate.of(1990,11,11));
        Person person3 = new Person("Mike","Kovalsky","62710170436",new Address("Test Street","London","13"),LocalDate.of(1990,11,11));
        Person person4 = new Person("Susan","Bloomberg","62710170436",new Address("Test Street","London","13"),LocalDate.of(1990,11,11));
        Person person5 = new Person("Peter","Jackson","62710170436",new Address("Test Street","London","13"),LocalDate.of(1990,11,11));
        Room room1 = new Room(false,5,5,5);
        Room room2 = new Room(false,51,25,5);
        Room room3 = new Room(false,512,52,5);
        Room room4 = new Room(false,53,5,5);
        Room room5 = new Room(false,53,5,5);
        Room room6 = new Room(false,5,5,5);
        Room room7= new Room(false,52,53,5);
        Room room8= new Room(false,52,52,5);
        Room room9= new Room(false,25,52,5);
        Room room10= new Room(false,25,25,25);

        room1.rentToPerson(person2, LocalDate.of(2010,11,1));
        room2.rentToPerson(person1, LocalDate.of(1994,11,10));
        room3.rentToPerson(person3, LocalDate.of(1994,11,10));
        room4.rentToPerson(person5, LocalDate.of(1994,11,10));
        room5.rentToPerson(person1, LocalDate.of(1994,11,10));
        room6.rentToPerson(person1, LocalDate.of(1994,11,10));
        room7.rentToPerson(person4, LocalDate.of(1994,11,5));
        room8.rentToPerson(person1, LocalDate.of(1994,11,10));

        Item item1 = new Item("test item1", BigDecimal.valueOf(12));
        Item item2 = new Item("test item2", BigDecimal.valueOf(12));
        Item item3 = new Item("test item3", BigDecimal.valueOf(88));
        Item item4 = new Item("test item4", BigDecimal.valueOf(124));
        Item item5 = new Item("test item5", BigDecimal.valueOf(125));
        Item item6 = new Item("test item6", BigDecimal.valueOf(128));

        Bicycle bicycle1 = new Bicycle("bicycle1",BigDecimal.valueOf(10),12);
        warehouse.addRoom(room1);
        warehouse.addRoom(room2);
        warehouse.addRoom(room3);
        warehouse.addRoom(room4);
        warehouse.addRoom(room5);
        warehouse.addRoom(room6);
        warehouse.addRoom(room7);
        warehouse.addRoom(room8);

        warehouse.addRoom(room9);
        warehouse.addRoom(room10);
        room2.addItem(bicycle1);
        room7.addItem(item4);
        room7.addItem(item5);
        room7.addItem(item6);

        //metoda ponizej zapisuje plik TXT  ->
        WarehouseToTextFileDto.process(Warehouse.warehouseExtention);

        List<Warehouse> warehouses = warehouseExtention;
        List<Item> items = itemExtention;
        List<Room> rooms = Room.roomExtension;

        //room 1-10 person 1-5
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("objects1.bin"))){
                oos.writeObject(warehouses);
                oos.writeObject(items);
                oos.writeObject(rooms);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

