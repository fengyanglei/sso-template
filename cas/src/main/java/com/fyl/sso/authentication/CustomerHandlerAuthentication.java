package com.fyl.sso.authentication;

import com.alibaba.fastjson.JSONObject;
import com.fyl.sso.config.DataSourceProperties;
import com.fyl.sso.model.User;
import com.fyl.sso.model.CustomCredential;
import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
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
 * 自定义表单认证登录策略
 */
public class CustomerHandlerAuthentication extends AbstractPreAndPostProcessingAuthenticationHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataSourceProperties properties;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CustomerHandlerAuthentication(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    public boolean supports(Credential credential) {
        //判断传递过来的Credential 是否是自己能处理的类型
        return credential instanceof UsernamePasswordCredential;
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        logger.info("credential : " + JSONObject.toJSONString(credential));

//        UsernamePasswordCredential usernamePasswordCredentia = (UsernamePasswordCredential) credential;
        CustomCredential customCredential = (CustomCredential) credential;
        String username = customCredential.getUsername();
        String password = customCredential.getPassword();

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

            return createHandlerResult(customCredential, this.principalFactory.createPrincipal(username, returnInfo), list);
        }


    }
}
