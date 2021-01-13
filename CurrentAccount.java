

public class CurrentAccount extends Account
{
    double od;

    public CurrentAccount(String accountNumber, String accountHolderName,
			String dateOfBirth, String contactAddress,
			String email, String phoneNumber, double amount) {

                super(accountNumber, accountHolderName,
                dateOfBirth, contactAddress,
                email, phoneNumber, amount);
                this.setAcctype("CurrentAccount");
                this.od = 5000.0;

            }
    

    public boolean debit(double amount) throws InsufficientFundsException, 
			InvalidAmountException {
		//	Your code goes here...
		try {
			if (amount>super.getAmount()+od)	{
                
				throw new InsufficientFundsException("Insufficient funds to withdraw the amount.");
				
			} else if (amount<=0) {
				throw new InvalidAmountException("Amount can't be negative or 0.");
				
			} else {
                if (amount>super.getAmount()) {
                    amount = amount - super.getAmount();
                    this.od = this.od - amount;
                    System.out.print("Money used from overdraft, current od remaining is : " + this.od + " ");
                    super.setAmount(0);
                    super.setPerf(true);
                    return true;

                } else {
                    super.debit(amount);
                    super.setPerf(true);
                    return true;
                }
			}

		} catch(InsufficientFundsException e) {
			System.out.println("Insufficient funds to withdraw the amount.");
			return false;
		} catch (InvalidAmountException e) {
			System.out.println("Amount can't be negative or 0.");
			return false;
		}

    }
    

    public boolean credit(double amount) throws InvalidAmountException {
		//	Your code goes here...
		try {				
			if (amount<=0) {
				throw new InvalidAmountException("Amount can't be negative or 0.");
				
			} else {
                if (this.od<5000.0) {
                    double remod = 5000-this.od;
                    if (amount<=remod) {
                        this.od += amount;
                        System.out.print("Amount added to pending OD, acc balance: " + getAmount() + " od pending: " + (5000-this.od) + " ");
                        super.setPerf(true);
                        return true;
                    } else {
                        amount = amount - remod;
                        this.od = 5000.0;
                        System.out.print("OD cleared! ");
                        super.credit(amount);
                        super.setPerf(true);
                        return true;
                    }
                } else {
                    super.credit(amount);
                    super.setPerf(true);
                    return true;
                }

			}

		} catch (InvalidAmountException e) {
			System.out.println("Amount can't be negative or 0.");
			return false;
		}
	}
}
