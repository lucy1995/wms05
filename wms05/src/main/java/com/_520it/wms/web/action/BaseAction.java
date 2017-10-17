package com._520it.wms.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.Servlet;
import java.io.IOException;

/**
 * Created by Administrator on 2017/9/11.
 */
public class BaseAction extends ActionSupport {
    public static final String LIST = "list";
    public static final String LOGIN = "login";
    public static final String MAIN = "main";

    public void putContext(String key , Object value){
        ActionContext.getContext().put(key,value);
    }

    public void putMsg(Object obj){
        try {
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().print("aaa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
