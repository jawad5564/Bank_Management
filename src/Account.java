abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String owner;
    protected String pin;
    protected String[] transactionHistory;
    protected int transactionCount = 0;

    public Account(String accountNumber, String owner, String pin, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.pin = pin;
        this.balance = initialDeposit;
        this.transactionHistory = new String[100]; // Fixed-size array for transactions
        recordTransaction("Account created with balance: " + initialDeposit);
    }

    public abstract boolean withdraw(double amount);

    public void deposit(double amount) {
        balance += amount;
        System.out.println("New balance: " + balance);
        recordTransaction("Deposited: " + amount + ", New Balance: " + balance);
    }
    public void Trdeposit(double amount) {
        balance += amount;
        recordTransaction("Deposited: " + amount + ", New Balance: " + balance);
    }

    public double getBalance(String enteredPin) {
        return authenticate(enteredPin) ? balance : -1;
    }

    public boolean authenticate(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void recordTransaction(String transaction) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount++] = transaction;
        }
    }

    public void printTransactionHistory(String enteredPin) {
        if (authenticate(enteredPin)) {
            for (int i = 0; i < transactionCount; i++) {
                System.out.println(transactionHistory[i]);
            }
        } else {
            System.out.println("Authentication failed.");
        }
    }
}



