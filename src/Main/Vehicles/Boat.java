package Main.Vehicles;

import Main.Buildings.ShippingPort;

public class Boat extends SeaVehicle {
    public Boat(ShippingPort chosenShippingPort){
        super(chosenShippingPort, 300, 4);
    }

    public static int getBuyCost(){
        return 300;
    }
    int material;
}
