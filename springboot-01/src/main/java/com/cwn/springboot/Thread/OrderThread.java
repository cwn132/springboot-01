package com.cwn.springboot.Thread;

public class OrderThread {

    int stock=100;
    int ordernum;
    public OrderThread(int ordernum){
        this.ordernum=ordernum;
    }

    public void reduceStock() throws InterruptedException {
        Thread.sleep(1000L);
        stock=stock-ordernum;
    }

    public int getBalance() {
        return stock;
    }

    public  static void main(String[] agrs){


        OrderThread ot=new OrderThread(2);
        OrderOper oo=new OrderOper(ot);
        final int THREAD_NUM = 5;

        Thread threads[] = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i ++) {
            threads[i] = new Thread(oo, "Thread" + i);
            threads[i].start();
        }
    }

}

class  OrderOper implements Runnable{
private OrderThread orderThread;

public  OrderOper(OrderThread orderThread){
    this.orderThread=orderThread;
}

    public void  run(){
synchronized (OrderOper.class){ //修饰一个类

        try {
            orderThread.reduceStock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"/"+orderThread.getBalance());

    }
    }
}