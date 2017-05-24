package cn.kosh.framework.web.security;

import cn.kosh.framework.domain.Message;
import cn.kosh.framework.util.IpUtils;
import cn.kosh.framework.util.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by kosh on 2017/5/18.
 */
@Service
public class TokenAuthenticationService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${sys.jwt.expiration:432000}")
    private long EXPIRATIONTIME;     // 5天
    private String SECRET = "P@ssw02d18628260919";            // JWT密码
    private String TOKEN_PREFIX = "Bearer";        // Token前缀
    private String HEADER_STRING = "Authorization";// 存放Token的Header Key
    @Value("${sys.jwt.dev.token}")
    private String dev_token;

    public void addAuthentication(HttpServletResponse response, Authentication auth) throws IOException {

        String authorities = StringUtils.replaceAll(auth.getAuthorities().toString(), "[\\[\\]]", "");
        AccountCredentials accountCredentials = (AccountCredentials) auth.getDetails();

        String token = Jwts.builder()
                .setClaims(accountCredentials.getClaims())
                .claim("authorities", authorities)
                .setSubject(auth.getName())
                .setExpiration(new Date(System.currentTimeMillis() + (EXPIRATIONTIME * 1000)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");// 跨域
        response.getWriter().write(JsonUtils.toJson(new Message(token)));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        token = StringUtils.isNotEmpty(token) ? token : dev_token;
        if (logger.isDebugEnabled()) {
            logger.debug("token: {}, remote host: {}", token, IpUtils.getClientIP(request));
        }

        if (StringUtils.isEmpty(token)) {
            logger.warn(String.format("access denied: %s %s, empty token, remote host: %s", request.getMethod(), request.getRequestURI(), IpUtils.getClientIP(request)));
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            String username = claims.getSubject();

            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

            UsernamePasswordAuthenticationToken authentication = username != null ? new UsernamePasswordAuthenticationToken(username, null, authorities) : null;
            AccountCredentials user = new AccountCredentials(username, "", authorities);
            user.setClaims(claims);
            authentication.setDetails(user);

            return authentication;
        } catch (ExpiredJwtException e) {
            logger.warn(String.format("access denied: %s %s, expired token, remote host: %s", request.getMethod(), request.getRequestURI(), IpUtils.getClientIP(request)));
        } catch (RuntimeException e) {
            logger.warn(String.format("access denied: %s %s, invalid token, remote host: %s", request.getMethod(), request.getRequestURI(), IpUtils.getClientIP(request)));
        }
        return null;
    }
}
