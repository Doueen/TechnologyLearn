package modules.classLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ZhangHongzheng
 * @Description
 * @create 2022-12-12 17:46
 */
public class CustomClassloader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
            InputStream inputStream = getClass().getResourceAsStream(fileName);
            if (inputStream == null) {
                return super.loadClass(name);
            }
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }
}
