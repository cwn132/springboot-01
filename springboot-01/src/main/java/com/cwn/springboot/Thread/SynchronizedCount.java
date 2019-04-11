package com.cwn.springboot.Thread;


/*
 *synchronized同步线程安全方法
 */
public class SynchronizedCount {


    public int num = 0;

    public synchronized void add() {
        try {
            Thread. sleep(5l);//模仿用户干活

        } catch (InterruptedException e) {

        }
        num += 1;
        System. out.println(Thread.currentThread().getName() +"-" + num);
    }

}
