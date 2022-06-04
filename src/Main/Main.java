package Main;

//mohamad khorsandi

import Main.Exeptions.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static MoneyPage profitPage = new MoneyPage("owner", "balance", "bank", "city");
    public static MoneyPage totalBalancesPage = new MoneyPage("total balance", "", "", "");
    public static double allBalances = 0;
    public static void main(String[] args) throws IOException {
        restoreFromFile();
        boolean go = true;
        int mainChoose;
        while (go) {
            System.out.println("======================================== COUNTRY MANAGEMENT ========================================");
            System.out.println("\t    TOTAL BUDGET : " + Country.getTotalBudget());
            System.out.println("\t1 : Build new city ");
            System.out.println("\t2 : Inter management of cities");
            System.out.println("\t3 : Total information");
            System.out.println("\t4 : Cities information");
            System.out.println("\t5 : Total accounts balance");
            System.out.println("\t6 : Financial Management");
            System.out.println("\t7 : End");
            try {
                mainChoose = sc.nextInt();
            }
            catch (InputMismatchException err){
                System.out.println("string value is not allowed");
                mainChoose = 3;
            }
            switch (mainChoose) {
                case 1 :
                    System.out.println("inter new city name");
                    Country.addNewCity(sc.next());
                    break;

                case 2 :
                    try {
                        Country.askWitchCity(null).management();
                    } catch (RuntimeException err) {
                        if (err instanceof InputMismatchException)
                            System.out.println("String value is not allowed here");

                        System.out.println(err.toString());
                    }
                    break;

                case 3 :
                    Country.showInformation();
                    break;

                case 4 :
                    try {
                        Country.askWitchCity(null).showTotalInf();
                    } catch (RuntimeException err) {
                        System.out.println(err.toString());
                    }
                    break;

                case 5 :
                    MoneyPage.main(args, totalBalancesPage);
                    break;

                case 6 :
                    MoneyPage.main(args, profitPage);
                    break;

                case 7 :
                    go = false;
                    break;

                default:
                    try {
                        throw new NotInTheList();
                    }
                    catch (InvalidInput e){
                        System.out.println(e);
                    }
                    break;
            }
        }

       saveToFile();
    }
    static File citiesDir = new File("cities");
    static File travelsDir  = new File("travels");
    static void restoreFromFile() throws IOException {
        if (!citiesDir.exists()) return;
        City city;
        String cityName;
        for (File cityFile : citiesDir.listFiles()) {
            cityName = cityFile.getName().split("\\.")[0];
            city = Country.addNewCity(cityName);
            city.cityDir = new File(citiesDir, city.getName());
            city.restoreFromFile();
        }
    }

    static void saveToFile () throws IOException {
        citiesDir.mkdir();
        for (City city : Country.getCities()){
            city.cityDir = new File(Main.citiesDir, city.getName());
            city.cityDir.mkdir();
            city.saveToFile();
        }

    }
}