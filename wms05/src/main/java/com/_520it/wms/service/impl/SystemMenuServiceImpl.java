package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.SystemMenuMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.UserContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @Override
    public int insert(SystemMenu record) {
        systemMenuMapper.insert(record);
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        SystemMenuQueryObject qo = new SystemMenuQueryObject();
        qo.setParentId(id);
        int count = systemMenuMapper.query4count(qo);
        if (count!=0){
            throw new RuntimeException("有子菜单的菜单无法删除");
        }
        systemMenuMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SystemMenu record) {
        systemMenuMapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemMenu> selectAll() {
        return systemMenuMapper.selectAll();
    }

    @Override
    public PageResult query(SystemMenuQueryObject qo) {
        int count = systemMenuMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<SystemMenu> listData = systemMenuMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }

    @Override
    public List<SystemMenu> getPrentMenus(Long parentId) {
        SystemMenu current = systemMenuMapper.selectByPrimaryKey(parentId);
        List<SystemMenu> list = new ArrayList<>();
        while (current!=null){
            list.add(current);
            if (current.getParent()!=null){
                current = systemMenuMapper.selectByPrimaryKey(current.getParent().getId());
            }else {
                current =null;
            }
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryMenuBySn(String parentSn) {
        List<Map<String, Object>> menuList = new ArrayList<>();
        Employee e = UserContext.getEmployeeInSession();
        if (e.isAdmin()){
           menuList = systemMenuMapper.queryMenuBySn(parentSn);
        }else {
            menuList = systemMenuMapper.queryMenuBySnAndEmpId(parentSn,e.getId());
        }

        return menuList;
    }
}
