package application;

import model.Address;
import model.Person;
import model.Room;

import java.math.BigInteger;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

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

        System.out.println(room1);
        System.out.println(room2);
        System.out.println(room3);
        System.out.println(room4);
        System.out.println(room5);
        System.out.println(room6);
    }
}
