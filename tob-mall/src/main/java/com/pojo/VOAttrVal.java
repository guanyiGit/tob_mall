package com.pojo;

import com.entities.AttrValueDO;
import com.entities.SkuDO;

public class VOAttrVal extends AttrValueDO {

    private SkuDO skuDO;

    public SkuDO getSkuDO() {
        return skuDO;
    }

    public void setSkuDO(SkuDO skuDO) {
        this.skuDO = skuDO;
    }
}
