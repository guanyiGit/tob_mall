package com.dao;

import com.entities.GoodsCategoryDO;
import com.entities.GoodsDO;
import com.pojo.MediaRefVO;
import com.pojo.Product;
import com.pojo.SkuDateils;
import com.pojo.VOAttr;
import com.utils.ReqPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDao {


    /**
     * 查总条数
     *
     * @return
     */
    int listCount(@Param("reqPage") ReqPage.QReqPage reqPage, @Param("cids") Integer[] cids);

    /**
     * 查列表
     *
     * @param reqPage
     * @param cids    分类id集合
     * @param type    null->无条件
     *                1->按照上架时间 倒序
     *                2->按照销售量 倒序
     * @return
     */
    List<Product> list(@Param("reqPage") ReqPage.QReqPage reqPage, @Param("cids") Integer[] cids, @Param("type") Integer type);

    /**
     * 新增商品
     *
     * @param product
     * @return
     */
    int save(@Param("product") Product product);

    /**
     * 查找子集分类
     *
     * @param pids
     * @return
     */
    List<GoodsCategoryDO> findChildren(@Param("pids") Integer[] pids);

    /**
     * 查商品详情
     *
     * @param skuId
     * @return
     */
    SkuDateils details(@Param("skuId") long skuId);
//    List<SkuDateils> details(@Param("goodsId") long goodsId);

    /**
     * 查商品 根据skuid
     *
     * @param skuId
     */
    GoodsDO findGoodsBySkuId(@Param("skuId") long skuId);

    /**
     * 查看商品sku下所有属性
     *
     * @param goodsId
     * @return
     */
    List<VOAttr> skuOLRAttrs(long goodsId);

    /**
     * 查看商品所有sku属性
     *
     * @param goodsId
     * @return
     */
    List<VOAttr> skuAttrs(long goodsId);

    /**
     * 根据id查类别
     *
     * @param cid
     */
    GoodsCategoryDO findCTByid(Integer cid);

    /**
     * @param refId 图片关联id
     * @param type  图片类型
     * @return
     */
    List<MediaRefVO> findImgsByRefIdOrType(@Param("refId") long refId, @Param("type") int type);
}
