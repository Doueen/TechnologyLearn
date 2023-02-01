package modules.annotation;


import java.lang.reflect.Method;

/**
 * 用于注解解析处理
 */
public class ReNameAnnotationProcess {

    public static void getMethInfo(Class<?> clazz){
        Method[] methods=clazz.getDeclaredMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(AnReName.class)){
                AnReName anReName=(AnReName) method.getAnnotation(AnReName.class);
                System.out.println(anReName);

            }
        }
        
    }
}
