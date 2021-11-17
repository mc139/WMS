package application;

import application.ui.UserInterface;
import model.Person;
import model.Warehouse;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("objects.bin"));
        List<Warehouse> warehouses = (ArrayList<Warehouse>) ois.readObject();

        boolean shouldContinue = true;
        Warehouse chosenWarehouse = warehouses.get(0);
        Scanner scanner = new Scanner(System.in);
        UserInterface.displayPeople(chosenWarehouse);

        int userChoice = Integer.parseInt(scanner.nextLine());
        Person chosenPerson = UserInterface.getPerson(userChoice, chosenWarehouse);

        UserInterface.displayHomeScreen(chosenPerson);

        while (shouldContinue) {
            int optionChosen = Integer.parseInt(scanner.nextLine());
            switch (optionChosen) {
                case 1:
                    shouldContinue = true;
                    UserInterface.displayPersonDetails(chosenPerson);
                    break;
                case 2:
                    UserInterface.enquireRooms(chosenPerson);
                    shouldContinue = true;
                    break;
                case 3:
                    shouldContinue = true;
                    UserInterface.addItem(chosenPerson);
                    break;
                case 4:
                    shouldContinue = true;
                    UserInterface.removeItem(chosenPerson);
                    break;
                case 5:
                    shouldContinue = true;
                    UserInterface.displayEmptyRooms(chosenWarehouse);
                    break;
                case 6:
                    shouldContinue = true;
                    UserInterface.rentNewRoom(chosenPerson,chosenWarehouse);
                    break;
                case 7:
                    shouldContinue = true;
                    UserInterface.saveCurrentWarehouseStatus();
                    break;
                case 8:
                    shouldContinue = true;
                    UserInterface.displayHomeScreen(chosenPerson);
                    break;
                case 9:
                    UserInterface.displayPeople(chosenWarehouse);
                    int userChoice1 = Integer.parseInt(scanner.nextLine());
                    chosenPerson = UserInterface.getPerson(userChoice1, chosenWarehouse);
                    UserInterface.displayHomeScreen(chosenPerson);
                    shouldContinue = true;
                    break;
                case 0:
                    shouldContinue = false;
                    break;
                default:
                    System.out.println("Unknown option chosen !");
                    Thread.sleep(200);
                    UserInterface.displayHomeScreen(chosenPerson);
            }


        }
    }
}

