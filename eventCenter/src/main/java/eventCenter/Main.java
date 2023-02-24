package eventCenter;


/**
 * @author admin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        EventCenter.doScan("eventCenter");
        EventCenter.broadcast(-10086, "broadcast : test");
    }
}


