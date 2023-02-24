package eventCenter.exception;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-24 14:06
 */
public class EventBroadcastException extends RuntimeException{


    public EventBroadcastException() {
    }

    public EventBroadcastException(String message) {
        super(message);
    }

    public EventBroadcastException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventBroadcastException(Throwable cause) {
        super(cause);
    }
}
