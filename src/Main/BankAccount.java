package Main;


import Main.Buildings.Bank;
import Main.Exeptions.NotInTheList;
import Main.Exeptions.StrNotAllowed;

import java.util.ArrayList;

public class BankAccount {
    public BankAccount(int balance, Person owner, String userName, String pass, Bank bank) {
        this.balance = balance;
        this.owner = owner;
        this.userName = userName;
        this.pass = pass;
        this.bank = bank;

        Main.allBalances += balance;
        Main.totalBalancesPage.lbl_balanceVar.setText(String.valueOf(Main.allBalances));

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    waitAndProfit();
                }
            }
        });
        t.start();
    }
    public BankAccount() {
    }
    ArrayList<Transaction> transactions = new ArrayList<>();
    double balance;
    Person owner;
    public String userName;
    public String pass;
    double timeToNextInterest;
    public Bank bank;

    void waitAndProfit(){
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) { }

        double profitAmount = this.balance / 10.0;
        this.balance += profitAmount;
        new Transaction(TransactionType.INTEREST, profitAmount, this);
    }

    public void accountManagement() {
        boolean go = true;
        int choose;
        while (go) {
            System.out.println("------------------------------ "+this.owner.getName()+" "+this.owner.getFamil()+" bank account ------------------------------");
            System.out.println("\t    balance : " + this.balance);
            System.out.println("\t1 : deposit");
            System.out.println("\t2 : withdraw");
            System.out.println("\t3 : show all transactions");
            System.out.println("\t4 : Back to main menu");
            try {
                choose = Main.sc.nextInt();
            } catch (Exception e) {
                throw new StrNotAllowed();
            } finally {
                Main.sc.nextLine();
            }

            //switch case------------------------------------
            switch (choose) {
                //deposit
                case 1:
                    int amount;
                    System.out.println("inter amount of deposit");
                    amount = Main.sc.nextInt();
                    balance += amount;

                    new Transaction(TransactionType.DEPOSIT, amount, this);
                    break;

                    //withdraw
                case 2:
                    System.out.println("inter amount of withdraw");
                    amount = Main.sc.nextInt();
                    if (balance >= amount) {
                        balance -= amount;

                        new Transaction(TransactionType.WITHDRAW, -1*amount, this);
                    }
                    else System.out.println("not enough money");
                    break;

                //show
                case 3:
                    for (Transaction transaction : this.transactions) {
                        transaction.show();
                    }
                    break;

                //information---------------------
                case 4:
                    go = false;
                    break;

                default:
                    throw new NotInTheList();
            }
        }
    }

    public void show() {
        System.out.println(owner.getName()+" "+owner.getFamil()+"/ "+"username : "+this.userName+"/ pass : " + this.pass+"/ balance : " + this.balance);
    }
}
