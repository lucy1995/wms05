package com._520it.wms.web.action;

import com._520it.wms.domain.Employee;
import com._520it.wms.service.IEmployeeService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/9/30.
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
    @Setter
    private String username;
    @Setter
    private String password;
    @Autowired
    private IEmployeeService employeeService;

    public String execute(){
        try {
            Employee emp = employeeService.checkLogin(username,password);
            if (emp==null){
                addActionError("您的账号或者密码有误,请重试");
                return LOGIN;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return MAIN;
    }
}
