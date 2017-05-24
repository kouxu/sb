package cn.kosh.framework.util;

import java.util.UUID;

/**
 * Created by kosh on 2017/5/17.
 */
public class UUIDGenerator {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(uuid());
    }
}
