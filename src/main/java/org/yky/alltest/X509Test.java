package org.yky.alltest;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.StringWriter;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class X509Test {

    public static Base64.Encoder encoder = Base64.getEncoder();
    public static Base64.Decoder decoder = Base64.getDecoder();

    public static void main(String[] args) throws Exception{
        KeyPairGenerator kpg =KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        String  publicKey = encoder.encodeToString(keyPair.getPublic().getEncoded());
        String  privateKey = encoder.encodeToString(keyPair.getPrivate().getEncoded());

        System.out.println(privateKey);
        String pem = privatePem(privateKey);
        System.out.println("openssl 生成的私钥.pem:");
        System.out.println(pem);

        System.out.println(publicKey);
        System.out.println("openssl 生成的公钥.pem");
        System.out.println("-----BEGIN PUBLIC KEY-----");
        formatKey(publicKey.replace("_","/").replace("-","+"));
        System.out.println("-----END PUBLIC KEY-----");



        byte[] keyBytes = decoder.decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey signKP = keyFactory.generatePrivate(keySpec);
        //keyFactory.generatePublic(keySpec);

        //为“Hello World”这一字符串生成 CMS 数字签名的过程。其中 certs 代表签名方的证书，signKP.getPrivate()
        //代表签名方的私钥，SHA1withRSA 代表消息的哈希算法是 SHA1，生成公钥私钥的算法是 RSA
        X509CertificateHolder signCert = null;
        System.out.println(sign(signCert, signKP));
    }


    public static String privatePem(String privateKey) throws Exception{
        byte[] privBytes = decoder.decode(privateKey);

        PrivateKeyInfo pkInfo = PrivateKeyInfo.getInstance(privBytes);
        DERObject privateKey1 = pkInfo.getPrivateKey();
        byte[] encoded = privateKey1.getEncoded();

//        ASN1Encodable encodable = pkInfo.parsePrivateKey();
//        ASN1Primitive primitive = encodable.toASN1Primitive();
//        byte[] privateKeyPKCS1 = primitive.getEncoded();

        return pkcs1ToPem(encoded,false);
    }

    public static String pkcs1ToPem(byte[] pcks1KeyBytes,boolean isPublic) throws Exception{
        String type;
        if(isPublic){
            type = "RSA PUBLIC KEY";
        }else{
            type = "RSA PRIVATE KEY";
        }

        PemObject pemObject = new PemObject(type, pcks1KeyBytes);
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        String pemString = stringWriter.toString();

        return pemString;
    }

    /**
     * 格式化java生成的key，一行长的，不适合pem中的-----BEGIN PUBLIC KEY-----，pem已经有换行了
     * @param key
     */
    public static void formatKey(String key){
        if(key==null) return;

        key = key.replace("\n","");

        int count = (key.length()-1)/64+1;
        for(int i=0;i<count;i++){
            if(i+1==count){
                //循环的最后一次
                System.out.println(key.substring(i*64));
            }else{
                System.out.println(key.substring(i*64,i*64+64));
            }
        }
    }

    /**
     * 从pem格式(-----BEGIN PUBLIC KEY-----)的key获取一行key
     * @param pem
     * @return
     */
    public static String pemToKey(String pem){
        if(pem==null) return "";
        if(pem.indexOf("KEY-----")>0){
            pem = pem.substring(pem.indexOf("KEY-----")+"KEY-----".length());
        }
        if(pem.indexOf("-----END")>0){
            pem = pem.substring(0,pem.indexOf("-----END"));
        }
        return pem.replace("\n","");
    }

    public static String sign(X509CertificateHolder signCert, PrivateKey priKey) throws Exception {
        List certList = new ArrayList();
        certList.add(signCert);
        Store certs = new JcaCertStore(certList);
        CMSTypedData msg = new CMSProcessableByteArray("ykky".getBytes());
        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();

        // 相当于“java.security”配置上security.provider.10=org.bouncycastle.jce.provider.BouncyCastleProvider
        Security.addProvider(new BouncyCastleProvider());


        ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(priKey);
        gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
                new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, signCert));
        gen.addCertificates(certs);
        CMSSignedData sigData = gen.generate(msg, true);
        String signature = new String(sigData.getEncoded());
        return signature;
    }
    //https://blog.csdn.net/sharemyfree/article/details/44016925
}
