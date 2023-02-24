package observer;


import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

@Listener
public class Observer1 {

    @EventResponse(eventCode = -10086)
    public void setObserverResponse(Object arg) {
        System.out.println(arg);

    }
}
