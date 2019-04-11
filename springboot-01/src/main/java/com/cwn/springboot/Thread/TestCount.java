package com.cwn.springboot.Thread;

/***
 *
 */
public class TestCount {

    public static void  main(String[] args){
        System.out.println("进来synchronized");

        SynchronizedCount count=new SynchronizedCount();
        for(int i=0;i<5;i++) {
            ThreadA task = new ThreadA(count);
            task.start(); //开启线程
        }
        try {

            Thread. sleep(100l);//等5个人干完活

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System. out.println("5个人干完活:最后的值:" +count .num );

    }




}
