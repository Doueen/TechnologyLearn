package hutoolTest.scp;

import cn.hutool.extra.ssh.Sftp;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-28 18:03
 */
public class Scptest {

    private static Sftp sftp;

    public static void main(String[] args) {
        String host="10.210.20.251";
        int port=22;
        String user="root";
        String pass="*94BYy8G1@All*";
        sftp=new Sftp(host,port,user,pass);
        System.out.println(sftp.toString());



    }
}
