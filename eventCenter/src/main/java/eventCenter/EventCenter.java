package eventCenter;

import eventCenter.annotation.EventResponse;
import eventCenter.annotation.Listener;
import eventCenter.exception.EventBroadcastException;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件中心
 */
public class EventCenter {

    /**
     * 存储事件map
     */
    private static ConcurrentHashMap<Integer, List<Response>> eventMap;

    /**
     * 添加事件监听
     *
     * @param eventCode 事件码
     * @param observer  观察者
     */
    public static void addListener(EventCode eventCode, Response observer) {
        addListener(eventCode.getCode(), observer);
    }

    public static void addListener(Integer eventCode, Response observer) {
        initEvent(eventCode);
        List<Response> observerList = eventMap.get(eventCode);
        observerList.add(observer);
    }

    /**
     * 广播事件
     *
     * @param eventCode 事件码
     * @param message   事件参数
     * @param <T>       事件参数类型
     */
    public static <T> void broadcast(EventCode eventCode, T message) {
        broadcast(eventCode.getCode(), message);
    }

    public static <T> void broadcast(Integer eventCode, T message) {
        initEvent(eventCode);
        List<Response> observerList = eventMap.get(eventCode);
        observerList.removeIf(Objects::isNull);
        observerList.forEach(
                observer -> {
                    observer.response(message);
                });
    }

    /**
     * 初始化事件
     *
     * @param eventCode 事件码
     */
    private static void initEvent(EventCode eventCode) {
        initEvent(eventCode.getCode());
    }

    private static void initEvent(Integer eventCode) {
        if (eventMap == null) {
            eventMap = new ConcurrentHashMap<Integer, List<Response>>();
        }
        if (!eventMap.containsKey(eventCode)) {
            eventMap.put(eventCode, new ArrayList<>());
        }
    }

    public static void doScan(String... basePackages) throws IOException, URISyntaxException {
        URI classPath = EventCenter.class.getResource("/").toURI();
        for (String basePackage : basePackages) {
            Files.walk(Paths.get(classPath)).filter(path -> path.toString().endsWith(".class")).forEach(path -> {
                if (path.toString().contains(basePackage.replace('.', File.separatorChar))) {
                    try {
                        String className = path.toString().replace(Paths.get(classPath)+ File.separator, "").replace(File.separatorChar, '.').replace(".class", "");
                        initListener(className);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }

    }



    private static void initListener(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName(className);
        Annotation[] annotations = c.getAnnotations();
        if (Arrays.stream(annotations).anyMatch(annotation ->
                annotation instanceof Listener
        )) {
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(EventResponse.class)) {
                    EventResponse eventResponse = method.getAnnotation(EventResponse.class);
                    Object o = c.newInstance();
                    Response response;
                    int paraCount=method.getParameterCount();
                    switch (paraCount){
                        case 0:{
                            response = new Response() {
                                @Override
                                public void response() {
                                    try {
                                        method.invoke(o);
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        System.out.println(e);
                                    }
                                }
                            };
                            break;
                        }
                        case 1:{
                            response = new Response() {
                                @Override
                                public <T> void response(T message) {
                                    try {
                                        method.invoke(o, message);
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        System.out.println(e);
                                    }
                                }
                            };
                            break;
                        }
                        default:{
                            throw new EventBroadcastException("Number of parameters not yet supported:" +paraCount);
                        }
                    }

                    EventCenter.addListener(eventResponse.eventCode(), response);
                }
            }
        }
    }
}
