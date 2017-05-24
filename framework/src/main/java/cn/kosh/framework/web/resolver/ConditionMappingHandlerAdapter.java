package cn.kosh.framework.web.resolver;

import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * Created by kosh on 2017/5/3.
 */
public class ConditionMappingHandlerAdapter implements HandlerMethodArgumentResolver {

    private String CONDITION_PARAMETER_PREFIX = "p_";

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(ConditionDefault.class) != null;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (!this.supportsParameter(parameter)) {
            return null;
        }
        Object o = BeanUtils.instantiate(parameter.getParameterType());
        Field[] fields = parameter.getParameterType().getDeclaredFields();
        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        for (String s : parameterMap.keySet()) {
            if (!StringUtils.contains(s, CONDITION_PARAMETER_PREFIX)) {
                continue;
            }
            String[] values = parameterMap.get(s);
            if (values == null || StringUtils.isBlank(values[0])) {
                continue;
            }
            for (Field field : fields) {
                if (!StringUtils.equals(CONDITION_PARAMETER_PREFIX + field.getName(), s)) {
                    continue;
                }
                field.setAccessible(true);
                if (field.getType() == Date.class) {
                    if (field.isAnnotationPresent(DateTimeFormat.class)) {
                        String pattern = field.getAnnotation(DateTimeFormat.class).pattern();
                        field.set(o, DateUtils.parse(values[0], pattern));
                        break;
                    }
                } else if (field.getType() == Integer.class) {
                    field.set(0, Integer.parseInt(values[0]));
                    break;
                } else if (field.getType() == Long.class) {
                    field.set(0, Long.parseLong(values[0]));
                    break;
                } else if (field.getType() == Short.class) {
                    field.set(0, Short.parseShort(values[0]));
                    break;
                } else if (field.getType() == Double.class) {
                    field.set(0, Double.parseDouble(values[0]));
                    break;
                } else if (field.getType() == Float.class) {
                    field.set(0, Float.parseFloat(values[0]));
                    break;
                } else if (field.getType() == Boolean.class) {
                    field.set(0, Boolean.parseBoolean(values[0]));
                    break;
                } else {
                    field.set(o, values[0]);
                    break;
                }
            }
        }
        return o;
    }
}
