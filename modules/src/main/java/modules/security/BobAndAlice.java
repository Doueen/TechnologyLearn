package modules.security;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;


/**
 * @author ZhangHongzheng
 * @Description RSA加解密测试
 * @create 2022-12-30 9:23
 */
public class BobAndAlice {
    public static final String RSA_ALGORITHM = "RSA"; // ALGORITHM ['ælgərɪð(ə)m] 算法的意思


    public static void main(String[] args) throws Exception{

        String filePath =
                "D:\\BaiduSyncdisk\\TechniqueTesting\\src\\main\\resources\\AliceEnvelope.txt";

        // 生成秘钥对
        KeyPair keyPair = genKeyPair(1024);

        // 读文件
        byte[] fileData = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            fileData = new byte[fileInputStream.available()];
            fileInputStream.read(fileData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 得到27到118字节
        byte[] plainData=new byte[118+1-27];
        for (int i = 0; i<plainData.length; i++) {
            plainData[i]=fileData[i+27-1];
        }

        // 加密
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encData= rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, plainData, 1024);


        // 解密
        Cipher cipherDec = Cipher.getInstance(RSA_ALGORITHM);
        cipherDec.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] decData=rsaSplitCodec(cipherDec, Cipher.DECRYPT_MODE, encData, 1024);

        System.out.println(new String(decData, StandardCharsets.UTF_8));
    }

    public static KeyPair genKeyPair(int keySize){
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        // 初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        // 生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        return keyPair;
    }




    //rsa切割解码  , ENCRYPT_MODE,加密数据   ,DECRYPT_MODE,解密数据
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;  //最大块
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    //可以调用以下的doFinal（）方法完成加密或解密数据：
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        finally {
            if(out!=null)
            {
                try{
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        byte[] resultDatas = out.toByteArray();

        return resultDatas;
    }


}
