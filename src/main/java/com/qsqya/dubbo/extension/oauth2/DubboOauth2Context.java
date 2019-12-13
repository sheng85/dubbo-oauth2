/**
 * Copyright (C), 2015-2019
 * FileName: DubboOauth2Context
 * Author:   钱胜
 * Date:     2019/12/13 13:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qsqya.dubbo.extension.oauth2;

import com.qsqya.dubbo.extension.oauth2.config.AbstractDubboOauth2Adapter;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

public class DubboOauth2Context {

    private static ThreadLocal<DubboOauth2Context> LOCAL = new ThreadLocal<DubboOauth2Context>();

    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public <T> T getUserdetail() {
        if (null == this.token || "" == this.token) {
            return null;
        }
        ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
        AbstractDubboOauth2Adapter dubboOauth2Configuration = ac.getBean(AbstractDubboOauth2Adapter.class);
        Object userDetail = dubboOauth2Configuration.getUserDetail();
        return (T) userDetail;
    }

    public static DubboOauth2Context getContext() {
        DubboOauth2Context dubboOauth2Context = LOCAL.get();
        if (null == dubboOauth2Context) {
            dubboOauth2Context = new DubboOauth2Context();
            LOCAL.set(dubboOauth2Context);
        }
        return dubboOauth2Context;
    }


}
