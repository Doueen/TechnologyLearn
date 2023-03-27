package modules.asn1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-24 20:05
 */
public class Main {
    public static void main(String[] args) throws Exception{
       // byte[] bytes0= Files.readAllBytes(Paths.get("C:\\Users\\admin\\Desktop\\attached/∆’Õ®Attached.asn1"));
        byte[] bytes1=Files.readAllBytes(Paths.get("C:\\Users\\admin\\Desktop\\attached/pbc2gAttached.asn1"));
        byte[] bytes0=Files.readAllBytes(Paths.get("C:\\Users\\admin\\Desktop\\attached/pbc2gAttached1.asn1"));
        System.out.println(bytes0.length);
        System.out.println(bytes1.length);
        HashMap<Integer,Byte> map=new HashMap<Integer, Byte>(56);
        for (int i = 0; i < bytes0.length; i++) {
            if(i< bytes1.length) {
                if(bytes0[i]!=bytes1[i]){
                    map.put(i,bytes0[i]);
                }
            }
        }
        System.out.println(map);
    }
}
