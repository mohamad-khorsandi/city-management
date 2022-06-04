package Main.Vehicles;

import Main.Buildings.AirPort;
import Main.Buildings.ShippingPort;
import Main.City;

public class PassengerAircraft extends AirVehicle {
    public PassengerAircraft(AirPort chosenAirPort) {
        super(chosenAirPort , 850, 12);
    }

    public static int getBuyCost(){
        return 850;
    }
    int numOfCrew;
    String seatRank;
}
