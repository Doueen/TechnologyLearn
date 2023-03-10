package modules.JUC.test;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-08 18:47
 */
public class Test03_顺序输出_WaitNotify {
    public static void main(String[] args) {
        WaitNotify waitNotify=new WaitNotify(1,5);
        new Thread(()->{
            waitNotify.print("a",1,2);
        }).start();
        new Thread(()->{
            waitNotify.print("b",2,3);
        }).start();
        new Thread(()->{
            waitNotify.print("c",3,1);
        }).start();
    }

}


class WaitNotify {
    public void print(String str,int waitFlag,int nextFlag){
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this){
                while (flag!=waitFlag){
                    try {
                       this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(str);
                flag=nextFlag;
                this.notifyAll();
            }
        }
    }

    /**
     * 等代标记
     */
    private int flag;

    /**
     * 循环次数
     */
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }


}
