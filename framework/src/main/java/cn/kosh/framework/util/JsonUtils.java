package cn.kosh.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * JSON工具，使用GSON
 * Created by kosh on 2017/5/16.
 */
public class JsonUtils {
    private static final Gson gson = new Gson();
    private static final ObjectMapper omapper = new ObjectMapper();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    public static <T> T toObject(InputStream is, Class<T> clazz) {
        try {
            return omapper.readValue(is, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
