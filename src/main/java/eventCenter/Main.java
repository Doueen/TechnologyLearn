package eventCenter;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-01-31 9:48
 */
public class Main {
    public static void main(String[] args) throws  Exception{

        EventCenter.doScan("eventCenter.response","eventCenter.handler");

        EventCenter.broadcast(10086,"�¼�0�����ˣ�");
        EventCenter.broadcast(10086,"�¼�1�����ˣ�");
        EventCenter.broadcast(10000,"�¼�0�ٴη����ˣ�");
        EventCenter.broadcast(10010,"�¼�2�����ˣ�");
        EventCenter.broadcast(10011,"�¼�3�����ˣ�");

    }
}
