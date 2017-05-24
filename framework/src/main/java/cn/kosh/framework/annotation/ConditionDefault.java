package cn.kosh.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by kosh on 2017/5/3.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface ConditionDefault {
}
