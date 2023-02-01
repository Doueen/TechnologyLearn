package modules.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ZhangHongzheng
 * @Description 这里测试context上下文，除了传递字段外，我们还要传递方法，首先测试传递字段的值
 * @create 2022-12-12 10:16
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception{
        Class<?> c= ReflectTest.class.getClassLoader().loadClass("modules.reflect.ReflectBean");
        System.out.println(c.getSimpleName());
        Field field1=c.getDeclaredField("name");
        Field field2=c.getDeclaredField("id");
        field1.set(c,"zhz");
        Object o=c.newInstance();
        field2.set(o,"123");
        Method method=c.getMethod("toString");
        System.out.println(method.invoke(o));
        System.out.println(field1.get(o));
    }
}
