package modules.JUC.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-08 19:13
 */
public class Test04_Ë³ÐòÊä³ö_ReentrantLock  {


    public static void main(String[] args) {
        AwaitSignal awaitSignal=new AwaitSignal(5);
        Condition a=awaitSignal.newCondition();
        Condition b=awaitSignal.newCondition();
        Condition c=awaitSignal.newCondition();
        new Thread(()->{
            awaitSignal.print("a",a,b);
        }).start();
        new Thread(()->{
            awaitSignal.print("b",b,c);
        }).start();
        new Thread(()->{
            awaitSignal.print("c",c,a);
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        awaitSignal.lock();
        try {
            System.out.println( "start");
            a.signal();
        }finally {
            awaitSignal.unlock();
        }
    }
}


class AwaitSignal extends ReentrantLock{
    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Condition current,Condition next){
        for (int i = 0; i < loopNumber; i++) {
            lock();

            try {
                current.await();
                System.out.println(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                unlock();
            }
        }
    }
}
