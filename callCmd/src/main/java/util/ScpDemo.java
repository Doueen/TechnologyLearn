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
        String user = "root"; // Linux��������¼�û���
        String host = "10.210.20.251"; // Linux������IP��ַ
        String password = "*94BYy8G1@All*"; // �洢�û����������
        String sourceFile = "D:\\WorkSpaces\\Items\\PP20230082-����ҽ����Ŀ(����)\\�汾����˵��.doc"; // �����ļ�·��
        String destination = "/opt/workTemp"; // Զ�̷�����Ŀ��·��

        try {
            // ����SCP����
            String command = "echo " + password + " | sudo -S scp " + sourceFile + " " + user + "@" + host + ":" + destination;

            // ִ��SCP����
            Process process = Runtime.getRuntime().exec(command);

            // ��ȡ����ִ�н��
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null||"".equals((line = reader.readLine()))) {
                System.out.println(line);
            }

            // ���SCP����ִ���Ƿ�ɹ�
            int exitStatus = process.waitFor();
            if (exitStatus != 0) {
                System.out.println("SCP command execution failed");
                System.exit(1);
            }

            // �����Ҫ�������룬����ʾ�û�����
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

