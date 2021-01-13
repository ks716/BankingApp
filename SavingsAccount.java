

class SavingsAccount extends Account {
    double rate;

    public SavingsAccount(String accountNumber, String accountHolderName, String dateOfBirth, String contactAddress,
            String email, String phoneNumber, double amount, double rate) {

        super(accountNumber, accountHolderName, dateOfBirth, contactAddress, email, phoneNumber, amount);
        this.setAcctype("SavingsAccount");
        this.rate = rate;
    }

    void calRoi() {
        
        double intr = getAmount() * (rate / 100);
        System.out.print("interest amount:" + intr + " ");
        // System.out.println("amount after interest will add:" + (getAmount() * (rate /
        // 100) + getAmount()));
        double Current = getAmount();
        super.setAmount(intr + Current);
    }
     
    
}