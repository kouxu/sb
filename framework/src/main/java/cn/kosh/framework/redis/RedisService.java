package cn.kosh.framework.redis;

import java.util.List;

/**
 * Created by kosh on 2017/5/16.
 */
public interface RedisService {

     boolean set(String key, String value);

     String get(String key);

     boolean expire(String key,long expire);

     <T> boolean setList(String key ,List<T> list);

     <T> List<T> getList(String key,Class<T> clz);

     long lpush(String key,Object obj);

     long rpush(String key,Object obj);

     String lpop(String key);
}
