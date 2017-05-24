package cn.kosh.framework.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kosh on 2017/5/17.
 */
public class CookieUtils {
    public static void addCookie(HttpServletResponse response, String name, String value, int expiredTime
    ) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (expiredTime > 0) {
            cookie.setMaxAge(expiredTime);// 以秒为单位
        }
        response.addCookie(cookie);
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        return getCookieMap(request).get(name);
    }

    private static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    public static String getValueFromCookie(HttpServletRequest request, String name) {
        Cookie cookie = getCookieByName(request, name);
        return cookie == null ? null : cookie.getValue();
    }
}
