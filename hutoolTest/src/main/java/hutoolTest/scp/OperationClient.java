package hutoolTest.scp;

import java.util.Scanner;

/**
 * 客户端操作
 *
 * @author Mr.Wang
 * @date 2023/01/03
 */
public class OperationClient {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choose = 0;
        System.out.println("你操作的主机地址是:" + UploadClient.getHost());
        while ((choose = getOperation()) != 3){
            switch (choose){
                case 1: UploadClient.restartTomcat(); break;
                case 2: UploadClient.restartNetSignServer(); break;
                default:
            }
        }
        System.exit(0);
    }


    private static int getOperation(){
        System.out.println("---------------------------------------------");
        System.out.println("\t 1. 重启 Tomcat ");
        System.out.println("\t 2. 重启签名进程");
        System.out.println("\t 3. 退出");
        System.out.print("请输入你的选择: ");
        String input = getInput();
        if("1".equals(input)){
            return 1;
        }
        if("2".equals(input)){
            return 2;
        }
        if("3".equals(input)){
            return 3;
        }
        return 0;
    }


    public static String getInput(){
        return scanner.nextLine();
    }
}
