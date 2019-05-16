package com.pojo;

import com.entities.SkuDO;

import java.util.List;

public class SkuDateils extends SkuDO {

    //销售量
    private Long totalNum;

    //浏览量
    private Long viewNum;

    private GoodsVO goodsVO;

    private List<SkuValueRefVo> skuValueRefVos;

    //评价
    private List<EvaluationVO> evaluationDOS;

    public GoodsVO getGoodsVO() {
        return goodsVO;
    }

    public void setGoodsVO(GoodsVO goodsVO) {
        this.goodsVO = goodsVO;
    }

    public List<SkuValueRefVo> getSkuValueRefVos() {
        return skuValueRefVos;
    }

    public void setSkuValueRefVos(List<SkuValueRefVo> skuValueRefVos) {
        this.skuValueRefVos = skuValueRefVos;
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

    public List<EvaluationVO> getEvaluationDOS() {
        return evaluationDOS;
    }

    public void setEvaluationDOS(List<EvaluationVO> evaluationDOS) {
        this.evaluationDOS = evaluationDOS;
    }
}
