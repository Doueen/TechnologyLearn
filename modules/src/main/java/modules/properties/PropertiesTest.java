package modules.properties;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author ZhangHongzheng @Description
 * @create 2022-12-14 9:45
 */
public class PropertiesTest {
    public static void main(String[] args) {
        Properties properties = new Properties();

        // 使用转换流解决乱码问题
        try (InputStreamReader fileReader =
                new InputStreamReader(new FileInputStream("D:\\WorkSpaces\\Items\\插件管理项目\\project.txt"),"GBK")) {
            properties.load(fileReader);
            properties.forEach((k, v) -> System.out.println(k.toString()+" : "+v.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
