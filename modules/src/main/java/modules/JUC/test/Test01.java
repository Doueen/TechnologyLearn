package modules.JUC.test;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-08 18:27
 */
public class Test01 {
    static final Object lock=new Object();
    static boolean t2Runned=false;
    public static void main(String[] args) {
        Thread thread1=new Thread(()->{
            synchronized (lock){
                while (!t2Runned){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T1");
            }
        },"t1");
        Thread thread2=new Thread(()->{
            synchronized (lock){
                System.out.println("T2");
                t2Runned=true;
                lock.notify();

            }
        },"t2");
        thread1.start();
        thread2.start();
    }
}
