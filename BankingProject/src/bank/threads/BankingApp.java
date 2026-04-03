package bank.threads;

import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter starting balance: ");
        int bal = sc.nextInt();

        BankAccount acc = new BankAccount(bal);

        while (true) {
            System.out.println("\n------- BANK APP DEMONSTRATING MULTI-THREADING -------");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Test Parallel Withdraw");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.println("Balance: ₹" + acc.checkBalance());
                    break;

                case 2:
                    System.out.print("Enter deposit: ₹");
                    int d = sc.nextInt();
                    new Thread(new DepositJob(acc, d)).start(); 
                    break;

                case 3:
                    System.out.print("Enter withdraw: ₹");
                    int w = sc.nextInt();
                    new Thread(new WithdrawJob(acc, w)).start();
                    break;

                case 4:
                    System.out.println("Running parallel withdraw test...");
                    new Thread(new WithdrawJob(acc, bal / 2)).start();
                    new Thread(new WithdrawJob(acc, bal / 2)).start();
                    break;

                case 5:
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Wrong option");
            }
        }
    }
}
