package com._520it.wms.web.action;

import com._520it.wms.domain.Product;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.util.FileUploadUtil;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * Created by Administrator on 2017/9/11.
 */
@Controller
@Scope("prototype")
public class ProductAction extends BaseAction {

    @Autowired
    private IProductService productService;
    @Autowired
    private IBrandService brandService;
    @Getter
    Product product = new Product();
    @Getter
    ProductQueryObject qo = new ProductQueryObject();
    @Setter
    private File pic;
    @Setter
    private String picFileName;

    @RequiredPermission("商品列表")
    @InputConfig(methodName = "input")
    public String execute()  {
        try {
            PageResult pageResult = productService.query(qo);
            putContext("pageResult",pageResult);
            putContext("brands",brandService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    public String selectProduct()  {
        try {
            qo.setPageSize(20);
            putContext("brands",brandService.listAll());
            PageResult pageResult = productService.query(qo);
            putContext("pageResult",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "selectProduct";
    }

    @RequiredPermission("商品编辑")
    public String input() {
        try {
            putContext("brands",brandService.listAll());
            if (product.getId()!=null){
                product = productService.get(product.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return INPUT;
    }

    @RequiredPermission("商品删除")
    public String delete() {
        try {
            String imagePath = productService.get(product.getId()).getImagePath();
            if (imagePath!=null){
                FileUploadUtil.deleteFile(imagePath);
            }
            productService.delete(product.getId());
            putMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败:"+e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("商品更新")
    public String saveOrUpdate() {

        try {
            if (pic!=null){
                if (product.getImagePath()!=null){
                    FileUploadUtil.deleteFile(product.getImagePath());
                }
                String path = FileUploadUtil.uploadFile(pic, picFileName);
                product.setImagePath(path);
            }
            if (product.getId()!=null){
                productService.update(product);
                addActionMessage("更新成功");
            }else {
                productService.save(product);
                addActionMessage("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("更新失败:"+e.getMessage());
        }
        return SUCCESS;
    }
}
