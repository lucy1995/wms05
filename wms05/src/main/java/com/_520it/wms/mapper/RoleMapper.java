package com._520it.wms.mapper;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int query4count(QueryObject qo);

    List<Role> query4list(QueryObject qo);

    void addPermissionRelation(@Param("role_id") Long role_id, @Param("permission_id")Long permission_id);

    void deletePermissionRelation(Long id);

    void addMenuRelation(@Param("role_id")Long role_id, @Param("menu_id")Long menu_id);

    void deleteMenuRelation(Long id);
}