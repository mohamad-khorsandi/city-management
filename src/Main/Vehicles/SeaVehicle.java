package Main.Vehicles;

import Main.Buildings.ShippingPort;

public abstract class SeaVehicle extends Vehicle {
    public SeaVehicle(ShippingPort chosenShippingPort,int buyCost, int capacity) {
        super(buyCost, capacity);
        if(chosenShippingPort.getCity().costBudget(this.cost())){
            chosenShippingPort.getVehicles().add(this);
            chosenShippingPort.numOfVehicle++;
            System.out.println("item bought successfully");
        }
    }
    String fuelKind;
    int minDepth;
}

