package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-24 9:42
 */

public class ScpDemo {

    public static void main(String[] args) {
        String user = "root"; // Linux服务器登录用户名
        String host = "10.210.20.251"; // Linux服务器IP地址
        String password = "*94BYy8G1@All*"; // 存储用户输入的密码
        String sourceFile = "D:\\WorkSpaces\\Items\\PP20230082-锦州医保项目(定制)\\版本发行说明.doc"; // 本地文件路径
        String destination = "/opt/workTemp"; // 远程服务器目标路径

        try {
            // 构造SCP命令
            String command = "echo " + password + " | sudo -S scp " + sourceFile + " " + user + "@" + host + ":" + destination;

            // 执行SCP命令
            Process process = Runtime.getRuntime().exec(command);

            // 读取命令执行结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null||"".equals((line = reader.readLine()))) {
                System.out.println(line);
            }

            // 检查SCP命令执行是否成功
            int exitStatus = process.waitFor();
            if (exitStatus != 0) {
                System.out.println("SCP command execution failed");
                System.exit(1);
            }

            // 如果需要输入密码，则提示用户输入
            if (reader.readLine().contains("password")) {
                System.out.print("Enter Linux login password: ");
                password = new BufferedReader(new InputStreamReader(System.in)).readLine();
                command = "echo " + password + " | sudo -S scp " + sourceFile + " " + user + "@" + host + ":" + destination;
                process = Runtime.getRuntime().exec(command);
                exitStatus = process.waitFor();
                if (exitStatus != 0) {
                    System.out.println("SCP command execution failed");
                    System.exit(1);
                } else {
                    System.out.println("File copied successfully to remote server");
                }
            } else {
                System.out.println("File copied successfully to remote server");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

