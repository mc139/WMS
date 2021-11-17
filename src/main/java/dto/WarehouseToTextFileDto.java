package dto;

import model.Room;
import model.Warehouse;
import model.item.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class WarehouseToTextFileDto {

    //ToDo - rosnacą wedlug rozmiaru pomieszcznia
    private final static String dir = "/Users/Programowanie/Desktop/WMS/";

    public static void process(List<Warehouse> warehouses) {
        warehouses.sort(new WarehouseVolumeComparator());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dir + "stockStatus.txt"))) {
            for (int i = 0; i < warehouses.size(); i++) {
                Warehouse warehouse = warehouses.get(i);
                bufferedWriter.write("[[" + warehouse.getName().toUpperCase(Locale.ROOT) + " | Total Warehouse area :" + warehouse.getVolume() + "]]\n");
                bufferedWriter.write(WarehouseToTextFileDto.prepareWarehouseData(warehouse) + "");
                bufferedWriter.write("||||||||||||||||||||||||||||||||||||||||||" + "\n");
                bufferedWriter.write("||||||||||||||||||||||||||||||||||||||||||" + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String prepareWarehouseData(Warehouse warehouse) {
        warehouse.getRooms().sort(new RoomVolumeComparator().reversed());
        StringBuilder rtn = new StringBuilder();
        for (int i = 0; i < warehouse.getRooms().size(); i++) {
            Room room = warehouse.getRooms().get(i);
            String firstLine = room.getName() + "| Total usage area: " + room.getUsageArea() + "\n| Area left : " + room.getAreaLeftInTheRoom() + "\n";
            String stockdata = "[[--------------------------------]]" + WarehouseToTextFileDto.prepareStockData(room.getItems()) + "\n[[--------------------------------]]\n";

            rtn.append(firstLine + stockdata);
        }
        return rtn.toString();
    }

    private static String prepareStockData(List<Item> items) {
        items.sort(new ItemVolumeComparator().reversed());
        StringBuilder rtn = new StringBuilder();
//        String name = warehouse.getName();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String firstLine = "\n" + "" + item.getName() + " volume: " + item.getVolume() + " m3" + "" + "";
            rtn.append(firstLine);
        }
        return rtn.toString();
    }

    private static class RoomVolumeComparator implements Comparator<Room> {
        @Override
        public int compare(Room o1, Room o2) {
            return o1.getUsageArea().compareTo(o2.getUsageArea());
        }
    }

    private static class ItemVolumeComparator implements Comparator<Item> {
        @Override
        public int compare(Item i1, Item i2) {
            return i1.getVolume().compareTo(i2.getVolume());
        }
    }

    private static class WarehouseVolumeComparator implements Comparator<Warehouse> {
        @Override
        public int compare(Warehouse w1, Warehouse w2) {
            return w1.getVolume().compareTo(w2.getVolume());
        }
    }

}
