package com.service.impl;

import com.dao.ProductDao;
import com.dao.TBrowseDao;
import com.entities.BrowseDO;
import com.entities.GoodsCategoryDO;
import com.entities.GoodsDO;
import com.github.pagehelper.PageInfo;
import com.pojo.*;
import com.service.ProductService;
import com.utils.CodeFactory;
import com.utils.ReqPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private TBrowseDao tBrowseDao;

    @Override
    public PageInfo<Product> list(ReqPage.QReqPage reqPage, Integer cid, Integer type) {
        //商品分类
        Integer[] cids = null;

        if (cid != null && 0 != cid) {
            //查子级
            List<GoodsCategoryDO> childs = this.findChilds(cid);
            if (childs != null && childs.size() > 0) {

                Set<Integer> collect = childs.stream()
                        .filter(x -> x.getCid() != null)
                        .map(GoodsCategoryDO::getCid)
                        .collect(Collectors.toSet());

                if (collect != null && collect.size() > 0) {
                    cids = collect.toArray(new Integer[collect.size() + 1]);
                }
            }
            //查本级
            if (cids != null) {
                cids[cids.length - 1] = cid;
            } else {
                cids = new Integer[1];
                cids[0] = cid;
            }
        }

        int totalCount = productDao.listCount(reqPage, cids);
        List<Product> list = null;
        if (totalCount > 0) {
            list = productDao.list(reqPage, cids, type);
        }
        PageInfo<Product> page = new PageInfo<Product>(list);
        page.setTotal(totalCount);
        return page;
    }

    @Override
    public boolean save(Product product) {
        product.setGoodsNum(generateNo());
        product.setCreateTime(Calendar.getInstance().getTime());
        if (product.getUpdateTime() == null)
            product.setUpdateTime(Calendar.getInstance().getTime());
        if (product.getDelistingTime() == null) {
            product.setDelistingTime(Date.from(Instant.MAX));
        }
        return 1 == productDao.save(product);
    }

    /**
     * 商品類型無限極
     *
     * @param pid
     * @return
     */
    @Override
    public List<GoodsCategoryDO> findChilds(Integer pid) {
        Integer[] pids = {pid};
        return this.findChilds(pids);
    }

    public List<GoodsCategoryDO> findChilds(Integer[] pids) {
        if (pids != null && pids.length > 0) {
            List<GoodsCategoryDO> children = productDao.findChildren(pids);
            if (children != null && children.size() > 0) {
                List<Integer> pidList = children.stream()
                        .filter(x -> x.getCid() != null)
                        .map(x -> x.getCid())
                        .collect(Collectors.toList());
                if (pidList != null && pidList.size() > 0) {
                    List<GoodsCategoryDO> childs = this.findChilds(pidList.toArray(new Integer[pidList.size()]));
                    if (childs != null && childs.size() > 0)
                        children.addAll(childs);
                }
                return children;
            }
        }
        return null;
    }

    /**
     * 生成商品编号
     *
     * @return
     */
    public String generateNo() {
        return CodeFactory.generateCode(3, true);
    }

    @Override
    @Transactional
    public SkuDateils details(long skuId, boolean isHistroy, Long hisUserId) {
        GoodsDO goods = productDao.findGoodsBySkuId(skuId);
        if (isHistroy && hisUserId != null) {
            boolean bl = false;
            if (goods != null) {
                BrowseDO history = new BrowseDO();
                history.setCreateTime(Calendar.getInstance().getTime());
                history.setGoodsId(goods.getGoodsId());
                history.setUpdateTime(Calendar.getInstance().getTime());
                history.setUserId(hisUserId);
                history.setSkuId(skuId);
                if (1 == tBrowseDao.addHistory(history)) {
                    bl = true;
                }
            }
            if (!bl) {
                throw new RuntimeException("查看商品详情，添加历史记录失败！");
            }
        }
        SkuDateils details = productDao.details(skuId);
        if(details != null && details.getGoodsVO() != null && details.getGoodsVO().getGoodsId() != null ){
            Long goodsId = details.getGoodsVO().getGoodsId();
            //查详情图片
            List<MediaRefVO> imgs = productDao.findImgsByRefIdOrType(goodsId,1);
            List<MediaRefVO> imgs2 = productDao.findImgsByRefIdOrType(goodsId,2);
            details.getGoodsVO().setMediaRefVOS(imgs);
            details.getGoodsVO().setGoodsInfoMedias(imgs2);
        }
        return details;
    }

    @Override
    public List<GoodsCategoryDO> categorys(Integer level) {
        Integer[] pids = {level};
        return productDao.findChildren(pids);
    }

    @Override
    public List<BrowseVo> historys(Long userId) {
        return tBrowseDao.historys(userId);
    }

    @Override
    public Map<String, Object> skuAttrs(long goodsId) {
        Map<String, Object> map = new HashMap<>();
        List<VOAttr> attrAll = productDao.skuAttrs(goodsId);
        List<VOAttr> skuAttrs = productDao.skuOLRAttrs(goodsId);
        map.put("attrAll", attrAll);
        map.put("skuAttrs", skuAttrs);
        return map;
    }

}
