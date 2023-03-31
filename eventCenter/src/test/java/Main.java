import eventCenter.EventCenter;

import java.util.HashMap;

/**
 * @author admin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        EventCenter.doScan("\\");
        EventCenter.broadcast(10086, "broadcast : 内存占用过大 events happened: 10086");
        EventCenter.broadcast(10010, "broadcast : events happened: 10010");
        HashMap<String, String> map=new HashMap<String,String>(16);
        map.put("event0 10000","happened");
        map.put("event1 10000"," happened");
        EventCenter.broadcast(10000,map);
        EventCenter.broadcast(10055,"游戏回合开始");
        EventCenter.broadcast(10056,"游戏回合结束");
    }
}


