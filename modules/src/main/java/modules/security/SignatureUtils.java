package modules.security;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.Signature;
import java.util.Map;

public class SignatureUtils {

    public static void main(String[] args) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        Map<String, String> map = RSAUtils.createKeys(2048);
        String privateKey = map.get("privateKey");
        String publicKey = map.get("publicKey");

        FileWriter writer = new FileWriter(new File("src/main/publicKey.txt"));
        writer.write(publicKey);
        FileWriter writer0 = new FileWriter(new File("src/main/privateKey.txt"));
        writer0.write(privateKey);
        writer.close();
        writer0.close();

        signature.initSign(RSAUtils.getPrivateKey(privateKey));
        //加解密数据
        byte[] data = "hello world".getBytes();
        //数据签名
        signature.update(data);
        byte[] digest = signature.sign();
        //数据解密加验证
        signature.initVerify(RSAUtils.getPublicKey(publicKey));
        signature.update(data);
        System.out.println("验证结果:" + signature.verify(digest));
    }

//    public static String sign(){
//
//    }
}
