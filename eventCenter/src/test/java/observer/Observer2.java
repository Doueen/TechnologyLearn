package observer;

import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

@Listener
public class Observer2 {


    @EventResponse(eventCode = 10086)
    public void res(Object msg){
        System.out.println( "Observer2 handel ≥¢ ‘GC:"+msg);
    }
    @EventResponse(eventCode = 10055)
    public void res1(Object msg){
        System.out.println( "Observer2 handel:"+msg);
    }
    @EventResponse(eventCode = 10056)
    public void res10(Object msg){
        System.out.println( "Observer2 handel:"+msg);
    }


}
