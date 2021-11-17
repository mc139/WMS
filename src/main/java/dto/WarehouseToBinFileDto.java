package dto;

import model.Room;
import model.Warehouse;
import model.item.Item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WarehouseToBinFileDto {

    public static void save (List<Warehouse> warehouses, List<Room> rooms, List<Item> items){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("objects.bin"))){
            oos.writeObject(warehouses);
            oos.writeObject(items);
            oos.writeObject(rooms);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
