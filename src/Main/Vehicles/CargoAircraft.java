package Main.Vehicles;

import Main.Buildings.AirPort;
import Main.Buildings.ShippingPort;
import Main.City;

public class CargoAircraft extends AirVehicle {
    public CargoAircraft(AirPort chosenAirPort) {
        super(chosenAirPort , 800, 12);
    }
    public static int getBuyCost(){
        return 800;
    }
    int maxWeightOfCargo;
}
