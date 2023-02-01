package modules.JUC;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhangHongzheng
 * @Description
 * @create 2023-01-04 14:16
 */
public class Bank {
    private ReentrantLock reentrantLock=new ReentrantLock();
  private final   double[] accounts ;

    public Bank(int n,double initialBalance) {
      accounts=new double[n];
        Arrays.fill(accounts,initialBalance);
    }

    public void transfer(int from,int to,double amount){
        reentrantLock.lock();
        try{
            System.out.println(Thread.currentThread());
            accounts[from]-=amount;
            // 如果不添加这一行，出问题风险大大降低，因为每个线程再次休眠之前做的工作很少，调度器不太可能在线程的计算过程中抢占它的运行权
            System.out.printf(" %10.2f from %d",amount,from,to);
            accounts[to]+=amount;
            System.out.printf(" 总金额 ： %10.2f%n",getTotalBalance());
        }
        finally{
            reentrantLock.unlock();
        }

    }

    public double getTotalBalance(){
        double sum =0;
        for (double a : accounts) {
            sum+=a;
        }
        return sum;
    }

    public int size(){
        return accounts.length;
    }
}
