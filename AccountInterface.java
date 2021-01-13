
public interface AccountInterface {
	public boolean credit(double amount) throws InvalidAmountException;
	public boolean debit(double amount) throws InsufficientFundsException, InvalidAmountException;
	public String toString();
}

