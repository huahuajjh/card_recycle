package com.tqmars.cardrecycle.infrastructure.StringTools;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * Created by jjh on 1/21/17.
 */
public class Des {
    private final static String secretKey = PropertiesFileTool.readByKey("secretKey");
    private final static String vector = PropertiesFileTool.readByKey("vector");
    private final static String encoding = "utf-8";

    public static String toDes3(String data) throws Exception {
        return toDes3(data,vector,secretKey);
    }

    public static String toDes3(String data,String iv,String secret) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(secret.getBytes(encoding));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        Key deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("DESede/CFB8/PKCS5Padding");

        IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
        cipher.init(Cipher.ENCRYPT_MODE, deskey,ips);
        byte[] encryptData = cipher.doFinal(data.getBytes(encoding));
        return new BASE64Encoder().encode(encryptData);
    }

    public static String decode(String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec( secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        deskey = keyfactory. generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DESede/CFB/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec( vector.getBytes());
        cipher. init(Cipher.DECRYPT_MODE,deskey,ips);

        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

        return new String(decryptData,encoding);
    }
}
