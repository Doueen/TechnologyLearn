package modules.security;


import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String plainTxt = "多少个地方还是说让他和你的身边";
        String saltKey = "0123456789123456";
        String IV = "0123456789123456";




        Map<String,String> map=RSAUtils.createKeys(2048);
       String encRes1= RSAUtils.publicEncrypt(plainTxt, RSAUtils.getPublicKey(map.get("publicKey")));
        String encRes2= RSAUtils.publicEncrypt(plainTxt, RSAUtils.getPublicKey(map.get("publicKey")));

        String decRes=RSAUtils.privateDecrypt(encRes1,RSAUtils.getPrivateKey(map.get("privateKey")));
        String decRes2=RSAUtils.privateDecrypt(encRes2,RSAUtils.getPrivateKey(map.get("privateKey")));

        System.out.println(encRes1);
        System.out.println(encRes2);

        System.out.println(decRes);
        System.out.println(decRes2);




//        String encRes=SecurityTest.encrypt(plainTxt,saltKey,IV);
//        System.out.println(encRes);
//       try(Writer writer=new FileWriter("ewc"+new Date().getTime())){
//           writer.write(encRes);
//       }catch (Exception e){
//           e.printStackTrace();
//       }
//
//        String decRes=SecurityTest.decrypt(encRes,saltKey,IV);
//        System.out.println(decRes);

    }
}
