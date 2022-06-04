package Main.Buildings;

import Main.City;

public class TrainStation extends Terminal {
    public TrainStation (String TrainStationName, City city,Object fromFile) {
        super(TrainStationName, city);
        city.trainStations.add(this);
        city.terminals.add(this);
    }

    public TrainStation(String terminalName , City city) {
        super(terminalName , city);
        if(city.costBudget(TrainStation.buildCost)){
            city.trainStations.add(this);
            city.terminals.add(this);
            System.out.println("TrainStation bought.");
        }
    }
    private static final int buildCost = 1700;

    public static int getBuildCost() {
        return buildCost;
    }

    int numOfInRails;
    int numOfOutRails;
}

