package com._520it.wms.util;

import com._520it.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

/**
 * Created by Administrator on 2017/9/16.
 */
public class UserContext {
    public  static  final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    public  static  final String EXPRESSIONS_IN_SESSION = "EXPRESSIONS_IN_SESSION";

    public static void putEmployeeSession(Employee e){
        ActionContext.getContext().getSession().put(EMPLOYEE_IN_SESSION,e);
    }
    public static void putEmployeeExpressionsSession(Set<String> expressions){
        ActionContext.getContext().getSession().put(EXPRESSIONS_IN_SESSION,expressions);
    }
    public static Employee getEmployeeInSession(){
        return (Employee) ActionContext.getContext().getSession().get(EMPLOYEE_IN_SESSION);
    }
    public static Set<String> getEmployeeExpressionsInSession(){
        return (Set<String>) ActionContext.getContext().getSession().get(EXPRESSIONS_IN_SESSION);
    }
    public static  void clearSession(){
        ActionContext.getContext().getSession().clear();
    }
}
