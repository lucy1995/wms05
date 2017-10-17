package com._520it.wms.service.impl;

import com._520it.wms.domain.Client;
import com._520it.wms.mapper.ClientMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientMapper clientMapper;
    @Override
    public void save(Client client) {
        clientMapper.insert(client);
    }

    @Override
    public void delete(Long id) {
        clientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Client client) {
        clientMapper.updateByPrimaryKey(client);
    }

    @Override
    public Client get(Long id) {
        return clientMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Client> listAll() {
        return clientMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = clientMapper.query4count(qo);
        if (totalCount==0){
            return  new PageResult().emptyResult(qo.getPageSize());
        }
        List<Client> listData = clientMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,listData);
    }
}
