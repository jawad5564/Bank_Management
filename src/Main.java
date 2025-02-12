import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Open Account\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bank.openAccount();
                    break;
                case 2:
                    bank.login();
                    bank.performBankingOperations();
                    break;
                case 3:
                    System.out.println("Thank you for using our bank!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
