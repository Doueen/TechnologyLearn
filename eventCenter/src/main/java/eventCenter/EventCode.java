package eventCenter;

/** 事件消息枚举类 */
public enum EventCode {

  /**
   * 默认事件码消息，用于测试
   */
  DefaultMessage(0);

  private String message;
  private Integer code;

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  EventCode(String message) {
    this.message = message;
    this.code = 0;
  }

  EventCode() {
    this.code = 0;
    this.message = "default";
  }

  EventCode(Integer code) {
    this.code = code;
    this.message = "default";
  }

  EventCode(String message, int code) {
    this.message = message;
    this.code = code;
  }
}
