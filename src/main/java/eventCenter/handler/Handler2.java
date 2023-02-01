package eventCenter.handler;

import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-01-31 13:15
 */
@Listener
public class Handler2 {

    @EventResponse(eventCode = 10000,parameterNum = 1)
    public void handle(String handle){
        System.out.println(this.getClass().getSimpleName()+" do Handle : "+handle);
    }

    @EventResponse(eventCode = 10086,parameterNum = 1)
    public void handle2(String handle){
        System.out.println(this.getClass().getSimpleName()+" do Handle2 : "+handle);
    }

    @EventResponse(eventCode = 10010,parameterNum = 1)
    public void handle3(String handle){
        System.out.println(this.getClass().getSimpleName()+" do Handle3 : "+handle);
    }



}
