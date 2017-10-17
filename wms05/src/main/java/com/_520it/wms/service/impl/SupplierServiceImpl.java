package com._520it.wms.service.impl;

import com._520it.wms.domain.Supplier;
import com._520it.wms.mapper.SupplierMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Override
    public void save(Supplier supplier) {
        supplierMapper.insert(supplier);
    }

    @Override
    public void delete(Long id) {
        supplierMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Supplier supplier) {
        supplierMapper.updateByPrimaryKey(supplier);
    }

    @Override
    public Supplier get(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Supplier> listAll() {
        return supplierMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = supplierMapper.query4count(qo);
        if (totalCount==0){
            return  new PageResult().emptyResult(qo.getPageSize());
        }
        List<Supplier> listData = supplierMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,listData);
    }
}
