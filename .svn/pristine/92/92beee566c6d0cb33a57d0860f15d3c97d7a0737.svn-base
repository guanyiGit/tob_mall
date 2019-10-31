package com.service;

import com.entities.GoodsCategoryDO;
import com.github.pagehelper.PageInfo;
import com.pojo.*;
import com.utils.ReqPage;

import java.util.List;
import java.util.Map;

public interface ProductService {
    /**
     * 查列表
     *
     * @param reqPage
     * @param cid
     * @param type    null->无条件
     *                1->按照上架时间 倒序
     *                2->按照销售量 倒序
     * @return
     */
    PageInfo<Product> list(ReqPage.QReqPage reqPage, Integer cid, Integer type);

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    boolean save(Product product);

    /**
     * 商品類型無限極
     *
     * @param pid
     * @return
     */
    List<GoodsCategoryDO> findChilds(Integer pid);


    /**
     * 查商品详情[新增浏览记录]
     *
     * @param skuId     商品skuid
     * @param isHistroy 是否留下历史记录，default->true
     * @param hisUserId 添加记录的用户id
     *                  isHistroy=>true 参数必传
     *                  isHistroy=>false 参数无效
     * @return
     */
    SkuDateils details(long skuId, boolean isHistroy, Long hisUserId);

    /**
     * 查看商品级别列表
     *
     * @param level 所属级别
     * @return
     */
    List<GoodsCategoryDO> categorys(Integer level);

    /**
     * 查看我的浏览历史 商品
     *
     * @param userId
     * @return
     */
    List<BrowseVo> historys(Long userId);

    /**
     * 查看商品所有sku属性
     *
     * @param goodsId
     * @return
     */
    Map<String, Object> skuAttrs(long goodsId);
}
