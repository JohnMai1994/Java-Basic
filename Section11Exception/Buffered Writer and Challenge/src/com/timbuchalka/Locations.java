package com.timbuchalka;


import java.io.*;
import java.util.*;

/**
 * Created by timbuchalka on 2/04/2016.
 */
public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) throws IOException {

//        BufferedWrite 输入数据
//        =======================================================================================
//        try(BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
//            BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions.txt"))) {
//            for(Location location : locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//                for(String direction : location.getExits().keySet()) {
//                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
//                }
//            }
//        }

//        DataOutputStream 输入数据
//        ========================================================================================
//        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations_DataStream.txt")))) {
//            for (Location location: locations.values()) {
//                locFile.writeInt(location.getLocationID());
//                locFile.writeUTF(location.getDescription());
//                System.out.println("Export Location" + location.getLocationID() + ": It's Description " + location.getDescription());
//                System.out.println("There exist " + ( location.getExits().size() -1) + "directions" );
//                locFile.writeInt(location.getExits().size() -1);
//                for (String direction : location.getExits().keySet()){
//                    if (!direction.equalsIgnoreCase("Q")) {
//                        System.out.println("\t\t\t"+ direction + ": " + location.getExits().get(direction));
//                        locFile.writeUTF(direction);
//                        locFile.writeInt(location.getExits().get(direction));
//                    }
//                }
//            }
//        }

//       ObjectOutputStream 输入数据
//        ============================================================================================
//        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations_ObjectStream.txt")))){
//            for (Location location: locations.values()){
//                locFile.writeObject(location);
//            }
//        }


    }

    static {
////       Scanner 来导入locations_big.txt 数据
////       ======================================================================================
//        // 根据loctions_big.txt 存入数据，提供给main使用
//        try(Scanner scanner = new Scanner(new FileReader("locations_big.txt"))) {
//            // useDelimiter() 是改变Scanner的分隔符
//            // Scanner 默认使用的分隔符是空格，回车，tab。
//            // 使用useDelimiter(',') 改变其分隔符为','
//            scanner.useDelimiter(",");
//            while(scanner.hasNextLine()) {
//                int loc = scanner.nextInt();
//                // 跳过，后面的默认分隔符",", 因为nextLine代表后面的所有东西，
//                scanner.skip(scanner.delimiter());
//                String description = scanner.nextLine();
//                System.out.println("Imported loc: " + loc + ": " + description);
//                Map<String, Integer> tempExit = new HashMap<>();
//                locations.put(loc, new Location(loc, description, tempExit));
//            }
//        } catch(IOException e) {
//            e.printStackTrace();
//        }

//        BufferReader导入数据
//        =============================================================================================
//        try (BufferedReader bf = new BufferedReader(new FileReader("locations_big.txt"))){
//            String input;
//            while ((input = bf.readLine()) != null) {
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String description = data[1];
//                System.out.println("Imported loc: " + loc + ": " + description);
//                Map<String, Integer> tempExit = new HashMap<>();
//                locations.put(loc, new Location(loc, description, tempExit));
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
//            String input;
//            while((input = dirFile.readLine()) != null) {
//                String[] data = input.split(",");
//                int loc = Integer.parseInt(data[0]);
//                String direction = data[1];
//                int destination = Integer.parseInt(data[2]);
//
//                System.out.println(loc + ": " + direction + ": " + destination);
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //        DataInputStream 导入数据
//        =============================================================================================
//        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations_DataStream.txt")))){
//            // 解决End of File 的问题：立flag
//            boolean flag = false;
//            while (!flag) {
//                try {
//                    // 基本上就是跟着write怎么写的，读就怎么读
//                    Map<String, Integer> exits = new LinkedHashMap<>();
//                    int loc = locFile.readInt();
//                    String description = locFile.readUTF();
//                    int exitNum = locFile.readInt();
//                    System.out.println("Export Location" + loc + ": It's Description " + description);
//                    System.out.println("There exist " + exitNum + "directions");
//                    for (int i = 0; i < exitNum; i++) {
//                        String direction = locFile.readUTF();
//                        int destination = locFile.readInt();
//                        System.out.println("\t\t\t" + direction + ": " + destination);
//                        exits.put(direction, destination);
//                    }
//                    locations.put(loc, new Location(loc, description, exits));
//                } catch (EOFException e) {
//                    System.out.println("I should turn it off!!!");
//                    flag = true;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        ObjectInputStream 导入数据
//        =============================================================================================
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations_ObjectStream.txt")))){
            boolean flag = false;
            while (!flag) {
                try {
                    Location tem = (Location) locFile.readObject();
                    System.out.println("Export Location" + tem.getLocationID() + ": It's Description " + tem.getDescription());
                    System.out.println("There exist " + tem.getExits().size() + "directions");
                    locations.put(tem.getLocationID(), tem);
                }catch (EOFException e) {
                    flag =true;
                    System.out.println("i should turn it off");
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


//        Map<String, Integer> tempExit = new HashMap<String, Integer>();
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest",tempExit));

    }
    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
