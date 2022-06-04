package Main.Vehicles;

import Main.Buildings.AirPort;

public abstract class AirVehicle extends Vehicle {
    public AirVehicle(AirPort chosenAirPort , int buyCost, int capacity) {
        super(buyCost, capacity);
        if(chosenAirPort.getCity().costBudget(buyCost)){
            chosenAirPort.getVehicles().add(this);
            chosenAirPort.numOfVehicle++;
            System.out.println("AirVehicle bought successfully.");
        }
    }
    int maxHeight;
    int runwayLength;
}

