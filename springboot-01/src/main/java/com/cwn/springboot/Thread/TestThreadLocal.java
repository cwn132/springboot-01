package com.cwn.springboot.Thread;

public class TestThreadLocal implements Cloneable{
    public static void main(String[] args){
        TestThreadLocal p=new TestThreadLocal();
       // TestThreadLocal p1=new TestThreadLocal();
        System.out.println(p); //1.@31221be2

        Thread t = new Thread(new Runnable(){
            public void run(){
                ThreadLocal<TestThreadLocal> threadLocal = new ThreadLocal<>();
                System.out.println(threadLocal); //2.@57a380d2
                threadLocal.set(p);
                //threadLocal.set(p1);
                System.out.println(threadLocal.get()); //3.@31221be2
                threadLocal.remove();
                try {
                    threadLocal.set((TestThreadLocal) p.clone());
                    System.out.println(threadLocal.get());  //4.@59647456
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadLocal); //5.@57a380d2
            }});

        t.start();
    }
}
