package modules.JUC.test;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-08 18:47
 */
public class Test03_˳�����_WaitNotify {
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
     * �ȴ����
     */
    private int flag;

    /**
     * ѭ������
     */
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }


}
