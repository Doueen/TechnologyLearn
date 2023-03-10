package modules.JUC.test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-09 9:23
 */
public class Test05_Ë³ÐòÊä³ö_park {
    static   Thread t1;
    static   Thread t2;
    static   Thread t3;
    public static void main(String[] args) {
        ParkUnpark parkUnpark=new ParkUnpark(5);
         t1=new Thread(()->{
            parkUnpark.print("a",t2);
        },"t1");
         t2=new Thread(()->{
             parkUnpark.print("b",t3);
         },"t2");
         t3=new Thread(()->{
             parkUnpark.print("c",t1);
         },"t3");

         t1.start();
         t2.start();
         t3.start();
         LockSupport.unpark(t1);

    }
}

class ParkUnpark{
    private int loopNumber;

    public ParkUnpark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str,Thread next){
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.println(str);
            LockSupport.unpark(next);
        }
    }
}
