package com._520it.wms.web.interceptor;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/30.
 */
public class SecurityInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Employee e = UserContext.getEmployeeInSession();
        if (e.isAdmin()){
            return invocation.invoke();
        }
        Object action = invocation.getProxy().getAction();
        String methodName = invocation.getProxy().getMethod();
        Method method = action.getClass().getMethod(methodName);
        if (!method.isAnnotationPresent(RequiredPermission.class)) {
           return invocation.invoke();
        }
        //用户有没有访问方法的权限
        Set<String> expressions= UserContext.getEmployeeExpressionsInSession();
        String expression =action.getClass().getName()+":"+methodName;
        if (expressions.contains(expression)) {
            return invocation.invoke();
        }
        return "nopermission";
    }
}
