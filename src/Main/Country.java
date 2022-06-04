package Main;

import Main.Exeptions.*;

import java.util.ArrayList;

public class Country {
    private static ArrayList<City> cities = new ArrayList<>();
    private static int totalPopulation;
    private static int totalBudget;

    static public City addNewCity(String name) {
        City newCity = new City(name);

        if (!Main.citiesDir.exists() && cities.isEmpty()) {
            newCity.addPopulation();
        }

        cities.add(newCity);
        return newCity;
    }

    public static int getTotalPopulation (){
        for (City city : cities)
            totalPopulation += city.citizens.size();
        return totalPopulation;
    }

    static public int getTotalBudget() {
        totalBudget = 0;
        for (City city : cities)
            totalBudget += city.getBudget();
        return totalBudget;
    }

    public static void showInformation() {
        for (City city : cities)
            city.showTotalInf();
        if (cities.isEmpty())
            System.out.println("there is no city yet");
    }

    public static ArrayList<City> getCities() {
        return cities;
    }

    static public City askWitchCity (City dontShowMe){
        int counter = 1;
        cities = Country.getCities();

        if (cities.isEmpty() || (cities.size() == 1 && cities.get(0) == dontShowMe)) {
            throw new CancelCauseOfEmptyList(new EmptyList ("city"));
        }

        for (City city : Country.getCities()) {
            if (city != dontShowMe) {
                System.out.println(counter + " : " + city.getName());
            }
            counter++;
        }
        int witchCity = Main.sc.nextInt();
        witchCity--;

        try {
            City city = cities.get(witchCity);
            if (city == dontShowMe)
                throw new InvalidCauseOfNotInList ();
            return city;
        } catch (Exception e) {
            throw new InvalidCauseOfNotInList();
        }
    }
}
