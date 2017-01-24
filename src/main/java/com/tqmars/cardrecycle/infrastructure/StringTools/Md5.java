package com.tqmars.cardrecycle.infrastructure.StringTools;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by jjh on 1/14/17.
 */
public class Md5 {
    public static String md5WithSalt(String rawStr){
        String salt = "com.tqmars";
        return DigestUtils.md5Hex(salt+rawStr);
    }

    public static String md5(String rawStr){
        return DigestUtils.md5Hex(rawStr);
    }

    public static String toBase64(String str){
        Base64 encoder = new Base64();
        return encoder.encodeToString(str.getBytes());
    }

    public static String toBase64(byte[] src){
        Base64 encoder = new Base64();
        return encoder.encodeToString(src);
    }

    public static String getFromBase64(String str){
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return Arrays.toString(decoder.decodeBuffer(str));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
