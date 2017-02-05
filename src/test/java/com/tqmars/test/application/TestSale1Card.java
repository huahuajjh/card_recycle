package com.tqmars.test.application;

import com.tqmars.cardrecycle.domain.services.sale.thirdapi.ApiResult;
import com.tqmars.cardrecycle.domain.services.sale.thirdapi.SaleCardApi;
import com.tqmars.cardrecycle.infrastructure.StringTools.Des;
import com.tqmars.cardrecycle.infrastructure.StringTools.Md5;
import com.tqmars.cardrecycle.infrastructure.StringTools.OrderNumGenerator;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.net.URLEncoder;
import java.security.Key;

/**
 * Created by jjh on 2/5/17.
 */
public class TestSale1Card {
    @Test
    public void testSale1Card() throws Exception {
        ApiResult result = SaleCardApi.sale1Card("SZX","50","11111111111111111","111111111111111111", OrderNumGenerator.generateOrderNum());
        System.out.println(result);


//        System.out.println(Des.toDes3("123"));
//        byte[] d = des3EncodeCBC("6C4E60E55552386C759569836DC0F83869836DC0F838C0F7".getBytes(),new byte[]{ 1, 2, 3, 4, 5, 6, 7, 8 },"123".getBytes());
//        System.out.println(URLEncoder.encode(Md5.toBase64(d)));

    }

    // 算法名称
    public static final String KEY_ALGORITHM = "desede";
    // 算法名称/加密模式/填充方式
    public static final String CIPHER_ALGORITHM = "desede/cfb/PKCS5Padding";

    /**
     * CBC加密
     * @param key 密钥
     * @param keyiv IV
     * @param data 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
        Key deskey = keyGenerator(new String(key));
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    /**
     *
     * 生成密钥key对象
     * @param KeyStr 密钥字符串
     * @return 密钥对象
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    private static Key keyGenerator(String keyStr) throws Exception {
        byte input[] = HexString2Bytes(keyStr);
        DESedeKeySpec KeySpec = new DESedeKeySpec(input);
        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
    }

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] HexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }
}
