import java.util.Scanner;

class Bank {
    private Account[] accounts;
    private int accountCount = 0;
    private Account loggedInAccount = null;
    private int nextAccountNumber = 11111; // Initialize the first account number

    public Bank() {
        accounts = new Account[100];
    }

    public void openAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Bank is full. Cannot open new accounts.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        String accNum = String.valueOf(nextAccountNumber++); // Set account number and increment it
        System.out.print("Set PIN: ");
        String pin = scanner.next();
        System.out.print("Enter Initial Deposit: ");
        double deposit = scanner.nextDouble();

        System.out.println("Choose Account Type: 1. Savings  2. Student  3. Business");
        int type = scanner.nextInt();

        if (type == 1) {
            accounts[accountCount++] = new SavingsAccount(accNum, name, pin, deposit);
        } else if (type == 2) {
            accounts[accountCount++] = new StudentAccount(accNum, name, pin, deposit);
        } else if (type == 3) {
            accounts[accountCount++] = new BusinessAccount(accNum, name, pin, deposit);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Account created successfully with Account Number: " + accNum);
    }


    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        String accNum = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber.equals(accNum) && accounts[i].authenticate(pin)) {
                loggedInAccount = accounts[i];
                System.out.println("Login successful. Welcome, " + loggedInAccount.owner + "!");
                return;
            }
        }

        System.out.println("Invalid credentials.");
    }

    public void logout() {
        loggedInAccount = null;
        System.out.println("Logged out.");
    }

    public void performBankingOperations() {
        if (loggedInAccount == null) {
            System.out.println("You must log in first.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Check Balance\n5. View Transactions\n6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Deposit
                    System.out.print("Enter Amount: ");
                    double depAmt = scanner.nextDouble();
                    if(depAmt>0){
                        loggedInAccount.deposit(depAmt);
                        System.out.println("Deposit successful.");
                        break;
                    }
                    System.out.println("Amount must be bigger than 0");
                    System.out.println("Deposit amount failed!");
                    break;

                case 2: // Withdraw
                    System.out.print("Enter Amount: ");
                    double wAmt = scanner.nextDouble();
                    if (loggedInAccount.withdraw(wAmt)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Withdrawal failed!");
                    }
                    break;

                case 3: // Transfer
                    System.out.print("Enter Receiver Account Number: ");
                    String toAcc = scanner.next();
                    System.out.print("Enter Amount: ");
                    double transAmt = scanner.nextDouble();
                    if(transAmt<=0){
                        System.out.println("Transfer amount must be bigger than 0");
                        System.out.println("Transfer money failed!");
                        break;
                    }

                    Account receiver = findAccount(toAcc);
                    if (receiver != null) {
                        if (loggedInAccount.withdraw(transAmt)) {
                            receiver.Trdeposit(transAmt);
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Transfer failed.");
                        }
                    } else {
                        System.out.println("Receiver account not found.");
                    }
                    break;

                case 4: // Check Balance
                    System.out.println("Balance: " + loggedInAccount.balance);
                    break;

                case 5: // View Transactions
                    loggedInAccount.printTransactionHistory(loggedInAccount.pin);
                    break;

                case 6: // Logout
                    logout();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private Account findAccount(String accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber.equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }
}