class BusinessAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 5000;

    public BusinessAccount(String accountNumber, String owner, String pin, double initialDeposit) {
        super(accountNumber, owner, pin, initialDeposit);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.println("Overdraft limit exceeded!");
            return false;
        }
        balance -= amount;
        System.out.println("New balance: " + balance);
        recordTransaction("Withdrawn: " + amount + ", New Balance: " + balance);
        return true;
    }
}

