package com._520it.wms.service.impl;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service
public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware {
    @Autowired
    private PermissionMapper permissionMapper;
    private  ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Override
    public void reload() {
        List<Permission> permissions = permissionMapper.selectAll();
        Set<String> experssions = new HashSet<>();
        for (Permission permission : permissions) {
            experssions.add(permission.getExpression());
        }
        Map<String, BaseAction> beanMaps = ctx.getBeansOfType(BaseAction.class);
        Collection<BaseAction> actions = beanMaps.values();
        for (BaseAction action : actions) {
            String actionName = action.getClass().getName();
            Method[] methods = action.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String expression = actionName+":"+method.getName();
                if (!experssions.contains(expression)&&method.isAnnotationPresent(RequiredPermission.class)){
                    RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                    Permission permission = new Permission();
                    permission.setExpression(expression);
                    permission.setName(annotation.value());
                    permissionMapper.insert(permission);
                }
            }
        }

    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = permissionMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<Permission> listData = permissionMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }


}
