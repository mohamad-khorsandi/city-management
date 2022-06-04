package Main;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {
    public Transaction (TransactionType type, double amount, BankAccount account) {
        this.type = type;
        this.amount = amount;
        this.account = account;

        account.transactions.add(this);

        Main.allBalances += amount;
        Main.totalBalancesPage.lbl_balanceVar.setText(String.valueOf(Main.allBalances));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        this.time = sdf.format(cal.getTime());

        this.changeJFrame();
    }

    TransactionType type;
    double amount;
    String time;
    BankAccount account;

    private void changeJFrame() {
        Main.profitPage.lbl_balanceVar.setText(new DecimalFormat("#.#").format(this.account.balance) +  "(" + this.type + ")");
        Main.profitPage.lbl_ownerVar.setText(this.account.owner.getName()+" "+this.account.owner.getFamil());
        Main.profitPage.lbl_bankVar.setText(this.account.bank.name);
        Main.profitPage.lbl_cityVar.setText(this.account.bank.city.getName());
    }

    void show (){
        System.out.println("Transaction Type : " + this.type + "   |    amount : " + this.amount + "   |    time : " + this.time);
    }
}
