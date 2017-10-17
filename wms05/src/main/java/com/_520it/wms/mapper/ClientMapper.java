package com._520it.wms.mapper;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.Client;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client record);

    int query4count(QueryObject qo);

    List<Client> query4list(QueryObject qo);
}