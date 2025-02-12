class StudentAccount extends Account {
    private static final double WITHDRAW_LIMIT = 1000;

    public StudentAccount(String accountNumber, String owner, String pin, double initialDeposit) {
        super(accountNumber, owner, pin, initialDeposit);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > WITHDRAW_LIMIT) {
            System.out.println("Withdrawal limit exceeded!");
            return false;
        }
        if (balance - amount < 0) {
            System.out.println("Insufficient balance!");
            return false;
        }
        balance -= amount;
        System.out.println("New balance: " + balance);
        recordTransaction("Withdrawn: " + amount + ", New Balance: " + balance);
        return true;
    }
}

