package application.ui;

import dto.WarehouseToBinFileDto;
import dto.WarehouseToTextFileDto;
import model.Person;
import model.Room;
import model.Warehouse;
import model.item.Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {

    private static boolean shouldContinue;


    public static Person initializePerson(Warehouse warehouse) {
        UserInterface.displayPeople(warehouse);
        Person person = UserInterface.getPerson(Integer.parseInt(new Scanner(System.in).nextLine()), warehouse);
        UserInterface.displayHomeScreen(person);
        return person;
    }

    public static void saveCurrentWarehouseStatus() {
        WarehouseToBinFileDto.save(Warehouse.warehouseExtention, Room.roomExtension, Item.itemExtention);
        WarehouseToTextFileDto.process(Warehouse.warehouseExtention);
    }

    public static void rentNewRoom(Person person, Warehouse warehouse) throws InterruptedException {
        displayEmptyRooms(warehouse);
        Room room = chooseRoom(Integer.parseInt(new Scanner(System.in).nextLine()), person);
        System.out.println("Are You Sure to rent this Y/N");
        String answer = new Scanner(System.in).nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            room.rentToPerson(person, LocalDate.now());
        } else if (answer.equalsIgnoreCase("N")) {
            Thread.sleep(100);
            displayHomeScreen(person);
        } else {
            System.out.println("Please provide Y or N answer!");
        }

    }

    public static void removeItem(Person person) throws InterruptedException {
        displayPersonRooms(person);
        Room room = chooseRoom(Integer.parseInt(new Scanner(System.in).nextLine()), person);
        System.out.println(room.getName());
        displayItemsInTheRoom(room);
        Item chosenItem = room.getItems().get(Integer.parseInt(new Scanner(System.in).nextLine()) - 1);
        room.removeItem(chosenItem);
        System.out.println("Item " + chosenItem.getName() + "has been removed");
        Thread.sleep(100);
        displayHomeScreen(person);

    }

    public static void addItem(Person person) throws InterruptedException {
        displayPersonRooms(person);
        Room room = chooseRoom(Integer.parseInt(new Scanner(System.in).nextLine()), person);
        System.out.println(room.getName());
        System.out.println("Please provide name of item:");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please provide volume of item:");
        BigDecimal volume = new Scanner(System.in).nextBigDecimal();
        room.addItem(new Item(name, volume));
        System.out.println("ITEM " + name + "added ");
        Thread.sleep(100);
        displayHomeScreen(person);
    }

    public static void enquireRooms(Person person) {
        displayPersonRooms(person);
        displayItemsInTheRoom(person.getRooms().get(Integer.parseInt(new Scanner(System.in).nextLine()) - 1));
    }

    public static void displayPersonRooms(Person person) {
        List<Room> collect = Optional.ofNullable(person.getRooms()).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            System.out.println((i + 1 + ". ") + collect.get(i).getName() + " | Area: " + collect.get(i).getUsageArea());
        }
        System.out.println("0. EXIT ");
    }

    public static void displayHomeScreen(Person person) {
        System.out.printf("Hello %s\n", person.getFirstName());
        System.out.println("1. Display your details ");
        System.out.println("2. Rooms Enquiry ");
        System.out.println("3. Add Item ");
        System.out.println("4. Remove Item ");
        System.out.println("5. Display Empty rooms ");
        System.out.println("6. Rent a Room ");
        System.out.println("7. Save ");
        System.out.println("8. Home screen ");
        System.out.println("9. LOGOUT ");
        System.out.println("0. EXIT ");
    }

    public static void displayPersonDetails(Person person) {
        System.out.println(person.getFirstName() + " " + person.getSurname());
        person.getRooms().stream()
                .map(room -> room.getName() + room.getUsageArea())
                .forEach(System.out::println);

        System.out.println("0. EXIT");
    }

    public static void displayPeople(Warehouse warehouse) {
        List<Person> peopleList = warehouse.getPeople();
        for (int i = 0; i < peopleList.size(); i++) {
            System.out.println((i + 1 + ". ") + peopleList.get(i).getFirstName() + " " + peopleList.get(i).getSurname());
        }
        System.out.println("0. EXIT ");
    }

    public static Person getPerson(int choice, Warehouse warehouse) {
        List<Person> people = warehouse.getPeople();
        if ((choice) <= people.size()) {
            return people.get(choice - 1);
        } else {
            throw new IllegalArgumentException("Please provide number within range");
        }
    }


    public static void displayItemsInTheRoom(Room room) {
        List<Item> collect = room.getItems();
        for (int i = 0; i < collect.size(); i++) {
            System.out.println((i + 1 + ". ") + collect.get(i).getName() + " | " + collect.get(i).getVolume());
        }
        System.out.println("0. EXIT");
    }

    public static Room chooseRoom(int choice, Person person) {
        List<Room> rooms = Person.getRooms(person);
        if ((choice - 1) < rooms.size()) {
            return rooms.get(choice - 1);
        } else {
            throw new IllegalArgumentException("Please provide number within range");
        }
    }

    public static void displayEmptyRooms(Warehouse warehouse) {
        List<Room> rooms = Warehouse.getListOfEmptyRooms(warehouse);
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println((i + 1 + ". ") + rooms.get(i).getName() + " | Area :" + rooms.get(i).getUsageArea());
        }
        System.out.println("0. EXIT");
    }

    //WAWALA
//    public static void initalizeMainMenu(Person chosenPerson,Warehouse chosenWarehouse) throws InterruptedException {
//        Scanner scanner = new Scanner(System.in);
//        while (shouldContinue) {
//            int optionChosen = Integer.parseInt(scanner.nextLine());
//            switch (optionChosen) {
//                case 1:
//                    shouldContinue = true;
//                    UserInterface.displayPersonDetails(chosenPerson);
//                    break;
//                case 2:
//                    UserInterface.enquireRooms(chosenPerson);
//                    shouldContinue = true;
//                    break;
//                case 3:
//                    shouldContinue = true;
//                    UserInterface.addItem(chosenPerson);
//                    break;
//                case 4:
//                    shouldContinue = true;
//                    UserInterface.removeItem(chosenPerson);
//                    break;
//                case 5:
//                    shouldContinue = true;
//                    UserInterface.displayEmptyRooms(chosenWarehouse);
//                    break;
//                case 6:
//                    shouldContinue = true;
//                    UserInterface.rentNewRoom(chosenPerson,chosenWarehouse);
//                    break;
//                case 7:
//                    shouldContinue = true;
//                    UserInterface.saveCurrentWarehouseStatus();
//                    break;
//                case 8:
//                    shouldContinue = true;
//                    UserInterface.displayHomeScreen(chosenPerson);
//                    break;
//                case 9:
//                    UserInterface.displayPeople(chosenWarehouse);
//                    int userChoice1 = Integer.parseInt(scanner.nextLine());
//                    chosenPerson = UserInterface.getPerson(userChoice1, chosenWarehouse);
//                    UserInterface.displayHomeScreen(chosenPerson);
//                    shouldContinue = true;
//                    break;
//                case 0:
//                    shouldContinue = false;
//                    break;
//                default:
//                    System.out.println("Unknown option chosen !");
//                    Thread.sleep(200);
//                    UserInterface.displayHomeScreen(chosenPerson);
//            }
//            System.out.println("8. Home screen ");
//            System.out.println("9. LOGOUT ");
//            System.out.println("0. EXIT ");
//        }

}

