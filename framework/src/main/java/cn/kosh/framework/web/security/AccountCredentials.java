package cn.kosh.framework.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by kosh on 2017/5/18.
 */
public class AccountCredentials extends User {
    private Claims claims = new DefaultClaims();
    private static final String ID_KEY = "id";
    private static final String ROLE_ID = "role";
    private static final String ORG_ID = "org";

    public AccountCredentials(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AccountCredentials(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    public void setClaim(String key, Object value) {
        if (StringUtils.isEmpty(key) || value == null) {
            return;
        }
        claims.put(key, value);
    }

    public String getClaim(String key) {
        return claims != null ? (String) claims.get(key) : null;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public String getUserid() {
        return getClaim(ID_KEY);
    }

    public void setUserid(String id) {
        setClaim(ID_KEY, id);
    }

    public String getRoleid() {
        return getClaim(ROLE_ID);
    }

    public void setRoleid(String roleid) {
        setClaim(ROLE_ID, roleid);
    }

    public String getOrgid() {
        return getClaim(ORG_ID);
    }

    public void setOrgid(String orgid) {
        setClaim(ORG_ID, orgid);
    }
}
