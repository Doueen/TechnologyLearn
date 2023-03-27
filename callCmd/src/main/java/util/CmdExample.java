package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-23 15:49
 */
public class CmdExample {
    public static void main(String[] args) throws IOException {
        String cmd="scp ";
        String cmd1="D:/WorkSpaces/Items/插件管理项目/插件管理测试数据/CVM1.1_INSManagePluginFor5.6_build20230103/plugin/what's new.txt" ;
        String cmd2="root@10.210.20.251:/opt";
        Process p = Runtime.getRuntime().exec(new String[]{cmd,cmd1,cmd2});
        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line = null;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                if (line.contains("root@10.210.20.251's password:")) {
                    p.getOutputStream().write("input1\n".getBytes());
                    p.getOutputStream().flush();
                } else if (line.contains("Enter another input:")) {
                    p.getOutputStream().write("input2\n".getBytes());
                    p.getOutputStream().flush();
                }
            }
            while ((line = error.readLine()) != null) {
                System.out.println(line+";");
            }
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}