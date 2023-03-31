package observer;


import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

@Listener
public class Observer1 {

    @EventResponse(eventCode = 10086)
    public void setObserverResponse(Object arg) {
        System.out.println("Observer1 handel 发送邮件给管理员:"+arg);

    }

    @EventResponse(eventCode = 10010)
    public void setObserverResponse1(Object arg) {
        System.out.println("Observer1 handel:"+arg);
    }

    @EventResponse(eventCode = 10000)
    public void setObserverResponse10(Object arg) {
        System.out.println("Observer1 handel:"+arg);
    }
}
