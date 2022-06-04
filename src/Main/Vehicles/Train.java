package Main.Vehicles;

import Main.Buildings.TrainStation;

import Main.WelfareServices;

public class Train extends GroundVehicle {
    public Train(TrainStation TrainRelatedTerminal , int numOfStars) {
        super(700, 12);
        if(TrainRelatedTerminal.getCity().costBudget(this.cost())){
            this.numOfStars = 0;
            if (numOfStars >= 0 && numOfStars <=5){
                if(TrainRelatedTerminal.getCity().costBudget(50 * numOfStars))
                this.numOfStars = numOfStars;
            }
            TrainRelatedTerminal.getVehicles().add(this);
            TrainRelatedTerminal.numOfVehicle++;
            System.out.println("Train Terminal bought");
        }
    }
    public static int getBuyCost(){
        return 700;
    }
    int numOfWagons;
    WelfareServices trainService;
    private int numOfStars;
}
