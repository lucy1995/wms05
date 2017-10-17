package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by Administrator on 2017/9/30.
 */
public class CheckLoginInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Employee e = UserContext.getEmployeeInSession();
        if (e==null){
            return Action.LOGIN;
        }
        return invocation.invoke();
    }
}
