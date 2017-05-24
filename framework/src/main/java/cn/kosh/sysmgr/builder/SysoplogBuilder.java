package cn.kosh.sysmgr.builder;

import cn.kosh.framework.SpringHolder;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.util.IpUtils;
import cn.kosh.sysmgr.domain.Sysoplog;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by kosh on 2017/5/21.
 */
public class SysoplogBuilder {
    public static Sysoplog build(OperationType type, String content, Object... params) {
        Sysoplog log = new Sysoplog();
        log.setType(type.name());
        log.setContent(String.format(content, params));
        log.setOpuser(SpringHolder.getCurrentuser().getUserid());
        log.setCreate_time(new Date());
        log.setServer_ip(IpUtils.getCurrentIp());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.setClient_ip(IpUtils.getClientIP(request));
        return log;
    }
}
