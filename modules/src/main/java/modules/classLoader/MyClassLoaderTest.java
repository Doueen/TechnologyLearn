package modules.classLoader;

import modules.JUC.Bank;

/**
 * @author ZhangHongzheng
 * @Description 这里用于测试相同类使用不同类加载器加载
 * @create 2022-12-06 14:31
 */
public class MyClassLoaderTest {

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader myClassLoader = new CustomClassloader();
        ClassLoader myClassLoader1 = new CustomClassloader();
        Object obj = myClassLoader.loadClass("modules.classLoader.MyClassLoaderTest").newInstance();
        Object obj1 = myClassLoader1.loadClass("modules.classLoader.MyClassLoaderTest").newInstance();

        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj1.getClass().getClassLoader());
        System.out.println(MyClassLoaderTest.class.getClassLoader());
        System.out.println(obj instanceof MyClassLoaderTest);
        System.out.println(obj.getClass().hashCode());
        System.out.println(obj1.getClass().hashCode());
        MyClassLoaderTest myClassLoaderTest = new MyClassLoaderTest();
        MyClassLoaderTest myClassLoaderTest1 = new MyClassLoaderTest();
        System.out.println(myClassLoaderTest.getClass().hashCode());
        System.out.println(myClassLoaderTest1.getClass().hashCode());
        System.out.println(Bank.class.getClassLoader());

    }

}
