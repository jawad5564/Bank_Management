class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500;
    private static final double WITHDRAW_LIMIT = 2000;

    public SavingsAccount(String accountNumber, String owner, String pin, double initialDeposit) {
        super(accountNumber, owner, pin, initialDeposit);
    }

    @Override
    public boolean withdraw(double amount) {
        if(amount<=0){
            System.out.println("Amount must be bigger than 0");
            return false;
        }
        if (amount > WITHDRAW_LIMIT) {
            System.out.println("Withdrawal limit exceeded!");
            return false;
        }
        if (balance - amount < MIN_BALANCE) {
            System.out.println("Insufficient balance! Minimum balance required: " + MIN_BALANCE);
            return false;
        }
        balance -= amount;
        System.out.println("New balance: " + balance);
        recordTransaction("Withdrawn: " + amount + ", New Balance: " + balance);
        return true;
    }
}

