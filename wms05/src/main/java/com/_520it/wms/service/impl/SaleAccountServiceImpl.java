package com._520it.wms.service.impl;

import com._520it.wms.domain.SaleAccount;
import com._520it.wms.mapper.SaleAccountMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISaleAccountService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
@Service
public class SaleAccountServiceImpl implements ISaleAccountService {
    @Autowired
    private SaleAccountMapper saleAccountMapper;
    @Override
    public void save(SaleAccount saleAccount) {
        saleAccountMapper.insert(saleAccount);
    }

    @Override
    public void delete(Long id) {
        saleAccountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SaleAccount saleAccount) {
        saleAccountMapper.updateByPrimaryKey(saleAccount);
    }

    @Override
    public SaleAccount get(Long id) {
        return saleAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SaleAccount> listAll() {
        return saleAccountMapper.selectAll();
    }
}
