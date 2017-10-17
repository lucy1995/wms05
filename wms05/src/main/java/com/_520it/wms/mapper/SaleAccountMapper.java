package com._520it.wms.mapper;

import com._520it.wms.domain.SaleAccount;
import java.util.List;

public interface SaleAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleAccount record);

    SaleAccount selectByPrimaryKey(Long id);

    List<SaleAccount> selectAll();

    int updateByPrimaryKey(SaleAccount record);
}