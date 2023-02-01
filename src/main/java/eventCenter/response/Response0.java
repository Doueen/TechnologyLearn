package eventCenter.response;

import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-01-31 9:47
 */
@Listener
public class Response0 {

    @EventResponse(eventCode = 10086,parameterNum = 1)
    public void res(String arg){
        System.out.println(this.getClass().getSimpleName()+" œÏ”¶ : "+arg);
    }
}
