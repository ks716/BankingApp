import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Bank {
    String bankname;
    String branch;
    String address;
    String ifsc;
    double micr;
    Account banker;

    ArrayList<Account> accs;
    int numofaccs;

    Bank(String bankname, String branch, String address, String ifsc, double micr) {
        this.numofaccs = 0;
        this.accs = new ArrayList<Account>();
        
        this.banker = new SavingsAccount("0000", bankname, "2000", address,"email", "phoneNumber", 500000, 0);
        this.banker.setPerf(true);
        accs.add(this.banker);

        this.bankname = bankname;
        this.branch = branch;
        this.address = address;
        this.ifsc = ifsc;
        this.micr = micr;
    }

    public Account createAccount(String[] tokens) {

        Account account = null;

        switch (tokens[0]) {

            // Savings account block
            case "Savingsaccount":
                tokens = tokens[1].split(",");
                account = new SavingsAccount(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                        Double.parseDouble(tokens[6]), Double.parseDouble(tokens[7]));
                ((SavingsAccount) account).calRoi();

                System.out.print("Account created and interest added, " + account + " ");
                break;
            // Current account block
            case "Currentaccount":
                tokens = tokens[1].split(",");
                account = new CurrentAccount(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                        Double.parseDouble(tokens[6]));
                System.out.print("CurrentAccount created, " + account + " ");
                break;
            // Checking account block
            case "Checkingaccount":
                tokens = tokens[1].split(",");
                account = new CheckingAccount(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
                        Double.parseDouble(tokens[6]));
                System.out.print("CheckingAccount created, " + account + " ");
                break;
            default:
                break;
        }
        this.setAccount(account);
        System.out.println("Account created in bank, acc number: " + this.numofaccs);
        account.setAC(this.numofaccs);
        

        return account;
    }

    private void setAccount(Account account) {
        this.numofaccs+=1;
        this.accs.add(account);
        
    }

    void display(){
        for(int i=0;i<this.accs.size();i++)
		{
            if (this.accs.get(i).getAmount()>=500000){
                ;
            } else {
                System.out.println(this.accs.get(i).getAC() + " " + this.accs.get(i).getAccountHolderName() + " " + this.accs.get(i).getAmount() + " " + this.accs.get(i).getAcctype());

            }
		   
		}

    }

    void sortByAmount(){
        Comparator<Account> comparebyamount = (Account o1, Account o2) -> Float.compare((float)o2.getAmount(),(float)o1.getAmount());
        Collections.sort(this.accs,comparebyamount);
        this.display();
    }

    void sortByAccType(){
        Comparator<Account> comparebytype = (Account o1, Account o2) -> o1.getAcctype().compareTo(o2.getAcctype());
        Collections.sort(this.accs,comparebytype);
        this.display();
    }

    void inactive(){
        for(int i=0;i<this.accs.size();i++){
            if (this.accs.get(i).getPerf() == false){
                System.out.println(this.accs.get(i).getAC() + " " + this.accs.get(i).getAccountHolderName() + " " + this.accs.get(i).getAmount() + " " + this.accs.get(i).getAcctype());

            }		   
		}
    }

}
