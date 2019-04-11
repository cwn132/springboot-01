package com.cwn.springboot.Thread;

/*
ThreadLocal是解决线程安全问题一个很好的思路，
它通过为每个线程提供一个独立的变量副本解决了变量并发访问的冲突问题。
在很多情况下，ThreadLocal比直接使用synchronized同步机制解决线程安全问题更简单，更方便，
且结果程序拥有更高的并发性。
 */
public class TestThreadLocalv1 {
    // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Object> seqNum = new ThreadLocal<Object>() {
        public Integer initialValue() {
            return 0;
        }
    };


    // ②获取下一个序列值
    public synchronized int getNextNum() {
        seqNum.set((Integer) seqNum.get() + 1);
        return (Integer) seqNum.get();
    }



    public static void main(String[] args) {
        TestThreadLocalv1 sn = new TestThreadLocalv1();
        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();


    }
}

/**
 * 做一个线程类。
 *
 */
class TestClient implements Runnable {
    private TestThreadLocalv1 sn;

    public TestClient(TestThreadLocalv1 sn) {
        super(); //调用父类的初始化方法，其实就是调用父类中的public xxx()方法
        this.sn = sn;
    }

    @Override
    public  void run() {

        for (int i = 0; i < 3; i++) {
            // ④每个线程打出3个序列值
            System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
        }
    }

}
