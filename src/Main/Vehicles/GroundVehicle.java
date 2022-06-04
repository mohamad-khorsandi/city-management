package Main.Vehicles;


import Main.WelfareServices;

public abstract class GroundVehicle extends Vehicle {
    public GroundVehicle(int buyCost, int capacity) {
        super(buyCost, capacity);
    }

    int maxSpeed;
    int fuelConsumption;
}

