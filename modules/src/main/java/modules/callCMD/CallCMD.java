package modules.callCMD;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CallCMD {
    public static void main(String[] args) {
        Arrays.stream(args).forEach(System.out::println);
        StringBuffer stringBuffer=new StringBuffer();
        String filePath="";
        for (int i = 0; i < args.length; i++) {
            if(i== args.length-1) {
                filePath=args[i];
            }
            else {
                stringBuffer.append(args[i]);
                stringBuffer.append(" ");
            }
        }
        callCommand(stringBuffer.toString(),new File(filePath));

    }


    /**
     * 调用shell命令
     * @param command 命令
     * @param file 文件目录
     */
    public static void callCommand(String command,File file){
        Process process =null;
        try {
            process = Runtime.getRuntime().exec(command, null, file);
            // 将流自动关闭
            try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            // 将流自动关闭
            try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }


            int status = process.waitFor();
            if (status != 0) {
                System.err.println("Failed to call shell's command and the return status's is: " + status);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 解压缩文件
     * @param fileName 文件名称
     * @param filePath 文件路径
     */
    public static void unzipCommand(String filePath,String fileName){
        String command="tar -zxvf "+ fileName;
        File file=new File(filePath);
        callCommand(command,file);
    }







}
