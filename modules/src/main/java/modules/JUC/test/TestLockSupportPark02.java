package modules.JUC.test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-08 18:37
 */
public class TestLockSupportPark02 {
    public static void main(String[] args) {
     Thread t1=   new Thread(()->{
            LockSupport.park();
            System.out.println("T1");
        },"T1");
     t1.start();

        new Thread(()->{
            System.out.println("T2");
            LockSupport.unpark(t1);
        },"T2").start();
    }
}
