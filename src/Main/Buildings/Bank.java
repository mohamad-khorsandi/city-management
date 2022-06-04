
package Main.Buildings;

import Main.*;

import java.util.ArrayList;

public class Bank {
    public Bank(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public String name;
    int inCirculationMoney;
    public ArrayList<BankAccount> accounts = new ArrayList();
    int numOfActiveAccounts;
    public City city;

    public static Bank chooseBank(City city){
        int counter = 1;
        for (Bank bank : city.banks){
            System.out.println(counter + " : " + bank.name);
            counter++;
        }
        int number = Main.sc.nextInt();
        return city.banks.get(--number);
    }

    public BankAccount login (String username, String pass) {
        BankAccount matchAccount = new BankAccount();
        for (BankAccount account : accounts) {
            if (account.userName.equals(username)) {
                matchAccount = account;
                break;
            }
        }
        if (matchAccount.pass.equals(pass)) {
            return matchAccount;
        }
        else throw new RuntimeException("pass wrong");
    }

    public void newAccount (City city){
        System.out.println("inter start balance");
        int startBalance = Main.sc.nextInt();

        System.out.println("who is the account owner?");
        Person owner = Person.choosePerson(city);

        System.out.println("inter username");
        String userName = Main.sc.next();

        System.out.println("inter pass");
        String pass = Main.sc.next();

        this.accounts.add(new BankAccount(startBalance, owner, userName, pass, this));
        this.numOfActiveAccounts++;
    }

    public void show() {
        System.out.println("BANK NAME : " + this.name + "-------------active accounts : " + this.numOfActiveAccounts);
        for (BankAccount account : this.accounts){
            account.show();
        }
    }
}





