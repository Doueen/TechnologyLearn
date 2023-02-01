package eventCenter;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-01-31 9:48
 */
public class Main {
    public static void main(String[] args) throws  Exception{

        EventCenter.doScan("eventCenter.response","eventCenter.handler");

        EventCenter.broadcast(10086,"事件0发生了！");
        EventCenter.broadcast(10086,"事件1发生了！");
        EventCenter.broadcast(10000,"事件0再次发生了！");
        EventCenter.broadcast(10010,"事件2发生了！");
        EventCenter.broadcast(10011,"事件3发生了！");

    }
}
