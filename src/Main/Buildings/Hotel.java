package Main.Buildings;

import Main.City;
import Main.Person;

import java.util.ArrayList;

public class Hotel {
    public Hotel(String name, City city) {
        if (city.costBudget(Hotel.buildingCost)){
            this.name = name;
            city.hotels.add(this);
            this.city = city;
        }
    }
    public Hotel(String name, City city, boolean free) {
            this.name = name;
            city.hotels.add(this);
            this.city = city;
    }
    private City city;
    public String name;
    private static final int buildingCost = 1000;
    String address;
    private int numOfStars;
    private int numOfRooms;
    ArrayList <Facilities> HotelFacilities = new ArrayList<>();
    ArrayList <Room> rooms = new ArrayList<Room>();
    public ArrayList<Person> crews = new ArrayList<Person>();

    public static int getBuildingCost() {
        return buildingCost;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public int getNumOfStars() {
        return numOfStars;
    }

    public void setNumOfStars(int numOfStars) {
        if (numOfStars >= 0 && numOfStars <= 5){
            if (this.city.costBudget(70*numOfStars))
                this.numOfStars = numOfStars;
        }
    }

    public void setNumOfRooms(int numOfRooms) {
        Room newRoom;
        if (numOfRooms >= 0 && numOfRooms <= 100){
            if (this.city.costBudget(10*numOfRooms)){
                this.numOfRooms = numOfRooms;
                for (int roomCounter = 1 ; roomCounter <= numOfRooms ; roomCounter++){
                    newRoom = new Room();
                    this.rooms.add(newRoom);
                }
            }
        }
    }

}
class Room{
    int roomNumber;
    int numOfBed;
    int area;
    int cost;
}
enum Facilities{
    Restaurant, Pool, Parking
}