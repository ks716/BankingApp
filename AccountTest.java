import java.util.*;


//import java.util.Arrays;


public class AccountTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    
        Account account = null;
        Bank bank = null;
        while (scan.hasNextLine()) {
            String tokens[] = scan.nextLine().split(" ");
            switch (tokens[0]) {
                case "openbank":
                    tokens = tokens[1].split(",");
                    bank = new Bank(tokens[0], tokens[1], tokens[2], tokens[3],Double.parseDouble(tokens[4]));
                    System.out.println("Bank opened with bank name: " + bank.bankname);
                    break;
                
                //Savings account block
                case "Savingsaccount":
                    account = bank.createAccount(tokens);
                    				
                    break;
                //Current account block
                case "Currentaccount":
                    account = bank.createAccount(tokens);
                    
                    break;
                //Checking account block
                case "Checkingaccount":
                    account = bank.createAccount(tokens);
                    
                    break;
                ///////-----------
                case "debit":
                    try {
                        boolean flag = account.debit(Double.parseDouble(tokens[1]));
                        if (flag)
                            System.out.println("After debit, " + account);
                            bank.banker.bankCredit(account.getFee());
                    } catch(Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    bank.accs.set(account.getAC(), account);
                    break;
                case "credit":
                    try {
                        boolean flag = account.credit(Double.parseDouble(tokens[1]));
                        if (flag)
                            System.out.println("After credit, " + account);
                            bank.banker.bankCredit(account.getFee());
                    } catch(Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    bank.accs.set(account.getAC(), account);
                    break;
                case "holdings":
                    System.out.println("Funds in main bank: " + bank.banker.getAmount());
                    break;
                // case "test":
                //     System.out.println(bank.accs.get(1).getAcctype());
                //     break;
                case "sortbyamount":                    
                    System.out.println("**Sort by amount**");
                    bank.sortByAmount();
                    break;
                case "sortbytype":
                    System.out.println("**Sort by account type**");
                    bank.sortByAccType();
                    break;
                case "non-performing":
                    System.out.println("**Non-performing accounts**");
                    bank.inactive();
                    
                default:
                    break;
            }
        }
        scan.close();
    }
    
}
