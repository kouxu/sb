package cn.kosh.framework.util;


import org.apache.tomcat.util.buf.HexUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具
 * Created by kosh on 2017/5/15.
 */
public class Md5Utils {
    public static String md5(String data) {
        String s = data == null ? "" : data;
        try {
            byte[] strTemp = s.getBytes(Charset.forName("utf-8"));
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            return HexUtils.toHexString(md);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String rs = md5("1");
        System.out.println(rs);
    }
}
