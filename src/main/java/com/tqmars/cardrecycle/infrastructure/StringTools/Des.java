package com.tqmars.cardrecycle.infrastructure.StringTools;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.net.URLEncoder;
import java.security.Key;

/**
 * Created by jjh on 1/21/17.
 */
public class Des {
    private final static String secretKey = PropertiesFileTool.readByKey("secretKey");
    private final static String vector = PropertiesFileTool.readByKey("vector");
    private final static String encoding = "utf-8";

    public static String toDes3(String data) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey .getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "DESede");
        deskey = keyfactory.generateSecret( spec);

        Cipher cipher = Cipher.getInstance( "DESede/CFB/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec( vector.getBytes());
        cipher.init(Cipher. ENCRYPT_MODE, deskey,ips);
        byte[] encryptData = cipher.doFinal( data.getBytes( encoding));
        return URLEncoder.encode(Md5.toBase64(encryptData));
    }

    public static String decode(String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec( secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "DESede");
        deskey = keyfactory. generateSecret( spec);
        Cipher cipher = Cipher.getInstance( "DESede/CFB/PKCS5Padding" );
        IvParameterSpec ips = new IvParameterSpec( vector.getBytes());
        cipher. init(Cipher. DECRYPT_MODE, deskey, ips);

        byte[] decryptData = cipher. doFinal(Base64. decode(encryptText ));

        return new String( decryptData, encoding);
    }
}