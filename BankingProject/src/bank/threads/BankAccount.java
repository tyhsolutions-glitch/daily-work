package bank.threads;

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized int checkBalance() {
        return balance;
    }

    public synchronized boolean withdrawAmount(int amt) {
        System.out.println(Thread.currentThread().getName() + " checking balance...");

        if (balance >= amt) {
            balance = balance - amt;
            return true;
        }
        return false;
    }

    public synchronized void depositAmount(int amt) {
        try { Thread.sleep(200); } catch (Exception e) {}
        balance = balance + amt;
    }
}
