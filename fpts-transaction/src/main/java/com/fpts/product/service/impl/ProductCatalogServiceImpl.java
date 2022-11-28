package com.fpts.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fpts.product.mapper.ProductCatalogMapper;
import com.fpts.product.domain.ProductCatalog;
import com.fpts.product.service.IProductCatalogService;
import com.fpts.common.core.text.Convert;

/**
 * 金融产品交易Service业务层处理
 * 
 * @author lay
 * @date 2022-11-26
 */
@Service
public class ProductCatalogServiceImpl implements IProductCatalogService 
{
    @Autowired
    private ProductCatalogMapper productCatalogMapper;

    /**
     * 查询金融产品交易
     * 
     * @param id 金融产品交易主键
     * @return 金融产品交易
     */
    @Override
    public ProductCatalog selectProductCatalogById(Integer id)
    {
        return productCatalogMapper.selectProductCatalogById(id);
    }

    /**
     * 查询金融产品交易列表
     * 
     * @param productCatalog 金融产品交易
     * @return 金融产品交易
     */
    @Override
    public List<ProductCatalog> selectProductCatalogList(ProductCatalog productCatalog)
    {
        return productCatalogMapper.selectProductCatalogList(productCatalog);
    }

    /**
     * 新增金融产品交易
     * 
     * @param productCatalog 金融产品交易
     * @return 结果
     */
    @Override
    public int insertProductCatalog(ProductCatalog productCatalog)
    {
        return productCatalogMapper.insertProductCatalog(productCatalog);
    }

    /**
     * 修改金融产品交易
     * 
     * @param productCatalog 金融产品交易
     * @return 结果
     */
    @Override
    public int updateProductCatalog(ProductCatalog productCatalog)
    {
        return productCatalogMapper.updateProductCatalog(productCatalog);
    }

    /**
     * 批量删除金融产品交易
     * 
     * @param ids 需要删除的金融产品交易主键
     * @return 结果
     */
    @Override
    public int deleteProductCatalogByIds(String ids)
    {
        return productCatalogMapper.deleteProductCatalogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除金融产品交易信息
     * 
     * @param id 金融产品交易主键
     * @return 结果
     */
    @Override
    public int deleteProductCatalogById(Integer id)
    {
        return productCatalogMapper.deleteProductCatalogById(id);
    }
}
