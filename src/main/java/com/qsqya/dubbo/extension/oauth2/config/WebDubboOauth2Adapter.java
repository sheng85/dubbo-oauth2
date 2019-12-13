/**
 * Copyright (C), 2015-2019
 * FileName: WebDubboOauth2Adapter
 * Author:   钱胜
 * Date:     2019/12/13 15:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qsqya.dubbo.extension.oauth2.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class WebDubboOauth2Adapter extends AbstractDubboOauth2Adapter {
    @Override
    public String getToken() {
        Authentication authentication = getAuthentication();
        Object details = authentication.getDetails();
        if (details instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails dt = (OAuth2AuthenticationDetails) details;
            if (null == dt) {
                return null;
            }
            String tokenValue = dt.getTokenValue();
            return tokenValue;
        }
        return null;
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
