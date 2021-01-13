

public class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, String accountHolderName,
    String dateOfBirth, String contactAddress,
    String email, String phoneNumber, double amount){
        super(accountNumber, accountHolderName,dateOfBirth,contactAddress,email,phoneNumber,amount);
        this.setAcctype("CheckingAccount");
    }

    public boolean debit(double amount) throws InsufficientFundsException, InvalidAmountException{
        super.setFee(0);
        try {
			if ((super.getAmount()-amount)<=0.03*amount)	{
				throw new InsufficientFundsException("Insufficient funds to withdraw the amount.");
				
			} else if (amount<=0) {
				throw new InvalidAmountException("Amount can't be negative or 0.");
				
			} else {
                super.debit(amount);
                super.setFee(CollectFee(amount));
                super.setPerf(true);
                System.out.print("Debit amount deducted after charging fee. ");
                return true;
			}

		} catch(InsufficientFundsException e) {
			System.out.println("Insufficient funds to withdraw the amount.");
			return false;
		} catch (InvalidAmountException e) {
			System.out.println("Amount can't be negative or 0.");
			return false;
		}
    }

    public boolean credit(double amount)throws InvalidAmountException{
        super.setFee(0);
        try {	
			if (amount<=0) {
                throw new InvalidAmountException("Amount can't be negative or 0.");
            } else{
                super.credit(amount);
                super.setFee(CollectFee(amount));
                super.setPerf(true);
                System.out.print("Credit amount added after charging fee. ");
                return true;
            }

		} catch (InvalidAmountException e) {
			System.out.println("Amount can't be negative or 0.");
			return false;
		}

    }

    public double CollectFee(double amount){

        double fee = 0.03*amount;
        super.setAmount(super.getAmount()-fee);
        System.out.print("BankCharges: "+ fee + " ");
        return fee;
    }
    
}
