package modules.fileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author ZhangHongzheng
 * @Description
 * @create 2023-01-06 13:12
 */
public class Main {
    public static void main(String[] args) {
        String filePath = "D:\\WorkSpaces\\Items\\插件管理项目\\2022 12 13会议记录 修改意见.md";
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath))){
            String line=null;
            if((line=bufferedReader.readLine())!=null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
