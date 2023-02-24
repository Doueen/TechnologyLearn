package eventCenter;

import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

@Listener
class Observer2 {


    @EventResponse(eventCode = -10086)
    public void res(Object msg){
        System.out.println(msg+" O 2");
    }


}
