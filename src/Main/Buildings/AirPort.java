package Main.Buildings;

import Main.City;

public class AirPort extends Terminal{

    public AirPort (String AirPortName, City city,Object fromFile) {
        super(AirPortName, city);
        city.airPorts.add(this);
        city.terminals.add(this);
    }
    public AirPort(String terminalName , int isInternational, City city){
        super( terminalName , city);
        if(city.costBudget(AirPort.buildCost)){
            if( isInternational == 1 && city.costBudget(200))
                this.isInternational = true;
            city.airPorts.add(this);
            city.terminals.add(this);
            System.out.println("AirPort bought successfully.");
        }
    }

    private boolean isInternational;
    int numOfRunway;
    private static final int buildCost = 2000;

    public static int getBuildCost() {
        return buildCost;
    }
    public boolean isInternational() {
        return isInternational;
    }
}
