package modules.reflect;


import java.util.HashMap;

/**
 * @author ZhangHongzheng
 * @Description 服务提供给插件的属性字段甚至函数，插件依赖服务提供的上下文对象。希望插件更多的依赖上下文对象而不是程序整体环境
 * @create 2022-12-13 15:01
 */
public class AdminProcessorContext {

    public HashMap<String, Object> getContextData() {
        return contextData;
    }

    public void setContextData(HashMap<String, Object> contextData) {
        this.contextData = contextData;
    }

    public static void setInstance(AdminProcessorContext instance) {
        AdminProcessorContext.instance = instance;
    }

    private HashMap<String,Object> contextData=new HashMap<>();

    private AdminProcessorContext(){}

    private static volatile AdminProcessorContext instance;

    public static AdminProcessorContext getInstance(){
        if(instance==null){
            synchronized (AdminProcessorContext.class){
                if(instance==null){
                    instance=new AdminProcessorContext();
                }
            }
        }
        return instance;
    }

    public Object getContextData(String key) {
        return contextData.get(key);
    }


    public AdminProcessorContext putData(String key, Object value) {
        contextData.put(key,value);
        return getInstance();
    }


    @Override
    public String toString() {
        return "AdminProcessorContext{" +
                "contextData=" + contextData+
                '}';
    }
}
