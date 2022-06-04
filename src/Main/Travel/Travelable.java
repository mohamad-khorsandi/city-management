package Main.Travel;

import Main.Buildings.Terminal;
import Main.Person;
import Main.Vehicles.Vehicle;

import java.util.ArrayList;

public interface Travelable {
    void newTravel(Terminal DestinationTerminal,
                   ArrayList<Person> passengers, Person driver, Vehicle vehicle);

    void sortTravels ();

    int costCalculate(ArrayList<Person> passengers, Vehicle vehicle);

    void travelLog(boolean showIn, boolean showOut);

}
