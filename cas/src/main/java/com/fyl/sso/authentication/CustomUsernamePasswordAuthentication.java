package com.fyl.sso.authentication;

import com.alibaba.fastjson.JSONObject;
import com.fyl.sso.config.DataSourceProperties;
import com.fyl.sso.model.User;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 自定义认证登录策略
 */
public class CustomUsernamePasswordAuthentication extends AbstractUsernamePasswordAuthenticationHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSourceProperties properties;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CustomUsernamePasswordAuthentication(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        logger.info("credential : " + JSONObject.toJSONString(credential));

        String username = credential.getUsername();
        String password = credential.getPassword();

        User user = jdbcTemplate.queryForObject(properties.getSql(), new Object[]{username}, new BeanPropertyRowMapper<>(User.class));

        if (user == null) {
            throw new AccountException("Sorry, username not found!");
        }

        if (!user.getPassword().equals(password)) {
            throw new FailedLoginException("Sorry, password not correct!");
        } else {
            //可自定义返回给客户端的多个属性信息
            HashMap<String, Object> returnInfo = new HashMap<>();
            returnInfo.put("expired", user.getDisabled());

            final List<MessageDescriptor> list = new ArrayList<>();

            return createHandlerResult(credential, this.principalFactory.createPrincipal(username, returnInfo), list);
        }

    }
}
