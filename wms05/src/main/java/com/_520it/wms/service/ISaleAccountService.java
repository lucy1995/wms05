package com._520it.wms.service;

import com._520it.wms.domain.SaleAccount;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public interface ISaleAccountService {
    void save(SaleAccount saleAccount);

    void delete(Long id);

    void update(SaleAccount saleAccount);

    SaleAccount get(Long id);

    List<SaleAccount> listAll();
}
