package modules.asn1;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-03-24 17:43
 */

import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;

public class AttachedSignatureParser {
    public static void main(String[] args) throws Exception {
        // 添加 Bouncy Castle 作为 Java Security Provider
        Security.addProvider(new BouncyCastleProvider());

        // 要解析的 ASN.1 格式签名数据
        byte[] signatureBytes = Files.readAllBytes(Paths.get("C:\\Users\\admin\\Desktop\\attached\\pbc2gAttached.asn1"));

        // 解析签名数据
        ContentInfo contentInfo = ContentInfo.getInstance(signatureBytes);
        CMSSignedData cmsSignedData = new CMSSignedData(contentInfo);

        // 获取签名者信息
        SignerInformationStore signerInformationStore = cmsSignedData.getSignerInfos();
        SignerInformation signerInformation = signerInformationStore.getSigners().iterator().next();
        System.out.println(signerInformation.toASN1Structure());



    }


}
