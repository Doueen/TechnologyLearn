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
        // ��� Bouncy Castle ��Ϊ Java Security Provider
        Security.addProvider(new BouncyCastleProvider());

        // Ҫ������ ASN.1 ��ʽǩ������
        byte[] signatureBytes = Files.readAllBytes(Paths.get("C:\\Users\\admin\\Desktop\\attached\\pbc2gAttached.asn1"));

        // ����ǩ������
        ContentInfo contentInfo = ContentInfo.getInstance(signatureBytes);
        CMSSignedData cmsSignedData = new CMSSignedData(contentInfo);

        // ��ȡǩ������Ϣ
        SignerInformationStore signerInformationStore = cmsSignedData.getSignerInfos();
        SignerInformation signerInformation = signerInformationStore.getSigners().iterator().next();
        System.out.println(signerInformation.toASN1Structure());



    }


}
