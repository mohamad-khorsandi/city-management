package Main.Vehicles;

import Main.Buildings.Terminal;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Vehicle{
    public Vehicle(int buyCost, int capacity) {
        Random R = new Random ();
        this.buyCost = buyCost;
        ID = R.nextInt(1000000);
        System.out.println("this vehicle id is : "+this.ID);
        this.capacity = capacity;
    }

    public Vehicle(int ID, Terminal terminal, int buyCost, int capacity){
        this.buyCost = buyCost;
        this.capacity = capacity;
        this.ID = ID;
        terminal.getVehicles().add(this);
    }
    private final int buyCost;
    private final int capacity;
    private int ID;
    private String makerCompany;

    public int getID() {
        return ID;
    }

    public int cost() {
        return buyCost;
    }

    public int getCapacity() {
        return capacity;
    }

    public static File vehicleFile;

    public static void restoreFromFile(Terminal parentTerminal) throws FileNotFoundException {

        Scanner sc = new Scanner(vehicleFile);
        sc.useDelimiter(",");
        while (sc.hasNextInt()){
            new Vehicle( sc.nextInt(), parentTerminal, sc.nextInt(), sc.nextInt());
        }
        sc.close();
    }

    static public void saveToFile(Terminal parentTerminal) throws IOException {

        FileWriter fw = new FileWriter(vehicleFile);
        PrintWriter pw = new PrintWriter(fw);

        for (Vehicle vehicle : parentTerminal.getVehicles()) {
            pw.print(vehicle.getID() + "," + vehicle.buyCost + "," + vehicle.capacity + ",");
        }
        pw.close();
        fw.close();
    }
}


