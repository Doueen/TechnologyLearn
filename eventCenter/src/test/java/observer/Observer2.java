package observer;

import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

@Listener
public class Observer2 {


    @EventResponse(eventCode = -10086)
    public void res(Object msg){
        System.out.println(msg+" O 2");
    }


}
