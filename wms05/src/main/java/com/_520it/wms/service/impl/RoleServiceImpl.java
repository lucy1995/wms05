package com._520it.wms.service.impl;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.RoleMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insert(Role record) {
        roleMapper.insert(record);
        List<Permission> permissions = record.getPermissions();
        for (Permission permission : permissions) {
            roleMapper.addPermissionRelation(record.getId(),permission.getId());
        }
        List<SystemMenu> menus = record.getMenus();
        for (SystemMenu menu : menus) {
            roleMapper.addMenuRelation(record.getId(),menu.getId());
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        roleMapper.deletePermissionRelation(id);
        roleMapper.deleteMenuRelation(id);
        roleMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        roleMapper.deletePermissionRelation(record.getId());
        roleMapper.deleteMenuRelation(record.getId());
        List<Permission> permissions = record.getPermissions();
        for (Permission permission : permissions) {
            roleMapper.addPermissionRelation(record.getId(),permission.getId());
        }
        List<SystemMenu> menus = record.getMenus();
        for (SystemMenu menu : menus) {
            roleMapper.addMenuRelation(record.getId(),menu.getId());
        }
        roleMapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = roleMapper.query4count(qo);
        if (count==0) {return PageResult.emptyResult(qo.getPageSize());}
        List<Role> listData = roleMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),count,listData);
    }
}
