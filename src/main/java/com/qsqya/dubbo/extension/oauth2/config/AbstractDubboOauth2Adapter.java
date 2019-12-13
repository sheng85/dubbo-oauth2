/**
 * Copyright (C), 2015-2019
 * FileName: DubboOauth2Configu
 * Author:   钱胜
 * Date:     2019/12/13 14:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qsqya.dubbo.extension.oauth2.config;

import com.qsqya.dubbo.extension.oauth2.DubboOauth2Context;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;

public abstract class AbstractDubboOauth2Adapter implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public <T> T getUserDetail() {
        String token = DubboOauth2Context.getContext().getToken();
        if (null == token || "".equals(token)) {
            return null;
        }
        Authentication authentication = getAuthentication();
        if (null == authentication) {
            return null;
        }
        return (T) authentication.getPrincipal();
    }


    public String getTokenKey() {
        return "qsqya.dubbo.token";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AbstractDubboOauth2Adapter.applicationContext = applicationContext;
    }

    public abstract String getToken();

    public abstract Authentication getAuthentication();
}
