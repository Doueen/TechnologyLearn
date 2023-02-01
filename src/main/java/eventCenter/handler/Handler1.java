package eventCenter.handler;

import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-01-31 13:15
 */
@Listener
public class Handler1 {
    @EventResponse(eventCode = 10010,parameterNum = 1)
    public void handle(String handle){
        System.out.println(this.getClass().getSimpleName()+" do Handle : "+handle);
    }
}
