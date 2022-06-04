package Main.Travel;

import Main.Buildings.Terminal;
import Main.City;
import Main.Person;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Travel implements Comparable<Travel> {
    private Terminal beginningTerminal;
    private Terminal destinationTerminal;
    private ArrayList<Person> passengers = new ArrayList<>();
    private Person driver;
    private Object vehicle;
    private int ID;
    private Integer cost;
    private Date date;

    public Terminal getBeginningTerminal(){
        if (beginningTerminal.searchNeeded){
            beginningTerminal = Terminal.searchTerminalsByName(beginningTerminal.getName());
            beginningTerminal.searchNeeded = false;
        }
        return beginningTerminal;
    }

    public Terminal getDestinationTerminal(){
        if (destinationTerminal.searchNeeded){
            destinationTerminal = Terminal.searchTerminalsByName(destinationTerminal.getName());
            destinationTerminal.searchNeeded = false;
        }
        return destinationTerminal;
    }

    public void setBeginningTerminal(Terminal beginningTerminal) {
        this.beginningTerminal = beginningTerminal;
    }

    public void setDestinationTerminal(Terminal destinationTerminal) {
        this.destinationTerminal = destinationTerminal;
    }

    public void setPassengers(ArrayList<Person> passengers) {
        this.passengers = passengers;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

    public void setVehicle(Object vehicle) {
        this.vehicle = vehicle;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setDate() {
        Random random = new Random();
        this.date = new Date(random.nextInt(30) + 1, random.nextInt(11) + 1, random.nextInt(50) + 1350);
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public int compareTo(Travel travel2) {
        byte result = (byte)this.date.compareTo(travel2.date);
        if (result == 0)
            return this.cost.compareTo(travel2.cost);
        else
            return result;
    }

    @Override
    public String toString() {
        return this.getBeginningTerminal().getCityName()+"("+this.getBeginningTerminal().getName()
                +") >>>>>>>>>>>>>>>>>>>>>>>>> "+this.date.show()+ " >>>>>>>>>>>>>>>>>>>>>>>>>"
                + this.getDestinationTerminal().getCityName()+"("+this.getDestinationTerminal().getName() +")" +
                "\nINCOME : " + cost +
                "\nDRIVER : " + driver.getName() +" "+ driver.getFamil() +
                "\nPASSENGERS : " + City.showCitizens(this.passengers, null);
    }

    public static File in_outTravelDir;

    public static void restoreFromFile (ArrayList<Travel> list) throws IOException {
        Travel newTravel = new Travel();
        ArrayList<Person> personList = new ArrayList<>();
        String[] fileName;

        for (File travelFile : in_outTravelDir.listFiles()){
            fileName = travelFile.getName().split("\\.");
            newTravel.beginningTerminal = new Terminal(fileName[0]);
            newTravel.cost = Integer.parseInt(fileName[1]);
            newTravel.date = new Date(Integer.parseInt(fileName[2]),Integer.parseInt(fileName[3]),Integer.parseInt(fileName[4]));
            newTravel.destinationTerminal = new Terminal(fileName[5]);

            Person.personsFile = travelFile;
            Person.restoreFromFile(personList);
            newTravel.driver = personList.remove(0);
            newTravel.passengers = personList;

            list.add(newTravel);
        }
    }
    public static void saveToFile (ArrayList<Travel> list) throws IOException {
        ArrayList<Person> driver_Passengers = new ArrayList<>();
        for (Travel travel : list) {
            driver_Passengers.add(travel.driver);
            driver_Passengers.addAll(travel.passengers);

            Person.personsFile = new File(in_outTravelDir, travel.beginningTerminal.getName()+"."+travel.cost+"."+travel.date.day
                                          +"."+travel.date.month+"."+travel.date.year+"."+travel.destinationTerminal.getName() +".csv");
            Person.saveToFile(driver_Passengers);
        }
    }
}
class Date implements Comparable<Date>{
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    int day;
    int month;
    int year;
    public String show (){
        return this.year+"/"+this.month+"/"+this.day;
    }

    @Override
    public int compareTo(Date date2) {
        return this.toCode().compareTo(date2.toCode());
    }

    private Integer toCode (){
         return this.day + (this.month * 100) + (this.year *10000);
    }
}