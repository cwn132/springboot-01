package com.cwn.springboot.Thread;

public class ThreadA extends  Thread{

    private SynchronizedCount count ;

    public ThreadA(SynchronizedCount count) {

        this.count =count;

    }

    public void run() {

        count.add();

    }
}
