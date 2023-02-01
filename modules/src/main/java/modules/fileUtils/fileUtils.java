package modules.fileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class fileUtils {
    public static void main(String[] args) throws Exception {
        String str0="D:\\MyDocument\\Desktop\\插件管理项目\\";
        String str1="D:\\MyDocument\\Desktop\\插件管理项目\\INSManagePluginFor5.6_build20221121";
//        deleteFile(new File("D:\\MyDocument\\Desktop\\插件管理项目\\INSManagePluginFor5.6_build20221121"));
     //   Files.copy(new File(str1).toPath(),new File("D:").toPath());
       // System.out.println(new File(str1).toURI().toURL());
        System.out.println(new File(str0+"ShellUtil.java").isFile());

    }

    // 递归删除
    public static void deleteFile(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("文件不存在");
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File item : files
            ) {
               deleteFile(item);
            }
            file.delete();
        }
        else if(file.isFile()){
            file.delete();
        }

    }



}
