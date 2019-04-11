package com.cwn.springboot.Thread;

public class AccountThread {
    String name;
    float amount;

    public AccountThread(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }
    //存钱
    public  void deposit(float amt) {
        amount += amt;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //取钱
    public  void withdraw(float amt) {
        amount -= amt;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public float getBalance() {
        return amount;
    }


    public static void main(String[] agrs){
        AccountThread account = new AccountThread("zhang san", 10000.0f);
        AccountOperator accountOperator = new AccountOperator(account);

        final int THREAD_NUM = 5;
        Thread threads[] = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i ++) {
            threads[i] = new Thread(accountOperator, "Thread" + i);
            threads[i].start();
        }

    }

}

/**
 * 账户操作类
 */
class AccountOperator implements Runnable{
    private AccountThread account;
    public AccountOperator(AccountThread account) {
        this.account = account;
    }

    public void run() {
        synchronized (account) {   //指定要给某个对象加锁
            account.deposit(500);
//            account.withdraw(500);
            System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
        }
    }
}