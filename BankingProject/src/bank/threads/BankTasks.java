package bank.threads;

class WithdrawJob implements Runnable {
    private BankAccount acc;
    private int amt;

    public WithdrawJob(BankAccount acc, int amt) {
        this.acc = acc;
        this.amt = amt;
    }

    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " trying to withdraw ₹" + amt);

        if (acc.withdrawAmount(amt)) {
            System.out.println(name + " withdrawal success ₹" + amt);
        } else {
            System.out.println(name + " withdrawal failed ₹" + amt);
        }
    }
}

class DepositJob implements Runnable {
    private BankAccount acc;
    private int amt;

    public DepositJob(BankAccount acc, int amt) {
        this.acc = acc;
        this.amt = amt;
    }

    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " adding ₹" + amt);
        acc.depositAmount(amt);
        System.out.println(name + " deposit done ₹" + amt);
    }
}
