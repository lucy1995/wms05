package com._520it.wms.service.impl;

import com._520it.wms.domain.Brand;
import com._520it.wms.mapper.BrandMapper;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/9/51.
 */
@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public void save(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void delete(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public Brand get(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Brand> listAll() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = brandMapper.query4count(qo);
        if (totalCount==0){
            return  new PageResult().emptyResult(qo.getPageSize());
        }
        List<Brand> listData = brandMapper.query4list(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,listData);
    }
}
