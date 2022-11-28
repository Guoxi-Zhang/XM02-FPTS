package com.fpts.product.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.product.domain.ProductCatalog;
import com.fpts.product.service.IProductCatalogService;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;

/**
 * 金融产品交易Controller
 * 
 * @author lay
 * @date 2022-11-26
 */
@Controller
@RequestMapping("/product/product_catalog")
public class ProductCatalogController extends BaseController
{
    private String prefix = "product/product_catalog";

    @Autowired
    private IProductCatalogService productCatalogService;

    @RequiresPermissions("product:product_catalog:view")
    @GetMapping()
    public String product_catalog()
    {
        return prefix + "/product_catalog";
    }

    /**
     * 查询金融产品交易列表
     */
    @RequiresPermissions("product:product_catalog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProductCatalog productCatalog)
    {
        startPage();
        List<ProductCatalog> list = productCatalogService.selectProductCatalogList(productCatalog);
        return getDataTable(list);
    }

    /**
     * 导出金融产品交易列表
     */
    @RequiresPermissions("product:product_catalog:export")
    @Log(title = "金融产品交易", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductCatalog productCatalog)
    {
        List<ProductCatalog> list = productCatalogService.selectProductCatalogList(productCatalog);
        ExcelUtil<ProductCatalog> util = new ExcelUtil<ProductCatalog>(ProductCatalog.class);
        return util.exportExcel(list, "金融产品交易数据");
    }

    /**
     * 新增金融产品交易
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存金融产品交易
     */
    @RequiresPermissions("product:product_catalog:add")
    @Log(title = "金融产品交易", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProductCatalog productCatalog)
    {
        return toAjax(productCatalogService.insertProductCatalog(productCatalog));
    }

    /**
     * 买入金融产品
     */
    @RequiresPermissions("product:product_catalog:purchase")
    @GetMapping("/purchase/{id}")
    public String purchase(@PathVariable("id") Integer id, ModelMap mmap)
    {
        ProductCatalog productCatalog = productCatalogService.selectProductCatalogById(id);
        mmap.put("productCatalog", productCatalog);
        return prefix + "/purchase";
    }

    /**
     * 保存买入金融产品
     */
    @RequiresPermissions("product:product_catalog:purchase")
    @Log(title = "金融产品交易", businessType = BusinessType.UPDATE)
    @PostMapping("/purchase")
    @ResponseBody
    public AjaxResult purchaseSave(ProductCatalog productCatalog)
    {
        return toAjax(productCatalogService.updateProductCatalog(productCatalog));
    }

    /**
     * 删除金融产品交易
     */
    @RequiresPermissions("product:product_catalog:remove")
    @Log(title = "金融产品交易", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(productCatalogService.deleteProductCatalogByIds(ids));
    }
}
