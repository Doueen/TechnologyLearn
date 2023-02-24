package eventCenter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author admin
 * @description
 * @create 2022-10-21 12:50
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventResponse {

    /**
     * 响应的描述
     * @return
     */
    String comment() default "";


    /**
     * 事件码
     * @return
     */
    int eventCode() default 0;
}
