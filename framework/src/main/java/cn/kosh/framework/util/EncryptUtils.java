package cn.kosh.framework.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * 加密解密工具
 * Created by kosh on 2017/5/17.
 */
public class EncryptUtils {
    private static final BasicTextEncryptor encryptor = new BasicTextEncryptor();

    static {
        encryptor.setPassword("kosh");
    }

    public static String encrypt(String message) {
        return encryptor.encrypt(message);
    }

    public static String decrypt(String encryptedMessage) {
        return encryptor.decrypt(encryptedMessage);
    }

    public static void main(String[] args) {
        String pwd = encrypt("1");
        System.out.println(pwd);
        System.out.println(decrypt(pwd));
        System.out.println(decrypt("NJJ4U+qnT2oj03WIM6gxDA=="));

    }
}
