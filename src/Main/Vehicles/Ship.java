package Main.Vehicles;

import Main.Buildings.ShippingPort;
import Main.City;
import Main.WelfareServices;

public class Ship extends SeaVehicle {
    public Ship(ShippingPort chosenShippingPort){
        super(chosenShippingPort, 600, 10);
    }

    public static int getBuyCost(){
        return 600;
    }
    WelfareServices shipService;
    double weight;

}
