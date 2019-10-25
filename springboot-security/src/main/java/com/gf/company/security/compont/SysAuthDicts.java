package com.gf.company.security.compont;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Liu Jinglei
 * @version 1.0
 * @Date Created in 2019/6/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Component
@ConfigurationProperties(prefix = "auth-dicts")
public class SysAuthDicts {
    private List<SysAuthType> auth_type;
    private String auth_str = "AUTH_COMPANY_BUSINESS,AUTH_COMPANY_DEPART,AUTH_COMPANY_DISH" +
            ",AUTH_COMPANY_PAY,AUTH_COMPANY_MEMBER,AUTH_COMPANY_INVENTORY" +
            ",AUTH_COMPANY_SYS_CONFIG,AUTH_WAITER_OPEN_ORDER,AUTH_WAITER_CASHIER,AUTH_WAITER_LINE" +
            ",AUTH_WAITER_LINK_QR,AUTH_WAITER_COPY_DISH,AUTH_COMMENT_DEPART,AUTH_COMMENT_MANAGE" +
            ",AUTH_COMMENT_FINANCIAL";
}

class SysAuthType{
    private String auth_type_name;
    private List<AuthBean> auth_infos;

    public String getAuth_type_name() {
        return auth_type_name;
    }

    public void setAuth_type_name(String auth_type_name) {
        this.auth_type_name = auth_type_name;
    }

    public List<AuthBean> getAuth_infos() {
        return auth_infos;
    }

    public void setAuth_infos(List<AuthBean> auth_infos) {
        this.auth_infos = auth_infos;
    }
}

class AuthBean{
    private String auth_code;
    private String auth_name;

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getAuth_name() {
        return auth_name;
    }

    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }
}