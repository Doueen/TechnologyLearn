package eventCenter;

/**
 * 观察者接口，用于对事件作出响应
 * @author
 */
public interface Response {
  default <T> void response(T message) {}

  default void response() {}


}
