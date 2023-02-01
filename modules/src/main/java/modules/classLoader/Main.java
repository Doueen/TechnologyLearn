package modules.classLoader;



import sun.misc.Launcher;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @author ZhangHongzheng
 * @Description
 * @create 2023-01-03 16:11
 */
public class Main {
    public static void main(String[] args) {
        // 打印各个加载器的 加载路径
        //1.启动类加载器，加载路径
        System.out.println("启动类的加载路径: ");
        for(URL url : Launcher.getBootstrapClassPath().getURLs()) {
            System.out.println(url);
        }
        System.out.println("----------------------------");
        //2.扩展类加载器，加载路径
        URLClassLoader urlClassLoaderParent = (URLClassLoader) ClassLoader.getSystemClassLoader().getParent();
        System.out.println(urlClassLoaderParent+"扩展类的加载路径: ");
        for(URL url : urlClassLoaderParent.getURLs()) {
            System.out.println(url);
        }
        System.out.println("----------------------------");
        //3.应用类加载器，加载路径
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        System.out.println(urlClassLoader+"应用类的加载路径: ");
        for(URL url : urlClassLoader.getURLs()) {
            System.out.println(url);
        }
        System.out.println("----------------------------");
        System.out.println("System  java.class.path");

        Arrays.stream(System.getProperties().get("java.class.path").toString().split(";"))
                .forEach(System.out::println);
    }
}
