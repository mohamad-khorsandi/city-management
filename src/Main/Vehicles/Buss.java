package Main.Vehicles;

import Main.Buildings.BussTerminal;
import Main.Buildings.Terminal;
import Main.City;

public class Buss extends GroundVehicle {
    public Buss( BussTerminal bussRelatedTerminal) {
        super(400, 6);
        if(bussRelatedTerminal.getCity().costBudget(this.cost())){
            bussRelatedTerminal.getVehicles().add(this);
            bussRelatedTerminal.numOfVehicle++;
            System.out.println("Buss bought");
        }
    }
    public static int getBuyCost(){
        return 400;
    }
    String color;
}
