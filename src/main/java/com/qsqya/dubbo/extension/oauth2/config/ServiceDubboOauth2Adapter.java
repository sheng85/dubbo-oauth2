/**
 * Copyright (C), 2015-2019
 * FileName: ServiceDubboOauth2Adapter
 * Author:   钱胜
 * Date:     2019/12/13 15:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qsqya.dubbo.extension.oauth2.config;

import com.qsqya.dubbo.extension.oauth2.DubboOauth2Context;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class ServiceDubboOauth2Adapter extends AbstractDubboOauth2Adapter {
    @Override
    public String getToken() {
        return DubboOauth2Context.getContext().getToken();
    }

    @Override
    public Authentication getAuthentication() {
        String access_token = getToken();
        if (null == access_token || "".equals(access_token)) {
            return null;
        }

        TokenStore tokenStore = getApplicationContext().getBean(TokenStore.class);
        if (null == tokenStore) {
            return null;
        }
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(access_token);
        return oAuth2Authentication;
    }
}
