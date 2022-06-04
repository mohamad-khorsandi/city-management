package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    public Person(String name, String famil, int birthYear, String birthLocation, Job job, Gender gender, int salary, City city) {
        this.name = name;
        this.famil = famil;
        this.birthYear = birthYear;
        this.birthLocation = birthLocation;
        this.job = job;
        this.gender = gender;
        this.salary = salary;
        city.citizens.add(this);
    }
    public Person(){
    }

    public Boolean isHired = false;
    private String name;
    private String famil;
    int birthYear;
    String birthLocation;

    Job job;
    Gender gender;
    int salary;

    public String getName() {
        return name;
    }

    public String getFamil() {
        return famil;
    }

    public static Person choosePerson(City city) {
        System.out.println(City.showCitizens(city.citizens, null));
        int number = Main.sc.nextInt();
        return city.citizens.get(--number);
    }

    public static File personsFile;

    public static void restoreFromFile ( ArrayList<Person> list) throws FileNotFoundException {

        if (!personsFile.exists()){
            return;
        }
        Scanner sc = new Scanner(personsFile);
        sc.useDelimiter(",");

        while (sc.hasNext()){
            Person person = new Person();

            person.name = sc.next();

            person.famil = sc.next();

            person.birthYear = sc.nextInt();

            person.birthLocation = sc.next();

            person.job = Job.valueOf(sc.next());

            person.gender = Gender.valueOf(sc.next());

            person.salary = sc.nextInt();

            person.isHired = sc.nextBoolean();

            list.add(person);
        }
        sc.close();
    }

    static public void saveToFile(ArrayList<Person> list) throws IOException {
        FileWriter fw = new FileWriter(personsFile);
        PrintWriter pw = new PrintWriter(fw);
        for (Person person : list) {
            pw.print(person.name + "," + person.famil + "," + person.birthYear + "," + person.birthLocation + "," + person.job + ","
                    + person.gender + "," + person.salary + "," + person.isHired + ",");
        }
        pw.close();
        fw.close();
    }
}

