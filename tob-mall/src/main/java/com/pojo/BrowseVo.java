package com.pojo;

import com.entities.BrowseDO;
import com.entities.GoodsDO;
import com.entities.SkuDO;

import java.util.List;

public class BrowseVo extends BrowseDO {

    private GoodsDO goodsDO;

    /**
     * 商品图片
     */
    private List<MediaRefVO> mediaRefVOS;

    private List<SkuDO> skuDOList;

    //销售量
    private Long totalNum;

    //浏览量
    private Long viewNum;

    public GoodsDO getGoodsDO() {
        return goodsDO;
    }

    public void setGoodsDO(GoodsDO goodsDO) {
        this.goodsDO = goodsDO;
    }

    public List<SkuDO> getSkuDOList() {
        return skuDOList;
    }

    public void setSkuDOList(List<SkuDO> skuDOList) {
        this.skuDOList = skuDOList;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public List<MediaRefVO> getMediaRefVOS() {
        return mediaRefVOS;
    }

    public void setMediaRefVOS(List<MediaRefVO> mediaRefVOS) {
        this.mediaRefVOS = mediaRefVOS;
    }
}
