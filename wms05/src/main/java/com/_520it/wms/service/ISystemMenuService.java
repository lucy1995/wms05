package com._520it.wms.service;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.SystemMenuQueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/30.
 */
public interface ISystemMenuService {
    int insert(SystemMenu record);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    PageResult query(SystemMenuQueryObject qo);

    List<SystemMenu> getPrentMenus(Long parentId);

    List<Map<String,Object>> queryMenuBySn(String parentSn);
}
