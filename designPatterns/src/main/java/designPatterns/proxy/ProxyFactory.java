package designPatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }


    // 创建代理对象，传入前置处理函数接口
    public Object getProxy(PreHandler preHandler){
        ClassLoader classLoader=this.getClass().getClassLoader();
        Class<?>[] interfaces=target.getClass().getInterfaces();

        // 调用处理
        InvocationHandler invocationHandler=new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res=null;
                try{
                    // 前置处理
                    if (preHandler!=null){
                        preHandler.perHandlerMethod(args);
                    }

                    res= method.invoke(target,args);
                    // 这里添加后置处理
                }catch (Exception e){
                    //这里添加处理
                }
                finally {
                    // 这里添加处理
                }
                return res;
            }
        };
        return Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }

    /**
     * 前置处理，函数式接口
     */
    interface PreHandler{
        void perHandlerMethod(Object[] args);
    }





}
