package Main.Buildings;

import Main.City;

public class ShippingPort extends Terminal {
    public ShippingPort (String ShippingPortName, City city,Object fromFile) {
        super(ShippingPortName, city);
        city.shippingPorts.add(this);
        city.terminals.add(this);
    }
    public ShippingPort(String terminalName , City city){
        super(terminalName , city);
        if(city.costBudget(ShippingPort.buildCost)){
            city.shippingPorts.add(this);
            city.terminals.add(this);
            System.out.println("ShippingPort bought");
        }
    }
    private static final int buildCost = 1300;
    int numOfPier;

    public static int getBuildCost() {
        return buildCost;
    }
}
