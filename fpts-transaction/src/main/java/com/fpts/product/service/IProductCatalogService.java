package com.fpts.product.service;

import java.util.List;
import com.fpts.product.domain.ProductCatalog;

/**
 * 金融产品交易Service接口
 * 
 * @author lay
 * @date 2022-11-26
 */
public interface IProductCatalogService 
{
    /**
     * 查询金融产品交易
     * 
     * @param id 金融产品交易主键
     * @return 金融产品交易
     */
    public ProductCatalog selectProductCatalogById(Integer id);

    /**
     * 查询金融产品交易列表
     * 
     * @param productCatalog 金融产品交易
     * @return 金融产品交易集合
     */
    public List<ProductCatalog> selectProductCatalogList(ProductCatalog productCatalog);

    /**
     * 新增金融产品交易
     * 
     * @param productCatalog 金融产品交易
     * @return 结果
     */
    public int insertProductCatalog(ProductCatalog productCatalog);

    /**
     * 修改金融产品交易
     * 
     * @param productCatalog 金融产品交易
     * @return 结果
     */
    public int updateProductCatalog(ProductCatalog productCatalog);

    /**
     * 批量删除金融产品交易
     * 
     * @param ids 需要删除的金融产品交易主键集合
     * @return 结果
     */
    public int deleteProductCatalogByIds(String ids);

    /**
     * 删除金融产品交易信息
     * 
     * @param id 金融产品交易主键
     * @return 结果
     */
    public int deleteProductCatalogById(Integer id);
}
