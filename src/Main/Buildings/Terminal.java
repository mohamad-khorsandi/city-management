package Main.Buildings;

import Main.City;
import Main.Country;
import Main.Person;
import Main.Travel.*;
import Main.Vehicles.*;

import java.io.*;
import java.util.Collections;
import java.util.ArrayList;

public class Terminal implements Travelable {
    public Terminal( String terminalName, City city) {
        this.city = city;
        this.cityName = city.getName();
        this.setName(terminalName);
    }

    public Terminal(String name) {
        this.name = name;
        this.searchNeeded = true;
    }

    private City city;
    private String cityName;
    private String name;
    private String terminalAddress;
    private int area;
    public int numOfVehicle;
    public boolean searchNeeded = false;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList <Person> drivers = new ArrayList<>();
    private ArrayList <Travel> outTravels = new ArrayList<>();
    private ArrayList <Travel> inTravels = new ArrayList<>();

    public City getCity() {
        return city;
    }

    public String getCityName() {
        return cityName;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<Person> getDrivers() {
        return drivers;
    }

    public void setName(String name){
        try {
            searchTerminalsByName(name);
            throw new RuntimeException("terminal name cant be used twice");
        } catch (Exception e){
            this.name = name;
        }
    }

    @Override
    public void newTravel(Terminal destinationTerminal,
                          ArrayList<Person> passengers, Person driver, Vehicle vehicle) {
        Travel newTravel = new Travel();
        newTravel.setBeginningTerminal(this);
        newTravel.setDestinationTerminal(destinationTerminal);
        newTravel.setPassengers(passengers);
        newTravel.setDriver(driver);
        newTravel.setCost(costCalculate(passengers, vehicle));
        newTravel.setDate();
        //----------------------------------------------------
        this.outTravels.add(newTravel);
        destinationTerminal.inTravels.add(newTravel);
        //----------------------------------------------------
        this.getCity().citizens.remove(driver);
        this.drivers.remove(driver);

        destinationTerminal.getCity().citizens.add(driver);
        destinationTerminal.drivers.add(driver);
        //----------------------------------------------------
        this.vehicles.remove(vehicle);
        destinationTerminal.vehicles.add(vehicle);
        //----------------------------------------------------
        ArrayList <Person> jobLessPersons = jobLessReturn(passengers);

        this.city.citizens.removeAll(jobLessPersons);
        destinationTerminal.city.citizens.addAll(jobLessReturn(jobLessPersons));
        //----------------------------------------------------
        this.city.addToBudget(newTravel.getCost());

        System.out.println("travel done successfully");
    }
    private ArrayList<Person> jobLessReturn (ArrayList<Person> passengers){
        ArrayList <Person> jobLessPersons = new ArrayList<>();
        for (Person p : passengers)
            if (p.isHired == false)
                jobLessPersons.add(p);
        return jobLessPersons;
    }
    @Override
    public void sortTravels() {
        Collections.sort(this.inTravels);
        Collections.sort(this.outTravels);
    }

    @Override
    public int costCalculate(ArrayList<Person> passengers, Vehicle vehicle) {
        int vehiclePart = vehicle.cost() / 10;
        int passengersPart = passengers.size() * 10;
        return vehiclePart  + passengersPart;
    }

    @Override
    public void travelLog(boolean showIn, boolean showOut) {
        this.sortTravels();
        if (showIn){
            for (Travel t : this.inTravels)
                System.out.println(t);
            if (this.outTravels.isEmpty())
                System.out.println("empty");
        }
        if (showOut) {
            for (Travel t : this.outTravels)
                System.out.println(t);
            if (this.outTravels.isEmpty())
                System.out.println("empty");
        }

    }

    public File terminalDir;

    public void restoreFromFile() throws IOException {
        //drivers----------------------------------------------------
        Person.personsFile = new File(terminalDir, "drivers.csv");
        Person.restoreFromFile(this.drivers);

        //vehicles----------------------------------------------------
        Vehicle.vehicleFile = new File(this.terminalDir, "vehicles.csv");
        if (Vehicle.vehicleFile.exists())
            Vehicle.restoreFromFile(this);

        //travels----------------------------------------------------
        Travel.in_outTravelDir = new File(this.terminalDir, "inTravels");
        Travel.restoreFromFile(this.inTravels);

        Travel.in_outTravelDir = new File(this.terminalDir, "outTravels");
        Travel.restoreFromFile(this.outTravels);
    }

    public void saveToFile() throws IOException {
        //person----------------------------------------------------
        Person.personsFile = new File(terminalDir, "drivers.csv");
        Person.saveToFile(this.drivers);

        //vehicle----------------------------------------------------
        Vehicle.vehicleFile = new File(this.terminalDir, "vehicles.csv");
        Vehicle.saveToFile(this);

        //travels----------------------------------------------------
        Travel.in_outTravelDir = new File(this.terminalDir, "inTravels");
        Travel.in_outTravelDir.mkdir();
        Travel.saveToFile(this.inTravels);

        Travel.in_outTravelDir = new File(this.terminalDir, "outTravels");
        Travel.in_outTravelDir.mkdir();
        Travel.saveToFile(this.outTravels);
    }

    static public Terminal searchTerminalsByName(String name){
        for (City city : Country.getCities()) {
            for (Terminal terminal : city.terminals){
                if (terminal.getName().equals(name))
                    return terminal;
            }
        }
        throw new RuntimeException("name was not found");
    }
}

