package Main.Buildings;

import Main.City;

public class BussTerminal extends Terminal {
    public BussTerminal (String bussTerminalName, City city,Object fromFile) {
        super(bussTerminalName, city);
        city.bussTerminals.add(this);
        city.terminals.add(this);
    }

    public BussTerminal(String bussTerminalName, City city){
        super(bussTerminalName, city);
        if(city.costBudget(buildCost)){
            city.bussTerminals.add(this);
            city.terminals.add(this);
            System.out.println("BussTerminal bought");
        }

    }
    private static final int buildCost = 1500;

    public static int getBuildCost() {
        return buildCost;
    }

}
