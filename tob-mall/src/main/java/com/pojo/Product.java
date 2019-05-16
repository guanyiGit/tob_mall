package com.pojo;

import com.entities.GoodsDO;
import com.entities.SkuDO;

import java.util.List;

public class Product extends GoodsDO {

    private Long totalNum;

    private Long viewNum;

    private List<MediaRefVO> tMediaVos;

    private List<SkuDO> skuDOList;

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public List<SkuDO> getSkuDOList() {
        return skuDOList;
    }

    public void setSkuDOList(List<SkuDO> skuDOList) {
        this.skuDOList = skuDOList;
    }

    public List<MediaRefVO> gettMediaVos() {
        return tMediaVos;
    }

    public void settMediaVos(List<MediaRefVO> tMediaVos) {
        this.tMediaVos = tMediaVos;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }
}
