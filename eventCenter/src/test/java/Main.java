import eventCenter.EventCenter;

import java.util.HashMap;

/**
 * @author admin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        EventCenter.doScan("\\");
        EventCenter.broadcast(10086, "broadcast : �ڴ�ռ�ù��� events happened: 10086");
        EventCenter.broadcast(10010, "broadcast : events happened: 10010");
        HashMap<String, String> map=new HashMap<String,String>(16);
        map.put("event0 10000","happened");
        map.put("event1 10000"," happened");
        EventCenter.broadcast(10000,map);
        EventCenter.broadcast(10055,"��Ϸ�غϿ�ʼ");
        EventCenter.broadcast(10056,"��Ϸ�غϽ���");
    }
}


