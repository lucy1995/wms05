package com._520it.wms.service.impl;

import com._520it.wms.domain.Depot;
import com._520it.wms.mapper.DepotMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
public class DepotServiceImpl implements IDepotService {
    @Autowired
    private DepotMapper depotMapper;
    @Override
    public void save(Depot depot) {
        depotMapper.insert(depot);
    }

    @Override
    public void delete(Long id) {
        depotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Depot depot) {
        depotMapper.updateByPrimaryKey(depot);
    }

    @Override
    public Depot get(Long id) {
        return depotMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Depot> listAll() {
        return depotMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = depotMapper.query4count(qo);
        if (totalCount==0){
            return  new PageResult().emptyResult(qo.getPageSize());
        }
        List<Depot> listData = depotMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,listData);
    }
}
