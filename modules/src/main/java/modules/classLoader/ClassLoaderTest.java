package modules.classLoader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @author ZhangHongzheng @Description
 * @create 2022-12-06 11:27
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\BaiduSyncdisk\\TechniqueTesting\\lib_5.6.50.4\\netsign-common.jar";
        File file=new File(filePath);
        JarFile jarFile=new JarFile(file);
        URL url = file.toURI().toURL();

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {url});
        List classList  = jarFile.stream()
             //   .filter(item->item.getName().contains("ICSAdminAbstractProcessor"))
                .filter(item -> item.getName().endsWith(".class"))
                .map(item-> item.getName().replace('/', '.'))
                .map(item->item.toString().replace(".class",""))
                .collect(Collectors.toList());
     //   classList.forEach(System.out::println);

        String className=classList.get(20).toString();
        System.out.println(className);
        // 这里由于没有导入jar依赖的jar，所以加载类会出现错误
      Class c=  urlClassLoader.loadClass(className);


        Arrays.stream(c.getDeclaredFields()).forEach(System.out::println);
    }



}
