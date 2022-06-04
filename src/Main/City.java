package Main;

import Main.Buildings.*;
import Main.Exeptions.*;
import Main.Vehicles.*;

import java.io.*;
import java.util.*;

public class City {
    public City(String name) {
        this.name = name;
    }
    private final String name;
    public ArrayList<Person> citizens = new ArrayList<>();
    public ArrayList<Hotel> hotels = new ArrayList<>();
    public ArrayList<Terminal> terminals = new ArrayList<>();
    public ArrayList<BussTerminal> bussTerminals = new ArrayList<>();
    public ArrayList<TrainStation> trainStations = new ArrayList<>();
    public ArrayList<AirPort> airPorts = new ArrayList<>();
    public ArrayList<ShippingPort> shippingPorts = new ArrayList<>();
    public ArrayList<Bank> banks = new ArrayList<>();

    private int Population;
    private int budget = 10000;

    public int getBudget() {
        return this.budget;
    }

    public void addToBudget (int cost){
        this.budget += cost;
    }

    public boolean costBudget(int cost) {
        boolean wasMoneyEnough = false;
        if (budget >= cost) {
            budget = budget - cost;
            wasMoneyEnough = true;
        } else
            System.out.println("you dont have enough money to buy this item ");
        return wasMoneyEnough;
    }

    public String getName() {
        return name;
    }

    void management() {
        boolean goMain = true;
        while (goMain) {
            System.out.println("======================================== MANAGEMENT OF "+this.name.toUpperCase()+" CITY ========================================");
            System.out.println("\t    remaining budget : " + this.getBudget());
            System.out.println("\t1 : BussTerminal $" + BussTerminal.getBuildCost());
            System.out.println("\t2 : ShippingPort $" + ShippingPort.getBuildCost());
            System.out.println("\t3 : AirPort $" + AirPort.getBuildCost());
            System.out.println("\t4 : TrainStation $" + TrainStation.getBuildCost());
            System.out.println("\t5 : Hotel $" + Hotel.getBuildingCost());
            System.out.println("\t6 : Bank");
            System.out.println("\t7 : Add travel");
            System.out.println("\t8 : Show done travels");
            System.out.println("\t9 : TOTAL INFORMATION OF CITY");
            System.out.println("\t10 : Back to country management list");
            int mainChoose = 0;
            try {
                mainChoose = Main.sc.nextInt();
            } catch (Exception e) {
                throw new StrNotAllowed();
            } finally {
                Main.sc.nextLine();
            }


            switch (mainChoose) {
                //BussTerminal===================================================================
                case 1:
                    int bussTerminalChoose;
                    boolean go1 = true;
                    while (go1) {
                        if (!this.bussTerminals.isEmpty()) {
                            System.out.println("------------------------------ BUSS TERMINAL MANAGEMENT LIST ------------------------------");
                            System.out.println("\t    remaining budget : " + this.getBudget());
                            System.out.println("\t1 : Buy Buss Terminal (costs " + BussTerminal.getBuildCost() + " $)");
                            System.out.println("\t2 : Buy Buss (costs " + Buss.getBuyCost() + " $)");
                            System.out.println("\t3 : Buy Driver ");
                            System.out.println("\t4 : Show information ");
                            System.out.println("\t5 : Back to main menu");
                            try {
                                bussTerminalChoose = Main.sc.nextInt();
                            } catch (Exception e) {
                                throw new StrNotAllowed();
                            } finally {
                                Main.sc.nextLine();
                            }

                        } else {
                            bussTerminalChoose = 1;
                        }
                        //Buy BussTerminal----------------
                        switch (bussTerminalChoose) {
                            case 1:
                                new BussTerminal(askTerminalName(), this);
                                break;
                            //buy buss--------------------
                            case 2:
                                new Buss(chooseTerminal(this.bussTerminals, "choose a terminal to place new vehicle"));
                                break;
                            //Buy BussDriver------------------
                            case 3:
                                //chose driver
                                Person chosenDriver = chooseWorker(Job.BussDriver);
                                //add worker
                                if (this.costBudget(chosenDriver.salary)) {
                                    chooseTerminal(this.bussTerminals, "choose a Terminal to place new worker").getDrivers().add(chosenDriver);
                                    chosenDriver.isHired = true;
                                    System.out.println("Buss driver hired");
                                }
                                break;
                            //Show information------------
                            case 4:
                                showTerminalsInf(bussTerminals);

                                break;
                            case 5:
                                go1 = false;
                                break;
                            default:
                                throw new NotInTheList();
                        }
                    }
                    break;
                //Shipping Port====================================================================
                case 2:
                    int ShippingPortChoose;
                    boolean go2 = true;
                    while (go2) {
                        if (!this.shippingPorts.isEmpty()) {
                            System.out.println("------------------------------ SHIPPING PORT MANAGEMENT LIST ------------------------------");
                            System.out.println("\t    remaining budget : " + this.getBudget());
                            System.out.println("\t1 : Buy ShippingPort (costs " + ShippingPort.getBuildCost() + " $)");
                            System.out.println("\t2 : Buy Ship (costs " + Ship.getBuyCost() + " $)");
                            System.out.println("\t3 : Buy Boat (costs " + Boat.getBuyCost() + " $)");
                            System.out.println("\t4 : Buy Seaman ");
                            System.out.println("\t5 : ShippingPort Show information ");
                            System.out.println("\t6 : Back to main menu");
                            try {
                                ShippingPortChoose = Main.sc.nextInt();
                            } catch (Exception e) {
                                throw new StrNotAllowed();
                            } finally {
                                Main.sc.nextLine();
                            }

                        } else {
                            ShippingPortChoose = 1;
                        }

                        //switch case------------------------------------
                        switch (ShippingPortChoose) {
                            case 1:
                                new ShippingPort(askTerminalName(), this);
                                break;
                            //buy Ship --------------------
                            case 2:
                                new Ship(chooseTerminal(this.shippingPorts, "choose a terminal to place new vehicle"));
                                break;
                            //buy Boat --------------------
                            case 3:
                                Ship boatOBJ = new Ship(chooseTerminal(this.shippingPorts, "choose a terminal to place new vehicle"));
                                break;
                            //Buy Seaman------------------
                            case 4:
                                //chose seaman
                                Person chosenSeaman = chooseWorker(Job.Seaman);
                                //add worker
                                if (this.costBudget(chosenSeaman.salary)) {
                                    chooseTerminal(this.shippingPorts, "choose a Terminal to place new worker").getDrivers().add(chosenSeaman);
                                    chosenSeaman.isHired = true;
                                    System.out.println("Buss driver hired ");
                                }
                                break;
                            //Show information------------
                            case 5:
                                showTerminalsInf(shippingPorts);

                                break;
                            case 6:
                                go2 = false;
                                break;
                            default:
                                throw new NotInTheList();
                        }
                    }
                    break;
                //Air Port =======================================================================
                case 3:
                    int AirPortChoose;
                    boolean go3 = true;
                    while (go3) {
                        if (!this.airPorts.isEmpty()) {
                            System.out.println("------------------------------ AIRPORT MANAGEMENT LIST ------------------------------");
                            System.out.println("\t    remaining budget : " + this.getBudget());
                            System.out.println("\t1 : Buy AirPort (costs " + AirPort.getBuildCost() + " $)");
                            System.out.println("\t2 : Buy  Cargo Aircraft (costs " + CargoAircraft.getBuyCost() + " $)");
                            System.out.println("\t3 : Buy Passenger Aircraft (costs " + Boat.getBuyCost() + " $)");
                            System.out.println("\t4 : Buy Pilot ");
                            System.out.println("\t5 : Show information of AirPorts");
                            System.out.println("\t6 : Back to main menu");
                            try {
                                AirPortChoose = Main.sc.nextInt();
                            } catch (Exception e) {
                                throw new StrNotAllowed();
                            } finally {
                                Main.sc.nextLine();
                            }
                            Main.sc.nextLine();
                        } else {
                            AirPortChoose = 1;
                        }
                        //switch case------------------------------------
                        switch (AirPortChoose) {
                            //buy AirPort
                            case 1:
                                System.out.println("do you want to have an International Airport?(it cost 200 more)\n 0 = NO , 1 = YES");
                                int isInternational = Main.sc.nextInt();
                                Main.sc.nextLine();
                                new AirPort(askTerminalName(), isInternational, this);
                                break;
                            //buy Cargo Aircraft --------------------
                            case 2:
                                new CargoAircraft(chooseTerminal(this.airPorts, "choose a terminal to place new vehicle"));
                                break;
                            //buy Passenger Aircraft --------------------
                            case 3:
                                new PassengerAircraft(chooseTerminal(this.airPorts, "choose a terminal to place new vehicle"));
                                break;
                            //Buy Pilot------------------
                            case 4:
                                //chose pilot
                                Person chosenPilot = chooseWorker(Job.Pilot);
                                //add worker
                                if (this.costBudget(chosenPilot.salary)) {
                                    chooseTerminal(this.airPorts, "choose a Terminal to place new worker").getDrivers().add(chosenPilot);
                                    chosenPilot.isHired = true;
                                    System.out.println("Pilot hired");
                                }
                                break;
                            //Show information------------
                            case 5:
                                showTerminalsInf(airPorts);
                                break;
                            case 6:
                                go3 = false;
                                break;
                            default:
                                throw new NotInTheList();
                        }
                    }
                    break;
                //Train Station=======================================================================
                case 4:
                    int TrainStationChoose;
                    boolean go4 = true;
                    while (go4) {
                        if (!this.trainStations.isEmpty()) {
                            System.out.println("------------------------------ TRAIN STATION MANAGEMENT LIST ------------------------------");
                            System.out.println("\t    remaining budget : " + this.getBudget());
                            System.out.println("\t1 : Train Station (costs " + TrainStation.getBuildCost() + " $)");
                            System.out.println("\t2 : Buy  Train (costs " + Train.getBuyCost() + " $)");
                            System.out.println("\t3 : Buy Train driver ");
                            System.out.println("\t4 : Show information of Train Stations");
                            System.out.println("\t5 : Back to main menu");
                            try {
                                TrainStationChoose = Main.sc.nextInt();
                            } catch (Exception e) {
                                throw new StrNotAllowed();
                            } finally {
                                Main.sc.nextLine();
                            }

                        } else {
                            TrainStationChoose = 1;
                        }
                        //switch case------------------------------------
                        switch (TrainStationChoose) {
                            //buy TrainStation
                            case 1:
                                new TrainStation(askTerminalName(), this);
                                break;
                            //buy Train--------------------
                            case 2:
                                System.out.println("how many stars does Train have? (it cost 50 more for each star) \n inter a number between 0 and 5");
                                int numOfStars = Main.sc.nextInt();
                                Main.sc.nextLine();
                                new Train(chooseTerminal(this.trainStations, "choose a terminal to place new vehicle"), numOfStars);
                                break;
                            //Buy Train driver------------------
                            case 3:
                                //chose Train driver
                                Person chosenTrainDriver = chooseWorker(Job.TrainDriver);
                                //add worker
                                if (this.costBudget(chosenTrainDriver.salary)) {
                                    chooseTerminal(this.trainStations, "choose a Terminal to place new worker").getDrivers().add(chosenTrainDriver);
                                    chosenTrainDriver.isHired = true;
                                    System.out.println("Train Driver hired");
                                }
                                break;
                            //Show information------------
                            case 4:
                                showTerminalsInf(trainStations);
                                break;
                            case 5:
                                go4 = false;
                                break;
                            default:
                                throw new NotInTheList();
                        }
                    }
                    break;
                //Hotel=======================================================================
                case 5:
                    int HotelChoose;
                    boolean go5 = true;
                    while (go5) {
                        if (!hotels.isEmpty()) {
                            System.out.println("------------------------------ HOTEL MANAGEMENT LIST ------------------------------");
                            System.out.println("\t    remaining budget : " + this.getBudget());
                            System.out.println("\t1 : Buy HOTEL (costs " + Hotel.getBuildingCost() + " $)");
                            System.out.println("\t2 : Buy crew ");
                            System.out.println("\t3 : Show information ");
                            System.out.println("\t4 : Back to main menu");
                            try {
                                HotelChoose = Main.sc.nextInt();
                            } catch (Exception e) {
                                throw new StrNotAllowed();
                            } finally {
                                Main.sc.nextLine();
                            }
                        } else {
                            HotelChoose = 1;
                        }
                        //Buy Hotel----------------
                        switch (HotelChoose) {
                            case 1:
                                System.out.println("inter name of new Hotel");
                                String HotelName = Main.sc.nextLine();
                                Hotel newHotel = new Hotel(HotelName, this);
                                System.out.println("number of hotel stars: (costs 70$ more for each star)\n inter a number between 0 and 5");
                                newHotel.setNumOfStars(Main.sc.nextInt());
                                Main.sc.nextLine();
                                System.out.println("number of Hotel rooms: (costs 10$ more for each room) \ninter a number between 0 and 100");
                                newHotel.setNumOfRooms(Main.sc.nextInt());
                                Main.sc.nextLine();
                                break;
                            //Buy crew------------------
                            case 2:
                                //choose hotel
                                System.out.println("in witch Hotel new crew will work?");
                                int counter = 1;
                                for (Hotel forEachObj : hotels) {
                                    System.out.println(counter + " : " + forEachObj.name);
                                    counter++;
                                }
                                int witchHotel = Main.sc.nextInt();
                                Main.sc.nextLine();
                                Hotel chosenHotel = hotels.get(--witchHotel);
                                //choose worker
                                Person chosenCrew = chooseWorker(Job.HotelCrew);
                                //add crew
                                if (this.costBudget(chosenCrew.salary)) {
                                    chosenHotel.crews.add(chosenCrew);
                                    chosenCrew.isHired = true;
                                    System.out.println("Crew hired");
                                }
                                break;
                            //Show information------------
                            case 3:
                                showHotelInf();
                                break;
                            case 4:
                                go5 = false;
                                break;
                            default:
                                throw new NotInTheList();
                        }
                    }
                    break;
                //Bank===================================================================
                case 6:
                    int bankChoose;
                    boolean go6 = true;
                    while (go6) {
                        if (!this.banks.isEmpty()) {
                            System.out.println("------------------------------ BANKS ------------------------------");
                            System.out.println("\t    remaining budget : " + this.getBudget());
                            System.out.println("\t1 : Build bank");
                            System.out.println("\t2 : make new account");
                            System.out.println("\t3 : inter an account");
                            System.out.println("\t4 : information");
                            System.out.println("\t5 : Back to main menu");
                            try {
                                bankChoose = Main.sc.nextInt();

                            } catch (Exception e) {
                                throw new StrNotAllowed();
                            } finally {
                                Main.sc.nextLine();
                            }
                        } else {
                            bankChoose = 1;
                        }

                        //switch case------------------------------------
                        switch (bankChoose) {
                            //build bank
                            case 1:
                                System.out.println("inter bank name");
                                this.banks.add(new Bank(Main.sc.nextLine(), this));
                                break;

                            //new account--------------------
                            case 2:
                                Bank chosenBank = Bank.chooseBank(this);
                                chosenBank.newAccount(this);
                                break;

                            //inter an account---------------
                            case 3:
                                chosenBank = Bank.chooseBank(this);

                                Main.sc.nextLine();
                                System.out.println("inter username");
                                String userName = Main.sc.nextLine();

                                System.out.println("inter pass");
                                BankAccount account = chosenBank.login(userName, Main.sc.nextLine());

                                account.accountManagement();
                                break;

                            //information---------------------
                            case 4:
                                chosenBank = Bank.chooseBank(this);
                                chosenBank.show();
                                break;

                            //--------------------------------
                            case 5:
                                go6 = false;
                                break;

                            default:
                                throw new NotInTheList();
                        }
                    }
                    break;
                //Add travel=======================================================================
                case 7:
                    //choose Destination City
                    System.out.println("choose Destination City :");
                    City destinationCity;
                    destinationCity = Country.askWitchCity(this);

                    //choose Travel Kind
                    TravelKind travelKind = chooseTravelKind();

                    //beginning Terminal
                    Terminal beginningTerminal = chooseTerminal(returnTerminals(this, travelKind), "choose beginning terminal");

                    //destination Terminal
                    Terminal destinationTerminal = chooseTerminal(returnTerminals(destinationCity, travelKind), "choose destination terminal");

                    //driver
                    Person driver = chooseDriver (beginningTerminal);

                    //Vehicle
                    Vehicle vehicle = vehicleChoose (beginningTerminal.getVehicles());

                    //choose Passengers
                    ArrayList<Person> chosenPassengers = choosePassengers(this.citizens, vehicle);
                    beginningTerminal.newTravel(destinationTerminal, chosenPassengers, driver, vehicle);
                    break;
                //Show done travels=======================================================================
                case 8:
                    for (Terminal T: this.terminals){
                        System.out.println("DONE TRAVELS FROM "+T.getName()+" "+T.getClass().getSimpleName() + " : ");
                        System.out.println();
                        T.travelLog(false, true);

                        System.out.println();
                        System.out.println("DONE TRAVELS TO "+T.getName()+" "+T.getClass().getSimpleName() + " : ");
                        System.out.println();
                        T.travelLog(true, false);
                    }
                    break;
                //Show Inf=======================================================================
                case 9:
                    showTotalInf();
                        break;
                //End=======================================================================
                case 10:
                    goMain = false;
                    break;
                default:
                    throw new NotInTheList();
            }
        }
    }
    //add Travel-------------------------------------------
    private TravelKind chooseTravelKind (){
        System.out.println("choose Travel kind");
        System.out.println("1 : sea travel");
        System.out.println("2 : air travel");
        System.out.println("3 : ground travel by buss");
        System.out.println("4 : ground travel by Train");

        int number = Main.sc.nextInt();
        --number;

        try {
            return TravelKind.values()[number];
        } catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidCauseOfNotInList();
        }
    }

    private ArrayList<? extends Terminal> returnTerminals (City city, TravelKind tk){
        switch (tk){
            case byBUSS:
                return city.bussTerminals;

            case byTRAIN:
                return city.trainStations;

            case byAIRCRAFT:
                return city.airPorts;

            case bySHIP:
                return city.shippingPorts;

            default:
                throw new RuntimeException();
                //todo
        }
    }

    private ArrayList<Person> choosePassengers (ArrayList<Person> citizens ,Vehicle vehicle){
        ArrayList<Person> chosenCitizens = new ArrayList<>();
        if (citizens.isEmpty())
            throw new CancelCauseOfEmptyList(new EmptyList("Passengers"));

        int capacity = vehicle.getCapacity();
        System.out.println("inter number of passengers (min: "+capacity/2+" max capacity:"+capacity+")");

        int numOfPassengers = Main.sc.nextInt();
        Main.sc.nextLine();
        if (numOfPassengers < capacity/2 || numOfPassengers > capacity)
            throw new illegalNumberOfPassengers();

        System.out.println(showCitizens(citizens, true));

        System.out.println("inter number of "+numOfPassengers+" passenger");
        int PassengerChoose;


        for (int i = 0; i < numOfPassengers; i++) {
            PassengerChoose = Main.sc.nextInt();
            Person passenger;
            try {
                passenger = citizens.get(--PassengerChoose);
                if (!passenger.isHired)
                    chosenCitizens.add(passenger);
                else
                    throw new InvalidCauseOfNotInList();
            } catch (Exception e) {
                throw new InvalidCauseOfNotInList();
            }
        }
        return chosenCitizens;
    }

    private Person chooseDriver (Terminal terminals){
        System.out.println("choose a driver :");

        StringBuilder drivers = showCitizens(terminals.getDrivers(), null);

        if (drivers.length() == 0)
            throw new CancelCauseOfEmptyList(new EmptyList("drivers"));

        System.out.println(drivers);
        int number = Main.sc.nextInt();


        try {
            return terminals.getDrivers().get(--number);
        } catch (RuntimeException e) {
            throw new InvalidCauseOfNotInList();
        }
    }

    private Vehicle vehicleChoose (ArrayList<Vehicle> vehicles) {
        System.out.println("list of chosen terminal vehicle's id :");
        if (vehicles.isEmpty())
            throw new CancelCauseOfEmptyList(new EmptyList("vehicles"));

        for (Vehicle V : vehicles){
            System.out.printf("%d %S" , V.getID() , " ,");
        }
        System.out.println();

        System.out.println("inter a vehicle id : ");
        int id = 0;
            id = Main.sc.nextInt();

        for (Vehicle V : vehicles){
            if (V.getID() == id)
                return V;
        }
        throw new InvalidCauseOfNotInList();
    }
    //GENERAL FUNCTIONS==================================================================================
    //ask functions--------------------------------------
    private <T extends Terminal> T chooseTerminal(ArrayList<T> terminals, String message) {
        System.out.println(message);
        showTerminals(terminals);
        if (terminals.isEmpty())
            throw new CancelCauseOfEmptyList(new EmptyList("destination terminal"));

        int witchTerminal = Main.sc.nextInt();
        witchTerminal--;

        try {
            return terminals.get(witchTerminal);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCauseOfNotInList();
        }
    }

    private String askTerminalName() {
        System.out.println("inter it's name");
        return Main.sc.nextLine();
    }

    private Person chooseWorker(Job jobFilter) {
        System.out.println("chose a worker");
        int age;
        int counter = 0;
        for (Person tempPerson : this.citizens) {
            if(tempPerson.job == jobFilter && tempPerson.isHired == false){
                age = 1400 - tempPerson.birthYear;
                counter = this.citizens.indexOf(tempPerson) + 1;
                System.out.println(counter+"-----------------"+tempPerson.getName()+" "+tempPerson.getFamil()+"-----------------\n"+
                        tempPerson.getName()+" is a "+age+" years old "+tempPerson.gender+" who can work as "+tempPerson.job+"."
                        +"  (SALARY: "+tempPerson.salary+" $)\n");
            }
        }
        if (counter == 0){
            throw new EmptyList("workers");
        }

        int workerNumber = Main.sc.nextInt();
        Main.sc.nextLine();

        Person chosenWorker = citizens.get(--workerNumber);
        if ( ! chosenWorker.job.equals(jobFilter) || chosenWorker.isHired )
            throw new NotInTheList();

        return chosenWorker;
    }
    //show functions--------------------------------------
    public void showTotalInf() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX TOTAL INFORMATION OF "+this.name+" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("budget : "+this.budget);
        System.out.println("\nHOTELS : ");
        showHotelInf();
        System.out.println("\nBUSS TERMINALS : ");
        showTerminalsInf(this.bussTerminals);
        System.out.println("\nTRAIN STATIONS : ");
        showTerminalsInf(this.trainStations);
        System.out.println("\nAIR PORTS : ");
        showTerminalsInf(this.airPorts);
        System.out.println("\nSHIPPING PORTS : ");
        showTerminalsInf(this.shippingPorts);
        System.out.println("\nCITIZENS : ");
        System.out.println(showCitizens(this.citizens, null));
    }

    private static void showTerminals (ArrayList<? extends Terminal> terminals) {
        int counter = 1;
        for (Terminal terminal : terminals) {
            System.out.println(counter + " : " + terminal.getName());
            counter++;
        }
        if (counter == 1)
            System.out.println("empty");
    }

    <T extends Terminal>void showTerminalsInf (ArrayList<T> Terminals){
        int counter = 1;
        for (Terminal forEachObj : Terminals) {
            System.out.println(counter+" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + forEachObj.getName() +" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(forEachObj.getName() + " is a terminal located in " + forEachObj.getCityName() +
                    " witch has " + forEachObj.getVehicles().size() + " vehicle and " + forEachObj.getDrivers().size() + " workers");
            System.out.println("workers : "+ showCitizens(forEachObj.getDrivers(), null));
            System.out.printf("vehicles id : ");
            for (Vehicle V : forEachObj.getVehicles())
                System.out.printf("%d,", V.getID());
            System.out.println();
            counter++;
        }
        if (counter == 1)
            System.out.println("empty");
    }

    public static StringBuilder showCitizens (ArrayList <Person> ArrList, Boolean F_hired__T_not__N_both){
        StringBuilder str = new StringBuilder();
        int counter = 1;
        for (Person tempPerson : ArrList) {
            if (!tempPerson.isHired.equals(F_hired__T_not__N_both)) {
                str.append(counter).append("-").append(tempPerson.getName()).append(" ").append(tempPerson.getFamil()).append(", ");
            }
            counter++;
        }
        return str;
    }
    //===============================================================================================
    private void showHotelInf(){
        for (Hotel forEachObj : hotels) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + forEachObj.name + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(forEachObj.name + " is a " + forEachObj.getNumOfStars() +
                    " star hotel which has " + forEachObj.getNumOfRooms() + " room/rooms and " + forEachObj.crews.size() + " crew");
            System.out.println("HERE IS CREW:");
            showCitizens(forEachObj.crews, null);
        }
    }

    public void addPopulation() {
        new Person("Maryam", "Fathi", 1351, "Tehran", Job.BussDriver, Gender.Woman, 182, this);
        new Person("Ali", "Irani", 1374, "Isfahan", Job.BussDriver, Gender.Man, 113, this);
        new Person("Reza", "Nasr", 1351, "Isfahan", Job.BussDriver, Gender.Man, 70, this);
        new Person("Amir", "Khorsandi", 1335, "Tehran", Job.BussDriver, Gender.Man, 94, this);
        new Person("Mohamad", "Mohamadi", 1337, "Shiraz", Job.BussDriver, Gender.Man, 209, this);
        new Person("Hossein", "Rezaee", 1373, "Isfahan", Job.BussDriver, Gender.Man, 192, this);

        new Person("Reza", "Fathi", 1339, "Shiraz", Job.Seaman, Gender.Man, 72, this);
        new Person("Mohamad", "Shaker", 1345, "Isfahan", Job.Seaman, Gender.Man, 44, this);
        new Person("Zeinab", "Khorsandi", 1346, "Yazd", Job.Seaman, Gender.Woman, 67, this);
        new Person("Maryam", "Rezaee", 1357, "Isfahan", Job.Seaman, Gender.Woman, 48, this);
        new Person("Hossein", "Mohamadi", 1353, "Tehran", Job.Seaman, Gender.Man, 46, this);
        new Person("Amir", "Nasr", 1360, "Shiraz", Job.Seaman, Gender.Man, 185, this);

        new Person("Reza", "Irani", 1358, "Yazd", Job.HotelCrew, Gender.Man, 232, this);
        new Person("Mohamad", "Mohamadi", 1379, "Tehran", Job.HotelCrew, Gender.Man, 175, this);
        new Person("Amir", "Shaker", 1348, "Tehran", Job.HotelCrew, Gender.Man, 134, this);
        new Person("Hossein", "Khorsandi", 1335, "Yazd", Job.HotelCrew, Gender.Man, 169, this);
        new Person("Ali", "Rezaee", 1353, "Isfahan", Job.HotelCrew, Gender.Man, 198, this);
        new Person("Matin", "Nasr", 1365, "Rasht", Job.HotelCrew, Gender.Man, 249, this);

        new Person("Ali", "Fathi", 1379, "Isfahan", Job.Pilot, Gender.Man, 148, this);
        new Person("Sara", "Shaker", 1372, "Tehran", Job.Pilot, Gender.Woman, 74, this);
        new Person("Zeinab", "Mohamadi", 1352, "Yazd", Job.Pilot, Gender.Woman, 153, this);
        new Person("Maryam", "Nasr", 1343, "Rasht", Job.Pilot, Gender.Woman, 72, this);
        new Person("Mohamad", "Khorsandi", 1354, "Isfahan", Job.Pilot, Gender.Man, 181, this);
        new Person("Zahra", "Irani", 1354, "Tehran", Job.Pilot, Gender.Woman, 139, this);

        new Person("Mohamad", "Shaker", 1379, "Shiraz", Job.TrainDriver, Gender.Man, 131, this);
        new Person("Matin", "Nasr", 1352, "Isfahan", Job.TrainDriver, Gender.Man, 237, this);
        new Person("Sara", "Irani", 1364, "Rasht", Job.TrainDriver, Gender.Woman, 130, this);
        new Person("Ali", "Mohamadi", 1356, "Tehran", Job.TrainDriver, Gender.Man, 206, this);
        new Person("Amir", "Fathi", 1365, "Yazd", Job.TrainDriver, Gender.Man, 244, this);
        new Person("Reza", "Khorsandi", 1341, "Isfahan", Job.TrainDriver, Gender.Man, 189, this);
    }

    public File cityDir;
    File budgetFile;

    public void restoreFromFile() throws IOException {
        //bydget----------------------------------------------------
        this.budgetFile = new File(cityDir, "budget.csv");
        Scanner sc = new Scanner(budgetFile);
        this.budget = sc.nextInt();
        sc.close();

        //citizens----------------------------------------------------
        Person.personsFile = new File(cityDir, "citizens.csv");
        Person.restoreFromFile(citizens);

        //terminal----------------------------------------------------
        String[] type0_name1 ;
        for (File folder : cityDir.listFiles()){
            if (folder.isDirectory()){
                type0_name1 = folder.getName().split("=");
                Terminal terminal = TerminalTypes.enumToTerminal(type0_name1[0], type0_name1[1], this);
                terminal.terminalDir = folder;
                terminal.restoreFromFile();
            }
        }

        //hotel------------------------------------------------------
        String type, name;
        for (File hotelFile : this.cityDir.listFiles()){
            if (hotelFile.isFile()){
                type = hotelFile.getName().split("=") [0];
                if (type.equals("HOTEL")){
                    name = hotelFile.getName().split("=") [1].split("\\.") [0];
                    Person.personsFile = hotelFile;
                    Hotel newHotel = new Hotel(name, this);
                    Person.restoreFromFile(newHotel.crews);
                }
            }
        }
    }

    void saveToFile () throws IOException {

        this.budgetFile = new File(cityDir, "budget.csv");
        FileWriter fw = new FileWriter(budgetFile);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(this.budget);

        pw.close();
        fw.close();
        //----------------------------------------------------
        Person.personsFile = new File(cityDir, "citizens.csv");
        Person.saveToFile(this.citizens);
        //----------------------------------------------------
        for (Terminal terminal : this.terminals){
            String terminalType = TerminalTypes.terminalToEnum(terminal).toString();
            terminal.terminalDir = new File (this.cityDir, terminalType+"="+terminal.getName());
            terminal.terminalDir.mkdir();
            terminal.saveToFile();
        }
        //hotel-----------------------------------------------
        for (Hotel hotel : this.hotels){
            Person.personsFile = new File(cityDir, "HOTEL="+hotel.name +".csv");
            Person.saveToFile(hotel.crews);
        }
    }

}
enum TravelKind{
    bySHIP,byAIRCRAFT,byBUSS,byTRAIN;
}
