/**
 * Copyright (C), 2015-2019
 * FileName: ProviderFilter
 * Author:   钱胜
 * Date:     2019/12/13 13:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qsqya.dubbo.extension.oauth2;

import com.qsqya.dubbo.extension.oauth2.config.AbstractDubboOauth2Adapter;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.context.ApplicationContext;

@Activate(group = CommonConstants.PROVIDER)
public class ProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("ProviderFilter");

        ApplicationContext applicationContext = AbstractDubboOauth2Adapter.getApplicationContext();
        AbstractDubboOauth2Adapter dubboOauth2Adapter = applicationContext.getBean(AbstractDubboOauth2Adapter.class);
        if (null != dubboOauth2Adapter) {
            String token = RpcContext.getContext().getAttachment(dubboOauth2Adapter.getTokenKey());

            DubboOauth2Context.getContext().setToken(token);
        }

        return invoker.invoke(invocation);
    }
}
